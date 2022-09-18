package com.example.twilio.sms.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUsuarioDto {
    private String nombre;
    private String correo;
    private String clave;
    private String cel;
}
