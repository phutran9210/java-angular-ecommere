package com.example.backend.application.service.Impl;

import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.application.dto.guestDto.GuestMapper;
import com.example.backend.application.service.GuestService;
import com.example.backend.domain.entity.guest.Guest;
import com.example.backend.domain.repository.GuestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }


    @Override
    public List<GuestDto> getAllGuest() {
        List<Guest> guests = guestRepository.findAll();

        return GuestMapper.toDtoList(guests);
    }

    @Transactional()
    public Optional<GuestDto> findUserByIdWithAddresses(String id) {
        Optional<Guest> guest = guestRepository.findByIdWithAddresses(id);
        return guest.map(GuestMapper::toDto);
    }

    @Transactional()
    public Optional<GuestDto> findUserByIdWithPrimaryAddress(String id) {
        Optional<Guest> guest = guestRepository.findByIdWithPrimaryAddress(id);
        return guest.map(GuestMapper::toDto);
    }
}
