package com.motorepuestos.melos.data.model;

import lombok.Data;

import java.util.List;
@Data
public class EmpleadoDTO {

    private int id;
    private String userType;
    private List<RolDto> rol;
    private String nombre;
    private String email;
    private String telefono;
    private Integer cedula; // Puede ser null si no es un empleado
    private Boolean enabled; // Añadir el campo enabled


    public Boolean isEnabled() {
        return enabled;
    }
}
