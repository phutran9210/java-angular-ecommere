package com.example.backend.application.service.Impl;

import com.example.backend.application.dto.guestDto.GuestCreateDTO;
import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.application.dto.guestDto.GuestMapper;
import com.example.backend.application.service.GuestService;
import com.example.backend.domain.entity.guest.Guest;
import com.example.backend.domain.entity.role.Role;
import com.example.backend.domain.entity.role.RoleName;
import com.example.backend.domain.repository.GuestRepository;

import com.example.backend.domain.repository.RoleRepository;
import com.example.backend.domain.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;


    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository,
                            PasswordEncoder passwordEncoder,
                            AuthenticationService authenticationService,
                            RoleRepository roleRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.guestRepository = guestRepository;
        this.authenticationService = authenticationService;
        this.roleRepository = roleRepository;
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

    @Transactional
    @Override
    public GuestDto createGuest(GuestCreateDTO guestCreateDTO) throws Exception {
        if (this.authenticationService.isEmailAlreadyRegistered(guestCreateDTO.getEmail())) {
            throw new Exception("Email is Exist");
        }

        if (!roleRepository.existsByRoleName(RoleName.ROLE_GUEST)) {
            throw new Exception(RoleName.ROLE_GUEST + " not found");
        }

        Role role = roleRepository.findRoleByRoleName(RoleName.ROLE_GUEST)
                .orElseThrow(() -> new Exception("Not found"));
        System.out.println(role);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Guest guest = new Guest();
        guest.setEmail(guestCreateDTO.getEmail());
        guest.setPassword(passwordEncoder.encode(guestCreateDTO.getPassword()));
        guest.setRoles(roles);
        guestRepository.save(guest);
        return GuestMapper.toDto(guest, false);
    }


    @Override
    public Boolean authenticateUser(String email, String password) throws Exception {
        boolean isExist = this.authenticationService.findEmailAndPassword(email, password);
        if (!isExist) {
            throw new Exception("Login Failed");
        }
        return true;
    }

    @Override
    public boolean findRoleByRoleName(RoleName roleName) {
        return roleRepository.existsByRoleName(roleName);
    }


}
