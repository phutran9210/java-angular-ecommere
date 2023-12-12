package com.example.backend.domain.repository;

import com.example.backend.domain.entity.guest.GuestAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestAddressRepository extends JpaRepository<GuestAddress, Long> {
}
