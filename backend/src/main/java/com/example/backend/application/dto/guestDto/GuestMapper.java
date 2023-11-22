package com.example.backend.application.dto.guestDto;

import com.example.backend.domain.entity.Guest;

import java.util.List;
import java.util.stream.Collectors;


public class GuestMapper {
    public static GuestDto toDto(Guest guest) {
        GuestDto dto = new GuestDto();
        dto.setGuestId(guest.getGuestId());
        dto.setUsername(guest.getUsername());
        dto.setEmail(guest.getEmail());
        dto.setPhone(guest.getPhone());
        dto.setCreateAt(guest.getCreateAt());
        dto.setStatus(guest.getStatus());
        return dto;
    }

    public static List<GuestDto> toDtoList(List<Guest> guests) {
        return guests.stream().map(GuestMapper::toDto).collect(Collectors.toList());
    }

}


