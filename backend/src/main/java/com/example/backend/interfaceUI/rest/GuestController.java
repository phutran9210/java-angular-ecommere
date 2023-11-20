package com.example.backend.interfaceUI.rest;

import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.application.service.GuestService;
import com.example.backend.domain.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
