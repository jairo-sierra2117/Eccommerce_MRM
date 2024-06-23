package com.motorepuestos.melos.service;


import com.motorepuestos.melos.data.entity.Roles;
import com.motorepuestos.melos.data.entity.Usuarios;
import com.motorepuestos.melos.data.model.RolDto;
import com.motorepuestos.melos.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    public List<RolDto> determineUserRoles(Authentication authentication) {
        // Obtener el nombre de usuario autenticado desde el Authentication principal
        String username = authentication.getName();

        // Buscar los roles del usuario según el nombre de usuario en la base de datos o donde se almacene
        Usuarios usuario = usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Devolver los roles del objeto encontrado
        List<RolDto> roles = new ArrayList<>();
        for (Roles rol : usuario.getRoles()) {
            roles.add(new RolDto(rol.getIdRole(), rol.getName()));
        }

        return roles;
    }

    public RolDto convertToRolDto(Role rol) {
        RolDto rdto = new RolDto();
        rdto.setRoleNombre(rol.getRoleName());
        return rdto;
    }
}

