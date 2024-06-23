package com.motorepuestos.melos.data.model;

import lombok.Data;


@Data
public class RolDto {
    private Long idRole;
    private String roleNombre;

    public RolDto(){}
    public RolDto(Long idRole, String roleNombre) {
        this.idRole = idRole;
        this.roleNombre = roleNombre;
    }
}