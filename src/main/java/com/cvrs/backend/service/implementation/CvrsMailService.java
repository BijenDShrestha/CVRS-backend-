package com.cvrs.backend.service.implementation;

import com.cvrs.backend.dto.CitizenDto;
import com.cvrs.backend.entity.VaccineEntity;
import com.cvrs.backend.exception.NotFoundException;
import com.cvrs.backend.repository.VaccineRepository;
import com.cvrs.backend.util.CvrsUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class CvrsMailService {
    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;
    private VaccineRepository vaccineRepository;

    @Autowired
    public CvrsMailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, VaccineRepository vaccineRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.vaccineRepository = vaccineRepository;
    }

    //prototype
//    @Scheduled(cron="*/15 * * * * ?")
//    public void SendMail() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom(CvrsUtils.USERNAME);
//        simpleMailMessage.setTo("bijen_shrestha09@yahoo.com");
//        simpleMailMessage.setSubject("spring boot");
//        simpleMailMessage.setText("sent from spring boot");
//
//        javaMailSender.send(simpleMailMessage);
//
//    }

    public boolean SendMail(CitizenDto citizenDto, String body, String subject) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(citizenDto.getEmail());
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setFrom(CvrsUtils.USERNAME);

        try {
            javaMailSender.send(mimeMessage);
        }catch (MailException me){
            log.error("Error Sending Mail", me);
            throw new NotFoundException("Error Sending Mail");
        }

        return true;
    }


    @Async
    public boolean sendNotificationToCitizen(CitizenDto citizenDto) throws MessagingException{
        if(citizenDto.getEmail() == null){
            throw new NotFoundException("Email Address not Found!");
        }
        Context context = new Context();
        context.setVariable("title", "Dear " + citizenDto.getFirstName() + citizenDto.getLastName() + ",");
        VaccineEntity vaccineEntity = vaccineRepository.findById(citizenDto.getVaccineId()).get();
        //Todo: check the units of vaccine and distribute it evenly for date schedule
        context.setVariable("body", "Your vaccination Date is: " + vaccineEntity.getScheduleFor().toString());
        String body = templateEngine.process("notification_template", context);

        return SendMail(citizenDto, body, "Vaccine Notification");
    }


    //For testing
//    public boolean SendMail(String body, String subject) throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//        helper.setTo("bijen_shrestha09@yahoo.com");
//        helper.setSubject(subject);
//        helper.setText(body, true);
//        helper.setFrom(CvrsUtils.USERNAME);
//
//        try {
//            javaMailSender.send(mimeMessage);
//        }catch (MailException me){
//            log.error("Error Sending Mail", me);
//            throw new NotFoundException("Error Sending Mail");
//        }
//
//        return true;
//    }
//
//    @Async
//    public boolean sendNotificationToCitizen(String vaccineDate) throws MessagingException{
//        Context context = new Context();
//        context.setVariable("title", "Dear Bijen Shrestha,");
//        context.setVariable("body", "Your vaccination Date is: " + vaccineDate);
//        String body = templateEngine.process("notification_template", context);
//
//        return SendMail(body, "Vaccine Notification");
//    }

}
