package io.rongxie.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService { // handle the error, and convert the error as pair<key,msg>
    public ResponseEntity<?> MapValidationService(BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errorsMap=new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errorsMap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorsMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
