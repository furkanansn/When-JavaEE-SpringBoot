package com.Hobedtech.when.api;

import com.Hobedtech.when.service.impl.NumberOfViewsServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * when Created by furkanansin on Dec, 2020
 */
@RestController
@RequestMapping("/v1/NumberOfViews")
@Api()
@Slf4j
@CrossOrigin
public class NumberOfViewsApi {
    private final NumberOfViewsServiceImpl numberOfViewsService;

    public NumberOfViewsApi(NumberOfViewsServiceImpl numberOfViewsService) {
        this.numberOfViewsService = numberOfViewsService;
    }

    @PostMapping
    public ResponseEntity<Boolean> addNumberOfViews(@RequestBody Long id){
        numberOfViewsService.numberOfViews(id);
        return ResponseEntity.ok(true);
    }
}
