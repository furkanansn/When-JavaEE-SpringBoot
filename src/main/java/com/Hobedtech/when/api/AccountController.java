
package com.Hobedtech.when.api;


import com.Hobedtech.when.dto.RegistrationRequest;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.repository.UserRepository;
import com.Hobedtech.when.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
public class AccountController {

   // @Autowired
 //   private AuthenticationManager authenticationManager;
  //  @Autowired
   // private JwtTokenUtil jwtTokenUtil;
    private final UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;

    public AccountController(UserServiceImpl userService) {
        this.userService = userService;
    }

   /* @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {


        if(userRepository.checkEmailExist(registrationRequest.getEmail()).getEmail().length()>1){
            return ResponseEntity.ok("Böyle bir kullanıcı zaten mevcut");
        }
        else{
            String response = userService.register(registrationRequest);
            return ResponseEntity.ok(response);
        }
    }
    @RequestMapping(value = "/send-again", method = RequestMethod.GET)
    public ResponseEntity<String> sendAgain(@RequestParam Long id){
        User user = userService.sendAgain(id);
        return ResponseEntity.ok("Aktivasyon linki tekrardan gönderildi");
    }
    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    public ResponseEntity<String> validate(@RequestParam String token,@RequestParam Long id){
      return ResponseEntity.ok( userService.validate(id,token));
    }
    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public ResponseEntity<String> sendAgain(@RequestParam String email){
        return ResponseEntity.ok(userService.forgotPassword(email));
    }

    @RequestMapping(value = "/change-password",method = RequestMethod.GET)
    public ResponseEntity<String> changePassword(@RequestParam Long id){
        return ResponseEntity.ok(userService.changePassword(id));
    }

    @RequestMapping(value = "/change-password-by-user",method = RequestMethod.GET)
    public ResponseEntity<String> changePasswordByUser(@RequestParam Long id, @RequestParam String password){
        return ResponseEntity.ok(userService.changePasswordByUser(id,password));
    }

}
