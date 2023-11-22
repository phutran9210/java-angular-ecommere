package com.example.backend.interfaceUI.rest;

import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.application.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/guests")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping()
    public List<GuestDto> getAll() {
        return guestService.getAllGuest();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getUserById(@PathVariable String id,
                                                @RequestParam(required = false) Boolean primaryOnly) {
        Optional<GuestDto> guest;
        if (Boolean.TRUE.equals(primaryOnly)) {
            guest = guestService.findUserByIdWithPrimaryAddress(id);
        } else {
            guest = guestService.findUserByIdWithAddresses(id);
        }

        return guest.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
