package com.example.backend.domain.repository;

import com.example.backend.domain.entity.guest.GuestAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestAddressRepository extends JpaRepository<GuestAddress, Long> {
}
