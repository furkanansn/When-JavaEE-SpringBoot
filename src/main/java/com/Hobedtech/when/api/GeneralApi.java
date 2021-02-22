package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.GeneralResponse;
import org.springframework.http.ResponseEntity;

public class GeneralApi {
    ResponseEntity<GeneralResponse> sendResponse(GeneralResponse generalResponse){
        return ResponseEntity.ok(generalResponse);
    }
}
