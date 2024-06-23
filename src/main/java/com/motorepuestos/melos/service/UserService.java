package com.motorepuestos.melos.service;

import com.motorepuestos.melos.data.entity.Cliente;
import com.motorepuestos.melos.data.entity.Empleado;
import com.motorepuestos.melos.data.entity.Usuarios;
import com.motorepuestos.melos.data.model.EmpleadoDTO;
import com.motorepuestos.melos.repository.ClienteRepository;
import com.motorepuestos.melos.repository.EmpleadoRepository;
import com.motorepuestos.melos.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private IUsuariosRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Constantes para mensajes de éxito y error
    private static final String SUCCESS_EMAIL_SENT = "Se ha enviado la nueva contraseña al correo electrónico proporcionado.";
    private static final String ERROR_USER_NOT_FOUND = "No existe el usuario con el correo electrónico proporcionado.";


    /**
     * Reestablecer contraseña de cualquier usuario.
     *
     * @param email      email del usuario al que se le asignara la nueva
     *                   contraseña.
     * @param actualContraseña actual contraseña del usuario.
     * @param nuevaContraseña nueva contraseña a asignar.
     * @return boolean Refleja si la operacion tuvo exito.
     */
    public void reestablecerContrasena(String email, String actualContraseña, String nuevaContraseña) {

        Usuarios user = userRepository.findByUsername(email).orElseThrow(() ->
                new UsernameNotFoundException("No existe el usuario con el correo electrónico proporcionado")
        );

        if (nuevaContraseña.equals(user.getUsername())){
            throw new IllegalArgumentException("La nueva contraseña no puede ser igual al correo electrónico");
        }

        if (!bCryptPasswordEncoder.matches(actualContraseña, user.getPassword())) {
            throw new IllegalArgumentException("Contraseña actual incorrecta");
        }

        user.setPassword(bCryptPasswordEncoder.encode(nuevaContraseña));
        userRepository.save(user);
    }

    /**
     * Establece contraseña temporal aleatoria al usuario y la envia a traves de
     * correo electronico.
     *
     * @param email email del usuario al que se le asignara la contraseña.
     *
     */
    public void emailContrasena(String email) {

        Usuarios user = userRepository.findByUsername(email).orElseThrow(() ->
                new UsernameNotFoundException("No existe el usuario con el correo electrónico proporcionado")
        );

        String tempcontrasena = generarContrasena(10);
        user.setPassword(bCryptPasswordEncoder.encode(tempcontrasena));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario");
        }

        String subject = "<p style=\"font-size: 28px; font-weight: bold;\">Contraseña de Recuperación </p>";
        String mensaje = "<p style=\"font-size: 18px;\">Su nueva contraseña de recuperacion es:</p>" +
                "<p style=\"font-size: 24px; font-weight: bold;\">" + tempcontrasena + "</p>";

        emailService.sendListEmail(email, subject, mensaje);
    }

    /**
     * Genera una contraseña temporal aleatoria.
     *
     * @param length longitud de la contraseña generada.
     * @return contraseña generada.
     */
    private String generarContrasena(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

    // Método para obtener el mensaje de éxito al enviar el correo
    public String getSuccessMessage() {
        return SUCCESS_EMAIL_SENT;
    }


    @Transactional
    public Empleado updateEmpleado(Long id, EmpleadoDTO empleadoDTO) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            empleado.setNombre(empleadoDTO.getNombre());
            empleado.setUsername(empleadoDTO.getEmail());
            empleado.setTelefono(empleadoDTO.getTelefono());
            empleado.setCedula(empleadoDTO.getCedula());
            // Actualizar otros campos según sea necesario

            return empleadoRepository.save(empleado);
        } else {
            throw new EntityNotFoundException("Empleado no encontrado con id: " + id);
        }
    }

    @Transactional
    public void disableEmpleado(Long id) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            empleado.setEnabled(false);
            empleadoRepository.save(empleado);
        } else {
            throw new EntityNotFoundException("Empleado no encontrado con id: " + id);
        }
    }

    @Transactional
    public void enableEmpleado(Long id) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);
        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            empleado.setEnabled(true);
            empleadoRepository.save(empleado);
        } else {
            throw new EntityNotFoundException("Empleado no encontrado con id: " + id);
        }
    }


    /**
     * Metodos para listar los dos tipos de usuarios con sus respectivos datos
     * @return
     */
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }




}
