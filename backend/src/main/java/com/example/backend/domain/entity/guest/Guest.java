package com.example.backend.domain.entity.guest;

import com.example.backend.domain.entity.role.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "guests", uniqueConstraints = {
        @UniqueConstraint(columnNames = "guest_id"),
        @UniqueConstraint(columnNames = "email"),
})

public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "guest_id", updatable = false, nullable = false)
    private String guestId;

    @Column(name = "guest_password", length = 250, nullable = false)
    private String password;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "phone", length = 15, nullable = true)
    private String phone;

    @Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private Instant createAt;

    @Column(name = "update_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updateAt;

    @Column(name = "guest_status", length = 25, columnDefinition = "VARCHAR(25) DEFAULT 'active'")
    private String status = "active";

    @Column(name = "hide", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean hide;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<GuestAddress> addresses;

    @ManyToMany
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "guest_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = Instant.now();
    }
}
