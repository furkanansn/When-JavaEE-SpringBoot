
package com.Hobedtech.when.mail;


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



@Service
public class MailService {


    private final JavaMailSender javaMailSender;


    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }



    public boolean sendEmail(String email,String link,String subject,String text) throws MailException {
            boolean isEmailReal;
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setSubject(subject);
            mail.setText(text + "  " + link);
            javaMailSender.send(mail);
            isEmailReal= true;
        }catch (MailException mailException){
            isEmailReal= false;
        }
        return isEmailReal;
    }



}

