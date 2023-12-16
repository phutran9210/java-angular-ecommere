package com.example.backend.application.service.Impl;

import com.example.backend.application.dto.guestDto.GuestCreateDTO;
import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.application.dto.guestDto.GuestMapper;
import com.example.backend.application.service.GuestService;
import com.example.backend.domain.entity.guest.Guest;
import com.example.backend.domain.repository.GuestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.guestRepository = guestRepository;
    }

    @Transactional
    @Override
    public List<GuestDto> getAllGuest() {
        List<Guest> guests = guestRepository.findAll();
        System.out.println(guests);
        return GuestMapper.toDtoList(guests, true);
    }

    @Transactional
    @Override
    public Optional<GuestDto> findUserByIdWithAddress(String id) {
        Optional<Guest> guest = guestRepository.findByIdWithAddresses(id);
        return guest.map(g -> GuestMapper.toDto(g, true));
    }

    @Transactional
    @Override
    public Optional<GuestDto> findUserById(String id) {
        Optional<Guest> guest = guestRepository.findUserById(id);
        return guest.map(g -> GuestMapper.toDto(g, false));
    }

    @Override
    public GuestDto createGuest(GuestCreateDTO guestCreateDTO) throws Exception {
        if (this.isEmailAlreadyRegistered(guestCreateDTO.getEmail())) {
            throw new Exception("Email is Exist");
        }
        Guest guest = new Guest();
        guest.setEmail(guestCreateDTO.getEmail());
        guest.setPassword(passwordEncoder.encode(guestCreateDTO.getPassword()));
        guestRepository.save(guest);
        return GuestMapper.toDto(guest, false);
    }

    @Override
    public Boolean isEmailAlreadyRegistered(String email) {
        return guestRepository.findByEmail(email).isPresent();
    }
}
