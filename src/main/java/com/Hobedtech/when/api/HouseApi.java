package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.HouseInitializeDto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.service.impl.HouseServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import com.Hobedtech.when.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("<API_KEY=>"+ApiPaths.NORMALUSERAPIKEY+ApiPaths.EventCtrl.CTRL +"/v1")
@Api(value = ApiPaths.EventCtrl.CTRL)
@Slf4j
@CrossOrigin

/*
 * When Created by furkanansin on Oct, 2020
 */
public class HouseApi {
    private final HouseServiceImpl eventServiceImpl;


    public HouseApi(HouseServiceImpl eventServiceImpl) {
        this.eventServiceImpl = eventServiceImpl;
    }
    @GetMapping("/pagination")
    @ApiOperation(value = "get all", response = Events.class)
    public ResponseEntity<TPage<Events>> getAll(Pageable pageable){
        TPage<Events> data = eventServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }


    @GetMapping("house/initialize")
    public ResponseEntity<List<HouseInitializeDto>> getHouseInitialize() {
        List<HouseInitializeDto> houseInitializeDto = eventServiceImpl.getHouseInitialize();
        return ResponseEntity.ok(houseInitializeDto);
    }




}
