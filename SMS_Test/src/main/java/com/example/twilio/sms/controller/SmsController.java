package com.example.twilio.sms.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/twilio"+"/v1")
public class SmsController {

    @GetMapping("/sendSMS")
    public ResponseEntity<String> sendSMS(){

        Twilio.init("ACbb36ac7956a5d2cd63b5ccce9e251592", "9d8e3f9a6d692c9fff7fe99bc1fb96dd");

        Message.creator(new PhoneNumber("+51987082313"),
                new PhoneNumber("+18159575610"), "Hola, Hemos reestablecido tu clave").create();

        return new ResponseEntity<String>("Mensaje enviado", HttpStatus.OK);
    }

}
