package com.motorepuestos.melos.data.model;

import lombok.Data;

@Data
public class DtoLogin {
    private String username;//este es el email pero con otro nombre :)
    private String password;
}