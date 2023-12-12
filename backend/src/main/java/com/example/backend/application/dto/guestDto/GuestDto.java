package com.example.backend.application.dto.guestDto;

import com.example.backend.application.dto.guestAddressDto.GuestAddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {
    private String guestId;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createAt;
    private String status;
    private Set<GuestAddressDto> addresses;
}
