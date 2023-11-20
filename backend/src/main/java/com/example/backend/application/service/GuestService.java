package com.example.backend.application.service;

import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.domain.entity.Guest;

import java.util.List;

public interface GuestService {
    List<GuestDto> getAllGuest();
}
