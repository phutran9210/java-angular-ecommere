package com.example.backend.application.service;

import com.example.backend.application.dto.guestDto.GuestCreateDTO;
import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.domain.entity.guest.Guest;
import com.example.backend.domain.entity.role.Role;
import com.example.backend.domain.entity.role.RoleName;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<GuestDto> getAllGuest();

    Optional<GuestDto> findUserByIdWithAddress(String id);

    Optional<GuestDto> findUserById(String id);

    GuestDto createGuest(GuestCreateDTO guestCreateDTO) throws Exception;

    Boolean authenticateUser(String email, String password) throws Exception;

    boolean findRoleByRoleName(RoleName roleName);
}
