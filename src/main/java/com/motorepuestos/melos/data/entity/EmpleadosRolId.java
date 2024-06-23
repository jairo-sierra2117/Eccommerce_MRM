package com.motorepuestos.melos.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class EmpleadosRolId implements Serializable {

    @Column(name = "empleado_id")
    private Long empleadoId;

    @Column(name = "rol_id")
    private Long rolId;

    // Getters y Setters...
}
