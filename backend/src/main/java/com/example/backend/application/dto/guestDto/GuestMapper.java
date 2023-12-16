package com.example.backend.application.dto.guestDto;

import com.example.backend.application.dto.guestAddressDto.GuestAddressMapper;
import com.example.backend.domain.entity.guest.Guest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


public class GuestMapper {
    public static GuestDto toDto(Guest guest, Boolean loadAddresses) {
        if (guest == null) {
            return null;
        }

        GuestDto dto = new GuestDto();
        dto.setGuestId(guest.getGuestId());
        dto.setEmail(guest.getEmail());
        dto.setPhone(guest.getPhone());
        dto.setCreateAt(LocalDateTime.ofInstant(guest.getCreateAt(), ZoneId.systemDefault()));
        dto.setStatus(guest.getStatus());

        if (loadAddresses && guest.getAddresses() != null) {
            dto.setAddresses(guest.getAddresses().stream()
                    .map(GuestAddressMapper::toDto)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    public static List<GuestDto> toDtoList(List<Guest> guests, boolean loadAddresses) {
        return guests.stream()
                .map(guest -> toDto(guest, loadAddresses))
                .collect(Collectors.toList());
    }

}


