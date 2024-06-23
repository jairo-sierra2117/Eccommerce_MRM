package com.motorepuestos.melos.data.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "id_empleado")
public class Empleado extends Usuarios {
    @Column(name = "cedula", nullable = false)
    private Integer cedula;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled; // Campo para deshabilitar el empleado

    public Boolean isEnabled() {
        return enabled;
    }
}
