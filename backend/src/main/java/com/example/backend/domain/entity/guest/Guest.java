package com.example.backend.domain.entity.guest;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "guests", uniqueConstraints = {
        @UniqueConstraint(columnNames = "guest_id"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "guest_username")

})

public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "guest_id", updatable = false, nullable = false)
    private String guestId;

    @Column(name = "guest_username", length = 50, nullable = false)
    private String username;

    @Column(name = "guest_password", length = 250, nullable = false)
    private String password;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "phone", length = 15, nullable = false)
    private String phone;

    @Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private LocalDateTime updateAt;

    @Column(name = "guest_status", length = 25, columnDefinition = "VARCHAR(25) DEFAULT 'active'")
    private String status;

    @Column(name = "hide", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean hide;


    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<GuestAddress> addresses;

    @OneToOne
    @JoinColumn(name = "primary_address_id", referencedColumnName = "address_id")
    private GuestAddress primaryAddress;

}
