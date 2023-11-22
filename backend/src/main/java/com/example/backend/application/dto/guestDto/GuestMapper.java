package com.example.backend.application.dto.guestDto;

import com.example.backend.application.dto.guestAddressDto.GuestAddressMapper;
import com.example.backend.domain.entity.guest.Guest;

import java.util.List;
import java.util.stream.Collectors;


public class GuestMapper {
    public static GuestDto toDto(Guest guest) {
        if (guest == null) {
            return null;
        }

        GuestDto dto = new GuestDto();
        dto.setGuestId(guest.getGuestId());
        dto.setUsername(guest.getUsername());
        dto.setEmail(guest.getEmail());
        dto.setPhone(guest.getPhone());
        dto.setCreateAt(guest.getCreateAt());
        dto.setStatus(guest.getStatus());

        // Chuyển đổi các địa chỉ
        if (guest.getAddresses() != null) {
            dto.setAddresses(guest.getAddresses().stream()
                    .map(GuestAddressMapper::toDto)
                    .collect(Collectors.toSet()));
        }

        // Chuyển đổi địa chỉ chính
        if (guest.getPrimaryAddress() != null) {
            dto.setPrimaryAddress(GuestAddressMapper.toDto(guest.getPrimaryAddress()));
        }

        return dto;
    }

    public static List<GuestDto> toDtoList(List<Guest> guests) {
        return guests.stream().map(GuestMapper::toDto).collect(Collectors.toList());
    }

}


