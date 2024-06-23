package com.motorepuestos.melos.data.model;

import lombok.Data;

import java.util.List;

@Data
public class DtoAuthRespuesta {
    private String accessToken;
    private String tokenType = "Bearer ";
    private int idUser;
    private String userType;
    private List<RolDto> rol;
    private String nombre;
    private String correo;
    private String telefono;
    private Integer cedula; // Puede ser null si no es un empleado

    public DtoAuthRespuesta(String accessToken,int idUser ,String userType, List<RolDto> rol,String nombre, String correo,String telefono, Integer cedula) {
        this.accessToken = accessToken;
        this.idUser = idUser;
        this.userType = userType;
        this.rol = rol;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.cedula = cedula;
    }


}