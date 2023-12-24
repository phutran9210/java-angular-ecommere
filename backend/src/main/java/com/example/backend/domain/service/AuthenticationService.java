package com.example.backend.domain.service;

import com.example.backend.domain.entity.guest.Guest;
import com.example.backend.domain.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isEmailAlreadyRegistered(String email) {
        return guestRepository.findByEmail(email).isPresent();
    }

    public boolean findEmailAndPassword(String email, String password) {
        Optional<Guest> optionalGuest = guestRepository.findByEmail(email);
        if (optionalGuest.isPresent()) {
            Guest guest = optionalGuest.get();
            return passwordEncoder.matches(password, guest.getPassword());
        }
        return false;
    }


    public boolean passwordDecrypt(String encryptedPassword, String password) {
        return passwordEncoder.matches(password, encryptedPassword);
    }
}
