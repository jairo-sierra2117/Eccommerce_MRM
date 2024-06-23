package com.motorepuestos.melos.controller;

import com.motorepuestos.melos.data.entity.Cliente;
import com.motorepuestos.melos.data.entity.Empleado;
import com.motorepuestos.melos.data.model.ClienteDTO;
import com.motorepuestos.melos.data.model.EmpleadoDTO;
import com.motorepuestos.melos.data.model.RolDto;
import com.motorepuestos.melos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.motorepuestos.melos.data.model.ResetPasswordDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * este endpoint se utiliza para cambiar de clave
     * re valida el email, clave-actual y se recibe la nueva clave
     */
    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        userService.reestablecerContrasena(resetPasswordDTO.getEmail(),
                resetPasswordDTO.getActualPassword(),
                resetPasswordDTO.getNewPassword());
    }

    /**
     * este endpoint se utiliza para enviar una nueva clave
     * esta se envia al email proporcionado, este email se verifica que exista en la BD
     */
    @PostMapping("/email-temp-password")
    public ResponseEntity<Map<String, String>> emailTempPassword(@RequestParam String email) {
        Map<String, String> response = new HashMap<>();
        try {
            userService.emailContrasena(email);
            response.put("message", "La nueva contraseña ha sido enviada a tu correo electrónico.");
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            response.put("message", "No existe el usuario con el correo electrónico proporcionado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "Ocurrió un error al intentar enviar el correo electrónico.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Manejo de excepciones específicas
    @ExceptionHandler({IllegalArgumentException.class, UsernameNotFoundException.class})
    public ResponseEntity<String> handleExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Endpoint para editar empleados
    @PutMapping("/UpdateEmpleados/{id}")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
        Empleado updatedEmpleado = userService.updateEmpleado(id, empleadoDTO);
        EmpleadoDTO updatedEmpleadoDTO = convertToEmpleadoDTO(updatedEmpleado);
        return ResponseEntity.ok(updatedEmpleadoDTO);
    }

    // Endpoint para deshabilitar empleados
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Void> disableEmpleado(@PathVariable Long id) {
        userService.disableEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para habilitar empleados
    @PutMapping("/empleados/{id}/enable")
    public ResponseEntity<Void> enableEmpleado(@PathVariable Long id) {
        userService.enableEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Metodos para listar los clientes y empleados registrados
     * @return
     */
    @GetMapping("/clientes")
    public List<ClienteDTO> getAllClientes() {
        return userService.getAllClientes().stream()
                .map(this::convertToClienteDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/empleados")
    public List<EmpleadoDTO> getAllEmpleados() {
        return userService.getAllEmpleados().stream()
                .map(this::convertToEmpleadoDTO)
                .collect(Collectors.toList());
    }


    private ClienteDTO convertToClienteDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre(cliente.getNombre());
        dto.setEmail(cliente.getUsername());
        dto.setTelefono(cliente.getTelefono());
        RolDto rdto = new RolDto();
        dto.setRol(cliente.getRoles().stream().map(role -> {
            rdto.setRoleNombre(role.getName());
            rdto.setIdRole(role.getIdRole());
            return rdto;
        }).collect(Collectors.toList()));
        return dto;
    }


    private EmpleadoDTO convertToEmpleadoDTO(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getIdUsuario());
        dto.setNombre(empleado.getNombre());
        dto.setEmail(empleado.getUsername());
        dto.setTelefono(empleado.getTelefono());
        dto.setCedula(empleado.getCedula());
        dto.setEnabled(empleado.isEnabled());  // Asignar el campo enabled
        RolDto rdto = new RolDto();
        dto.setRol(empleado.getRoles().stream().map(role -> {
            rdto.setRoleNombre(role.getName());
            rdto.setIdRole(role.getIdRole());
            return rdto;
        }).collect(Collectors.toList()));
        return dto;
    }

}

