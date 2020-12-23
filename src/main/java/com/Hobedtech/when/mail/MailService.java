/*
package com.Hobedtech.when.mail;

*/
/**
 * When Created by furkanansin on Oct, 2020
 *//*

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.Hobedtech.when.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


*/
/**
 *
 * @author Mukuljaiswal
 *
 *//*

@Service
public class MailService {

    */
/*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     *//*

    private JavaMailSender javaMailSender;

    */
/**
     *
     * @param javaMailSender
     *//*

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    */
/**
     * This function is used to send mail without attachment.

     * @throws MailException
     *//*


    public void sendEmail(String email,String link,String subject,String text) throws MailException {

        System.out.println(link);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(text + "  " + link);
        javaMailSender.send(mail);
    }



}
*/
