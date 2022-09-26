package com.example.twilio.sms.controller;

import com.example.twilio.sms.dtos.CreateUsuarioDto;
import com.example.twilio.sms.dtos.UsuarioDto;
import com.example.twilio.sms.exceptions.TwilioException;
import com.example.twilio.sms.responses.TwilioResponse;
import com.example.twilio.sms.services.UsuarioService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping(path = "/twilio"+"/v1")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/usuarios")
    public TwilioResponse<UsuarioDto> createUsuario(@RequestBody CreateUsuarioDto createUsuarioDto) throws TwilioException{
        Twilio.init("ACbb36ac7956a5d2cd63b5ccce9e251592", "c55ad6ae7d1f04e8baeb1229a0e7c2ec");
        ValidationRequest validationRequest = ValidationRequest.creator(
                new PhoneNumber("+51" + createUsuarioDto.getCel()))
                .setFriendlyName(createUsuarioDto.getNombre())
                .create();

        return new TwilioResponse<>("Success", String.valueOf(HttpStatus.OK), validationRequest.getFriendlyName(),
                usuarioService.createUsuario(createUsuarioDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/usuarios/{celUsuario}")
    public void updateClaveBySMS(@PathVariable String celUsuario) throws TwilioException{
        Random rand = new Random();
        int code = 10000000 + rand.nextInt(100000000);

        Twilio.init("ACbb36ac7956a5d2cd63b5ccce9e251592", "c55ad6ae7d1f04e8baeb1229a0e7c2ec");
        Message.creator(new PhoneNumber("+51" + "924580494"),
                new PhoneNumber("+18159575610"), "Ahora tu clave es: " + code).create();



        //usuarioService.updateClaveBySMS(celUsuario, String.valueOf(code));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/usuarios/{correoUsuario}/{claveUsuario}")
    public void updateClave(@PathVariable String correoUsuario, @PathVariable String claveUsuario) throws TwilioException{
        usuarioService.updateClave(correoUsuario, claveUsuario);
    }
}
