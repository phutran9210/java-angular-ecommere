package com.example.backend.application.dto.guestAddressDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestAddressDto {
    private Long addressId;
    private String street;
    private String city;
    private String detailAddress;
}
