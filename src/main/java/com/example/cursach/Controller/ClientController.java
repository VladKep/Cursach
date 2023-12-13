package com.example.cursach.Controller;

import com.example.cursach.Model.Client;
import com.example.cursach.Model.Note;
import com.example.cursach.Model.ParkingSpot;
import com.example.cursach.Repository.NoteRepository;
import com.example.cursach.Repository.ParkingSpotRepository;
import com.example.cursach.Service.ClientService;
import com.example.cursach.Security.ClientDetails;
import com.example.cursach.Service.ClientServiceAdmin;
import com.example.cursach.Service.NoteService;
import com.example.cursach.Service.ParkingSpotService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class ClientController {
    private final ClientService clientService;
    private final ClientServiceAdmin clientServiceAdmin;
    private final ParkingSpotService parkingSpotService;
    private final NoteService noteService;
    private final ParkingSpotRepository parkingSpotRepository;
    private final NoteRepository noteRepository;

    public ClientController(ClientService clientService, ClientServiceAdmin clientServiceAdmin,
                            ParkingSpotService parkingSpotService,
                            NoteService noteService, ParkingSpotRepository parkingSpotRepository,
                            NoteRepository noteRepository) {
        this.clientService = clientService;
        this.clientServiceAdmin = clientServiceAdmin;
        this.parkingSpotService = parkingSpotService;
        this.noteService = noteService;
        this.parkingSpotRepository = parkingSpotRepository;
        this.noteRepository = noteRepository;
    }

    @GetMapping("")
    public String mainPage() {
        return "client/main";
    }

    @GetMapping("/available")
    public String availableSpots(Model model) {
        model.addAttribute("available", clientService.availableParkingSpots());
        return "client/freeSpots";
    }

    @GetMapping("/my-info")
    public String userInfo(@AuthenticationPrincipal ClientDetails clientDetails, Model model) {
        //model.addAttribute("client", clientDetails.getClient());
        Client client = clientServiceAdmin.clientById(clientDetails.getClient().getId());
        model.addAttribute("client", client);
        return "client/clientInfo";
    }

    @GetMapping("/reservation")
    public String myReservations(Model model, @AuthenticationPrincipal ClientDetails clientDetails) {
        int id = clientDetails.getClient().getId();
        model.addAttribute("reservations", clientServiceAdmin.clientById(id).getNotes());
        return "client/myReservations";
    }

    @GetMapping("/my-info/edit")
    public String edit(@AuthenticationPrincipal ClientDetails clientDetails, Model model) {
        Client client = clientServiceAdmin.clientById(clientDetails.getClient().getId());
        model.addAttribute("client", client);
        return "client/clientInfoEdit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("client")Client client, @PathVariable("id") int id) {
        clientService.update(id, client);
        return "redirect:/my-info";
    }

    @GetMapping("/available/{id}/reservation")
    public String reservation(@PathVariable("id") int id, Model model) {
        model.addAttribute("spot", parkingSpotService.findById(id));
        model.addAttribute("note", new Note());
        return "client/spotReservation";
    }

    @PostMapping("/available/{id}")
    public String reserv(@ModelAttribute("note") Note note,
                         @PathVariable("id") int id, @AuthenticationPrincipal ClientDetails clientDetails) {
        Note note1 = new Note();
        note1.setClient(clientDetails.getClient());
        ParkingSpot parkingSpot = parkingSpotService.findById(id);
        note1.setParkingSpot(parkingSpot);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(note.getStartDate(), formatter);
        note1.setStartDate(note.getStartDate());
        note1.setEndDate(note.getEndDate());
        LocalDateTime end = LocalDateTime.parse(note.getEndDate(), formatter);
        Float price = (float) parkingSpot.getPrice() * Duration.between(start, end).toMinutes() / 60;
        note1.setFinalPrice(price);
        note1.setStatus("Активне");
        clientService.reservation(note1);
        parkingSpot.setStatus("Зайнято");
        parkingSpotRepository.save(parkingSpot);
        return "redirect:/confirm/{id}";
    }

    @GetMapping("/confirm/{id}")
    public String confirmation(Model model, @PathVariable("id") int id){
        ParkingSpot parkingSpot = parkingSpotService.findById(id);
        List<Note> notes = noteRepository.findByParkingSpot(parkingSpot);
        Optional<Note> activeNote = notes.stream()
                .filter(note -> "Активне".equals(note.getStatus()))
                .findFirst();
        if (activeNote.isPresent()) {
            model.addAttribute("note", activeNote.get());
            return "client/confirmReserv";
        } else {
            throw new NoSuchElementException("Не вдалося знайти запис для паркувального місця з ID: " + id);
        }
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin/mainAdmin";
    }
}
