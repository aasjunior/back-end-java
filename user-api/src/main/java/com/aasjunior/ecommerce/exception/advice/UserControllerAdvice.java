package com.aasjunior.ecommerce.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aasjunior.ecommerce.shoppingclient.dto.ErrorDTO;
import com.aasjunior.ecommerce.shoppingclient.exception.UserNotFoundException;

@ControllerAdvice(basePackages =  "com.aasjunior.ecommerce.controller")
public class UserControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFound(UserNotFoundException e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Usuário não encontrado");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public  ErrorDTO handleParamMissing(MissingServletRequestParameterException e){
        String parameterName = e.getParameterName();
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDTO.setMessage("O parâmetro '" + parameterName + "' é obrigatório");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }
}
