package com.example.backend.domain.repository;

import com.example.backend.domain.entity.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {
    @Query("SELECT g FROM Guest g LEFT JOIN FETCH g.addresses WHERE g.guestId = :id")
    Optional<Guest> findByIdWithAddresses(@Param("id") String id);

    @Query("SELECT g FROM Guest g WHERE g.guestId = :id")
    Optional<Guest> findUserById(@Param("id") String id);
}
