package com.motorepuestos.melos.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Usuarios {
    // Puedes agregar campos especÃ­ficos para clientes aquÃ­ si los hay
}
