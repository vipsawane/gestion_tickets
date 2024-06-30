package com.gestion_tickets.controller;

import com.gestion_tickets.models.MailStructure;
import com.gestion_tickets.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@Tag(name = "Gestion de Ticket", description = "API pour la gestion des tickets")
public class MailController {

    @Autowired
    private MailService mailService;

    @Operation(summary = "Envoie de notification par mail")
    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure) {
        mailService.sendMail(mail, mailStructure);
        return "Votre mail a été envoyé avec succes !";
    }
}
