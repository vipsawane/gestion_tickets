package com.gestion_tickets.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailStructure {
    private String subject;
    private String message;

    // Constructeur avec subject et message
    public MailStructure(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }
}
