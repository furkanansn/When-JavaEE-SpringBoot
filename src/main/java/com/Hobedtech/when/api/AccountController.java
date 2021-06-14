
package com.Hobedtech.when.api;


import com.Hobedtech.when.config.TokenProvider;
import com.Hobedtech.when.dto.AuthToken;
import com.Hobedtech.when.dto.GeneralResponse;
import com.Hobedtech.when.dto.LoginRequest;
import com.Hobedtech.when.dto.RegistrationRequest;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.repository.EventRepository;
import com.Hobedtech.when.repository.UserRepository;
import com.Hobedtech.when.repository.UserVipRepository;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    @Autowired
    private UserVipRepository userVipRepository;
    @Autowired
    private EventRepository eventRepository;

   @GetMapping("/generate-data")
   public ResponseEntity<GeneralResponse> generateData(){
       try{
           for (int i = 0 ; i < 100 ; i++){
               UsrVp usrVp = new UsrVp();
               usrVp.setEmail("string" + String.valueOf(i) +"@gmail.com");
               usrVp.setConfirmedAccount("true");
               usrVp.setBio("Lorem İpsum Venue Bio");
               usrVp.setLatitude(123.123);
               usrVp.setLongitude(234.123);
               usrVp.setPassword("string");
               usrVp.setRole("VENUE");
               usrVp.setPhone("+905449561307");
               usrVp.setUsername("stringvenue"+String.valueOf(i));
               usrVp.setPhoto("https://www.kibrispostasi.com/imagecache/newsimage/news/u/un/untitled-3_1597650146.jpg");
               UsrVp usrVp1 = userVipRepository.save(usrVp);
               for(int j = 0 ; j < 5; j++){
                   Events events = new Events();
                   events.setCity("Girne");
                   events.setDate(new Date());
                   events.setEventImagePath("https://static.dw.com/image/57028885_101.jpg");
                   events.setTitle("Lorem İpsum Event");
                   events.setNumberOfViews(100L);
                   events.setUserVips(usrVp1);
                   Events events1 = eventRepository.save(events);
               }
               User user = new User();
               user.setBio("Lorem İpsum User Bio");
               user.setEmail("string"+String.valueOf(i)+"@gmail.com");
               user.setActive(true);
               user.setUsername("string" + String.valueOf(i));
               user.setAge(20);
               user.setCreatedDate(new Date());
               user.setNameSurname("String String");
               user.setPassword("string");
               user.setPhone("+905449561307");
               user.setRole("USER");
               user.setImage("https://pbs.twimg.com/profile_images/1269570753630015493/x4NFy1YH_400x400.jpg");
               userRepository.save(user);
           }
           return new GeneralApi().sendResponse(new GeneralResponse(true,"eklendi",null));

       }catch (Exception e){
           return new GeneralApi().sendResponse(new GeneralResponse(false,null,e.getMessage().toString()));
       }

   }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<GeneralResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        String response = userService.register(registrationRequest);

        if(!response.isEmpty()){
            return new GeneralApi().sendResponse(new GeneralResponse(false,null,response.toString()));

        }
        return new GeneralApi().sendResponse(new GeneralResponse(true,"Başarıyla kayıt oldunuz. Lütfen E-postanıza gönderilen bağlantı ile doğrulama yapınız.",null));
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
