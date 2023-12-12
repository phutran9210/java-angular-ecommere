package com.example.backend.application.service;

import com.example.backend.application.dto.guestDto.GuestDto;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<GuestDto> getAllGuest();

    Optional<GuestDto> findUserByIdWithAddress(String id);

    Optional<GuestDto> findUserById(String id);

    GuestDto createGuest(GuestDto guestDto);
}
