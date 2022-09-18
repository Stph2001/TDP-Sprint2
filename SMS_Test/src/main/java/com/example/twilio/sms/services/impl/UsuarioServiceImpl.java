package com.example.twilio.sms.services.impl;

import com.example.twilio.sms.dtos.CreateUsuarioDto;
import com.example.twilio.sms.dtos.UsuarioDto;
import com.example.twilio.sms.entities.Usuario;
import com.example.twilio.sms.exceptions.InternalServerErrorException;
import com.example.twilio.sms.exceptions.NotFoundException;
import com.example.twilio.sms.exceptions.TwilioException;
import com.example.twilio.sms.repositories.UsuarioRepository;
import com.example.twilio.sms.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TwilioException {
        Usuario usuario = new Usuario();
        usuario.setNombre(createUsuarioDto.getNombre());
        usuario.setCorreo(createUsuarioDto.getCorreo());
        usuario.setClave(createUsuarioDto.getClave());
        usuario.setCel(createUsuarioDto.getCel());

        try {
            usuario=usuarioRepository.save(usuario);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
        }
        return modelMapper.map(getUsuarioEntity(usuario.getId()), UsuarioDto.class);
    }

    @Override
    public UsuarioDto getUsuarioByCorreoandClave(String correoUsuario, String claveUsuario) throws TwilioException {
        return null;
    }

    @Override
    public void updateClave(String correoUsuario, String claveUsuario) throws TwilioException {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new NotFoundException("NOT FOUND-404","USUARIO_NOTFOUND-404"));
        usuario.setClave(claveUsuario);

        try {
            usuario = usuarioRepository.save(usuario);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public void updateClaveBySMS(String celUsuario, String claveSMSUsuario) throws TwilioException {
        Usuario usuario = usuarioRepository.findByCel(celUsuario)
                .orElseThrow(() -> new NotFoundException("NOT FOUND-404","USUARIO_NOTFOUND-404"));
        usuario.setClave(claveSMSUsuario);

        try {
            usuario = usuarioRepository.save(usuario);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
    }

    private Usuario getUsuarioEntity(Long usuarioId) throws TwilioException{
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("NOT FOUND-404","USUARIO_NOTFOUND-404"));
    }
}
