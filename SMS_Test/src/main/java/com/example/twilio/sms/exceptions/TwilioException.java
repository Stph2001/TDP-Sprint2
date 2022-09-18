package com.example.twilio.sms.exceptions;

import com.example.twilio.sms.dtos.ErrorDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TwilioException extends Exception{
    private final String code;
    private final int responseCode;
    private final List<ErrorDto> errorDtoList = new ArrayList<>();

    public TwilioException(String code, int responseCode, String message){
        super(message);
        this.code = code;
        this.responseCode = responseCode;
    }

    public TwilioException(String code, int responseCode, String message, List<ErrorDto> errorDtoList){
        super(message);
        this.code = code;
        this.responseCode = responseCode;
        this.errorDtoList.addAll(errorDtoList);
    }
}
