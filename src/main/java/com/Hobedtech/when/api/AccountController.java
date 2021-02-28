
package com.Hobedtech.when.api;


import com.Hobedtech.when.config.TokenProvider;
import com.Hobedtech.when.dto.AuthToken;
import com.Hobedtech.when.dto.GeneralResponse;
import com.Hobedtech.when.dto.LoginRequest;
import com.Hobedtech.when.dto.RegistrationRequest;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.repository.UserRepository;
import com.Hobedtech.when.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

   /* @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<GeneralResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        Object response = userService.register(registrationRequest);

        if(response !="" && response != null){
            return new GeneralApi().sendResponse(new GeneralResponse(false,null,response.toString()));

        }
        return new GeneralApi().sendResponse(new GeneralResponse(true,response,null));
    }


    @RequestMapping(value = "/send-again", method = RequestMethod.GET)
    public ResponseEntity<GeneralResponse> sendAgain(@RequestParam Long id){
        User user = userService.sendAgain(id);
        return new GeneralApi().sendResponse(new GeneralResponse(false,"Aktivasyon linki tekrardan gönderildi\"",null));


    }
    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    public ResponseEntity<Boolean> validate(@RequestParam String token, @RequestParam Long id){
        return ResponseEntity.ok( userService.validate(id,token));
    }
    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public ResponseEntity<String> sendAgain(@RequestParam String email){
        User user = userService.forgotPassword(email);
        if(user.getEmail().isEmpty()){return ResponseEntity.ok("Böyle bir hesap bulunamadı");}
        else return ResponseEntity.ok("Parola sıfırlama linki gönderildi");
    }

    @RequestMapping(value = "/change-password",method = RequestMethod.GET)
    public ResponseEntity<String> changePassword(@RequestParam Long id){
        return ResponseEntity.ok(userService.changePassword(id));
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        Optional<User> userEmailAndPassCheck = Optional.ofNullable(userRepository.findByEmail(loginRequest.getEmail()));
        if(userEmailAndPassCheck.isPresent()){
            if(!bcryptEncoder.matches(loginRequest.getPassword(),userEmailAndPassCheck.get().getPassword())){
                return new GeneralApi().sendResponse(new GeneralResponse(false,null,"Yanlış E-posta ya da parola girdiniz"));
            }
            if(!userEmailAndPassCheck.get().getActive()){
                return new GeneralApi().sendResponse(new GeneralResponse(false,null,"Lütfen E-posta adresinize gönderilen link ile hesabınızı doğrulayıp tekrar deneyiniz"));
            }
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            return new GeneralApi().sendResponse(new GeneralResponse(true,new AuthToken(userEmailAndPassCheck.get().getId(),"Bearer "+token),null));
        }
        return new GeneralApi().sendResponse(new GeneralResponse(false,null,"Yanlış E-posta ya da parola girdiniz"));
    }


}
