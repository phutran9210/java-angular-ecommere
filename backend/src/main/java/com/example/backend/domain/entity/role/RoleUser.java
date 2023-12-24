package com.example.backend.domain.entity.role;

import com.example.backend.domain.entity.guest.Guest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_user")
@IdClass(RoleUserId.class)
public class RoleUser {
    @Id
    @Column(name = "guest_id")
    private String guestId;

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @ManyToOne
    @JoinColumn(name = "guest_id", insertable = false, updatable = false)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;
}


