package com.jobapplication.example.jobapplication.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl @Autowired constructor(
        private val javaMailSender: JavaMailSender,
        @Value("\${spring.mail.username}") private val sender: String
) : EmailService {
    override fun sendSimpleMail(details: EmailDetails) {
        try {
            val mailMessage = SimpleMailMessage()
            mailMessage.setFrom(sender)
            mailMessage.setTo(details.recipient)
            mailMessage.setText(details.msgBody)
            mailMessage.setSubject(details.subject)

            javaMailSender.send(mailMessage)
        } catch (e: Exception) {
            // Handle exceptions if needed
        }
    }
}