package com.example.twilio.sms.services;

import com.example.twilio.sms.dtos.CreateUsuarioDto;
import com.example.twilio.sms.dtos.UsuarioDto;
import com.example.twilio.sms.exceptions.TwilioException;

public interface UsuarioService {
    UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TwilioException;
    UsuarioDto getUsuarioByCorreoandClave(String correoUsuario, String claveUsuario) throws TwilioException;
    void updateClave(String correoUsuario, String claveUsuario) throws TwilioException;
    void updateClaveBySMS(String celUsuario, String claveSMSUsuario) throws TwilioException;
}
