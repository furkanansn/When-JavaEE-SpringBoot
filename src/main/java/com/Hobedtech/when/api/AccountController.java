
package com.Hobedtech.when.api;


import com.Hobedtech.when.dto.RegistrationRequest;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.repository.UserRepository;
import com.Hobedtech.when.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        String response = userService.register(registrationRequest);
        return ResponseEntity.ok(response);
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
        User user = userService.forgotPassword(email);
        if(user.getEmail().isEmpty()){return ResponseEntity.ok("Böyle bir hesap bulunamadı");}
        else return ResponseEntity.ok("Parola sıfırlama linki gönderildi");
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
