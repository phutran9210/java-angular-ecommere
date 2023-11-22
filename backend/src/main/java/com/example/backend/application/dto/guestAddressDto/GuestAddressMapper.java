package com.example.backend.application.dto.guestAddressDto;

import com.example.backend.domain.entity.guest.GuestAddress;

public class GuestAddressMapper {
    public static GuestAddressDto toDto(GuestAddress address) {
        if (address == null) {
            return null;
        }

        GuestAddressDto addressDto = new GuestAddressDto();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setDetailAddress(address.getDetailAddress());
      
        return addressDto;
    }
}
