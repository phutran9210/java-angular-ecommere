package com.example.backend.domain.entity.role;

import com.example.backend.domain.entity.guest.Guest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private RoleName roleName;

    @Column(name = "role_description", nullable = true, length = 255)
    private String roleDescription;

    @ManyToMany(mappedBy = "roles")
    private Set<Guest> guests = new HashSet<>();


}
