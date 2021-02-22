package com.Hobedtech.when.api;

import com.Hobedtech.when.config.TokenProvider;
import com.Hobedtech.when.dto.*;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.repository.UserVipRepository;
import com.Hobedtech.when.service.impl.UserVipServiceImpl;
import com.Hobedtech.when.util.ApiPaths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * when Created by furkanansin on Dec, 2020
 */
@RestController
@RequestMapping(ApiPaths.VenueCtrl.CTRL + "/v1")
@Slf4j
@CrossOrigin
public class VenueSpecialApi {
    private final UserVipServiceImpl userVipService;
    private final UserVipRepository userVipRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    public VenueSpecialApi(UserVipServiceImpl userVipService, UserVipRepository userVipRepository) {
        this.userVipService = userVipService;
        this.userVipRepository = userVipRepository;
    }


    @PostMapping("/event")
    public ResponseEntity<Events> addEvent(@RequestBody EventDto events) throws IOException {
        return ResponseEntity.ok(userVipService.addEvent(events));
    }

    @DeleteMapping("/event")
    public ResponseEntity<Boolean> deleteEvent(@RequestParam Long id){
        return ResponseEntity.ok(userVipService.deleteEvents(id));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserVipDto> getUserVipProfile(@RequestParam Long id){
        UserVipDto userVipDto = userVipService.getById(id);
        Integer count = userVipService.follwersCount(id);
        userVipDto.setFollowersCount(count);
        return ResponseEntity.ok(userVipDto);
    }

    @GetMapping("/profile/followers")
    public ResponseEntity<List<FriendDto>> getUserVipProfileFollowers(@RequestParam Long id){
        return ResponseEntity.ok(userVipService.getByIdFollowers(id));
    }

    @GetMapping("/event")
    public ResponseEntity<List<Events>> getEventsByVenueId(Long id){
        return ResponseEntity.ok(userVipService.getEvents(id));
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody  UsrVp usrVp){
        Long res = userVipService.register(usrVp);
        if(res == 0L){
            return new GeneralApi().sendResponse(new GeneralResponse(false,null,"Bu E-posta adresi veya kullanıcı adı ile kayıtlı bir kullanıcı bulunmaktadır"));

        }
        return new GeneralApi().sendResponse(new GeneralResponse(true,res,null));
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
    Optional<UsrVp> userVip = Optional.ofNullable(userVipRepository.getByEmail(loginRequest.getEmail()));

    if(!userVip.isPresent()){
        return new GeneralApi().sendResponse(new GeneralResponse(false,null,"Yanlış E-posta ya da parola girdiniz"));

    }
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return new GeneralApi().sendResponse(new GeneralResponse(true,new AuthToken(userVip.get().getId(),token),null));
    }

}
