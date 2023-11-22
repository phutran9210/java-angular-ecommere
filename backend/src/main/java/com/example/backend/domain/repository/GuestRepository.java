package com.example.backend.domain.repository;

import com.example.backend.application.dto.guestDto.GuestDto;
import com.example.backend.domain.entity.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, String> {
    @Query("SELECT g FROM Guest g LEFT JOIN FETCH g.addresses WHERE g.guestId = :id")
    Optional<Guest> findByIdWithAddresses(@Param("id") String id);

    @Query("SELECT g FROM Guest g LEFT JOIN FETCH g.primaryAddress WHERE g.guestId = :id")
    Optional<Guest> findByIdWithPrimaryAddress(@Param("id") String id);
}
