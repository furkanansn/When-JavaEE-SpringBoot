package com.Hobedtech.when.api;

import com.Hobedtech.when.dto.GeneralResponse;
import com.Hobedtech.when.dto.SearchDto;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.service.impl.SearchUserServiceImpl;
import com.Hobedtech.when.service.impl.SearchVenueServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * When Created by furkanansin on Oct, 2020
 */
@RestController
@RequestMapping(ApiPaths.SearchCtrl.CTRL +"/v1")
@Api(value = ApiPaths.SearchCtrl.CTRL)
@Slf4j
@CrossOrigin
public class SearchApi {
    private final SearchUserServiceImpl searchServiceImpl;
    private final SearchVenueServiceImpl searchVenueServiceImpl;
    private final ModelMapper modelMapper;

    public SearchApi(SearchUserServiceImpl searchServiceImpl, SearchVenueServiceImpl searchVenueServiceImpl, ModelMapper modelMapper) {
        this.searchServiceImpl = searchServiceImpl;
        this.searchVenueServiceImpl = searchVenueServiceImpl;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/user")
    public ResponseEntity<GeneralResponse> getSearch(@RequestParam String userName){
        List<User> searchUserRequest = searchServiceImpl.searchByUserName(userName);
        List<UsrVp> searchVenueRequest = searchVenueServiceImpl.sarchByVenueName(userName);

        if(!searchUserRequest.isEmpty() || !searchVenueRequest.isEmpty()){
            List<Object> list = Stream.concat(searchUserRequest.stream(),searchVenueRequest.stream())
                    .collect(Collectors.toList());
            return new GeneralApi().sendResponse(new GeneralResponse(true,modelMapper.map(list, SearchDto[].class),null));

        }
        if(!searchUserRequest.isEmpty()){
            return new GeneralApi().sendResponse(new GeneralResponse(true,modelMapper.map(searchUserRequest,SearchDto[].class),null));

        }
        if(!searchVenueRequest.isEmpty()){
            return new GeneralApi().sendResponse(new GeneralResponse(true,modelMapper.map(searchVenueRequest,SearchDto[].class),null));

        }
        else {
            return new GeneralApi().sendResponse(new GeneralResponse(false,null,"Böyle bir kişi ya da mekan bulunamadı"));

        }



    }


}
