package com.Hobedtech.when.api;

import com.Hobedtech.when.PostDto.CheckInPostDto;
import com.Hobedtech.when.dto.CheckInDto;
import com.Hobedtech.when.dto.GeneralResponse;
import com.Hobedtech.when.dto.OtherUserDto;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.service.impl.CheckInServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(ApiPaths.CheckInCtrl.CTRL +"/v1")
public class CheckInApi {
    private final CheckInServiceImpl checkInService;

    public CheckInApi(CheckInServiceImpl checkInService) {
        this.checkInService = checkInService;
    }
    @GetMapping()
    ResponseEntity<GeneralResponse> makeCheckIn(@RequestParam Double eventLat, @RequestParam Double eventLon, @RequestParam Double userLat, @RequestParam Double userLon,@RequestParam Long eventId,@RequestParam Long userId){
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        GlobalPosition eventPos = new GlobalPosition(eventLat, eventLon, 0.0);
        GlobalPosition userPos = new GlobalPosition(userLat, userLon, 0.0);
        double distance = geoCalc.calculateGeodeticCurve(reference, userPos, eventPos).getEllipsoidalDistance();
        if((int)distance > 1000){
            return new GeneralApi().sendResponse(new GeneralResponse(false,(int)distance,"Check-in yapmak istediğiniz yerde değilsiniz"));
        }
        CheckInPostDto checkInPostDto = new CheckInPostDto();
        checkInPostDto.setEvent_id(eventId);
        checkInPostDto.setUser_id(userId);
        List<User> checkInDtoList = checkInService.doCheckIn(checkInPostDto);
            return new GeneralApi().sendResponse(new GeneralResponse(true,checkInDtoList,null));
    }
}
