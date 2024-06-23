package com.motorepuestos.melos.controller;

import com.motorepuestos.melos.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam String emailTo,
            @RequestParam String mensaje,
            @RequestParam String mensaje2
    ) {
        emailService.sendListEmail(emailTo, mensaje, mensaje2);
        return "Email sent successfully";
    }
}
