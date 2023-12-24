package com.example.backend.domain.entity.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserId implements Serializable {
    private String guestId;
    private Integer roleId;
}
