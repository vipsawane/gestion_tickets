package com.gestion_tickets.service;

import com.gestion_tickets.models.Apprenant;
import com.gestion_tickets.models.Formateur;
import com.gestion_tickets.models.MailStructure;
import com.gestion_tickets.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender; // Injection de JavaMailSender pour envoyer des emails

    @Value("${spring.mail.username}")
    private String fromMail; // Récupère l'adresse email de l'expéditeur à partir des propriétés de configuration

    // Méthode pour envoyer un email
    public void sendMail(String mail, MailStructure mailStructure) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); // Crée un nouvel email
        simpleMailMessage.setFrom(fromMail); // Définit l'expéditeur
        simpleMailMessage.setSubject(mailStructure.getSubject()); // Définit le sujet
        simpleMailMessage.setText(mailStructure.getMessage()); // Définit le corps du message
        simpleMailMessage.setTo(mail); // Définit le destinataire
        mailSender.send(simpleMailMessage); // Envoie l'email
    }

    // Méthode pour envoyer une notification de ticket au formateur
    public void EnvoieNotificationTicketFormateur(Ticket ticket, Formateur formateur) {
        String subject = "Nouveau Ticket Soumis : " + ticket.getTitre();
        String message = "Un nouveau ticket a été soumis par un apprenant. \n"
                + "ID du Ticket : " + ticket.getId() + "\n"
                + "Titre du Ticket: " + ticket.getTitre() + "\n"
                + "Description : " + ticket.getDescription();

        MailStructure mailStructure = new MailStructure(subject, message);
        sendMail(formateur.getEmail(), mailStructure);
    }
    // Méthode pour envoyer une notification de réponse à l'apprenant
    public void EnvoieNotificationReponseApprenant(Ticket ticket, Apprenant apprenant) {
        String subject = "Réponse à Votre Ticket : " + ticket.getTitre();
        String message = "Une réponse a été soumise à votre ticket.\n"
                + "ID du Ticket : " + ticket.getId() + "\n"
                + "Titre du Ticket: " + ticket.getTitre() + "\n"
                + "Description : " + ticket.getDescription();

        MailStructure mailStructure = new MailStructure(subject, message);
        sendMail(apprenant.getEmail(), mailStructure);
    }
}
