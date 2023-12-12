package com.example.backend.interfaceUI.rest;

import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.application.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public void createUser(@RequestBody GuestDto guestDto) {
        System.out.println(guestDto);
//        GuestDto createdGuest = guestService.createGuest(guestDto);
//        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public String loginUser() {
        return "100";
    }

    @GetMapping()
    public List<GuestDto> getAll() {
        return guestService.getAllGuest();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getUserById(@PathVariable String id,
                                                @RequestParam(required = false) Boolean withAddress) {
        Optional<GuestDto> guest = (Boolean.TRUE.equals(withAddress))
                ? guestService.findUserByIdWithAddress(id)
                : guestService.findUserById(id);
        return guest.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
