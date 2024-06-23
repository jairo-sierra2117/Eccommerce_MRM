package com.motorepuestos.melos.data.model;

import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {

    private String userType;
    private List<RolDto> rol;
    private String nombre;
    private String email;
    private String telefono;
}
