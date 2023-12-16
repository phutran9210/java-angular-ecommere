package com.example.backend.application.dto.guestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestCreateDTO {
    @NotBlank
    @Email(message = "Email not valid")
    private String email;

    @NotBlank
    @Size(min = 8, max = 32, message = "Password not valid")
    private String password;
}
