package com.example.backend.application.service.Impl;

import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.application.dto.guestDto.GuestMapper;
import com.example.backend.application.service.GuestService;
import com.example.backend.domain.entity.Guest;
import com.example.backend.domain.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
