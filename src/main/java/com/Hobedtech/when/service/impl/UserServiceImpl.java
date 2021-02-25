package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.dto.*;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.mail.MailService;
import com.Hobedtech.when.repository.UserRepository;
import com.Hobedtech.when.repository.UserVipRepository;
import com.Hobedtech.when.service.UserService;
import com.Hobedtech.when.util.ApiPaths;
import com.Hobedtech.when.util.TPage;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.*;


@Service(value = "userService")
@Slf4j
@Validated
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    @Autowired
    ServletContext servletContext;

    private final UserRepository userRepository;
    private final UserVipRepository userVipRepository;

    private final ModelMapper modelMapper;
    private final MailService mailService;
    public UserServiceImpl(UserRepository userRepository, UserVipRepository userVipRepository, ModelMapper modelMapper, MailService mailService) {
        this.userRepository = userRepository;
        this.userVipRepository = userVipRepository;

        this.modelMapper = modelMapper;

        this.mailService = mailService;
    }

    @Override
    public UserUpdateDto save(UserUpdateDto user) throws IOException {
        User currentUser = userRepository.findById(user.getId()).get();
        String base64Image = user.getImage().split(",")[1];
        byte[] imageByte=Base64.getDecoder().decode(base64Image);
        String image_path =currentUser.getUsername()+getAlphaNumericString(10);
        String directory="/Users/furkanansin/IdeaProjects/images/"+ image_path+user.getImageType();
        new FileOutputStream(directory).write(imageByte);
        currentUser.setImage(image_path+user.getImageType());
        currentUser.setAge(user.getAge());
        currentUser.setNameSurname(user.getNameSurname());
        currentUser.setFirebaseId(user.getFirebaseId());
        currentUser.setSchool(user.getSchool());
        currentUser.setAge(user.getAge());
        currentUser.setGender(user.getGender());
        userRepository.save(currentUser);

        return user;
    }

    @Override
    public UserDto getById(Long id) {
        User u = userRepository.getOne(id);
        return modelMapper.map(u, UserDto.class);
    }

    @Override
    public OtherUserDto getByIdOtherUser(Long id) {
        User u = userRepository.getOne(id);
        return modelMapper.map(u, OtherUserDto.class);
    }

    @Override
    public ProfileCheckInDto getByIdProfileCheckIn(Long id) {
        User u = userRepository.getOne(id);
        return modelMapper.map(u, ProfileCheckInDto.class);
    }

    @Override
    public ProfileFavDto getByIdProfileFav(Long id) {
        User u = userRepository.getOne(id);
        return modelMapper.map(u, ProfileFavDto.class);
    }

    @Override
    public UserVipDto getByIdProfileVenue(Long id) {
        UsrVp u = userVipRepository.getVenueForProfile(id);
        return modelMapper.map(u, UserVipDto.class);
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        TPage<UserDto> respnose = new TPage<UserDto>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), UserDto[].class)));
        return respnose;
    }

    public List<UserDto> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }


    @Override
    public UserDto getByUsername(String username) {
        User u = userRepository.findByUsername(username);
        return modelMapper.map(u, UserDto.class);
    }

    @Override
    public User sendAgain(Long id) {
        User user = userRepository.getOne(id);
        String subject = "When uygulamasına kayıt olduğunuz için teşekkür ederiz";
        String text = "Lütfen uygulamayı kullanmaya devam edebilmek için bu linkten hesabınızı doğrulayınız";
        String validationLink = ApiPaths.BASE_URL+"api/token/validate?id=" + user.getId() + "&token=" + user.getToken();
        mailService.sendEmail(user.getEmail(),validationLink,subject,text);
        return user;
    }

    @Override
    public User forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if(!user.getEmail().isEmpty()){
            user.setCreatedDate(new Date(System.currentTimeMillis()));
            user.setExpiryDate(user.calculateExpiryDate(60 * 24));
            String validationLink = ApiPaths.BASE_URL+"api/token/change-password?id=" + user.getId();
            String subject = "When Parola Sıfırlama İsteği";
            String text = "Lütfen bu linke tıklayarak parolanızı sıfırlayınız";
            mailService.sendEmail(user.getEmail(), validationLink,subject,text);
        }
        return null;
    }

    @Override
    public String changePassword(Long id) {
        User user = userRepository.getOne(id);
        String newPassword = getAlphaNumericString(8);
        if (new Date(System.currentTimeMillis()).before(user.getExpiryDate())){
            user.setPassword(newPassword);
            String subject = "When Yeni Parolanız";
            String text = "";
             mailService.sendEmail(user.getEmail(),"Geçici parolanız:" +"  " + newPassword+ " " + "Bu parola ile giriş yapıp şifrenizi değiştirebilirsiniz.",subject,text);
            return "Parola Başarıyla değiştirildi. Birazdan mail alıcaksınız.";
        }

        else {
            String subject = "When Yeni Parolanız";
            String text = "";
            mailService.sendEmail(user.getEmail(),"Parola sıfırlama linkinin süresi dolmuş. Lütfen yeni bir parola sıfırlama linki alınız.",subject,text);

            return "Parola sıfırlama linkinin süresi dolmuş. Lütfen yeni bir parola sıfırlama linki alınız.";
        }
    }

    @Override
    public String changePasswordByUser(Long id, String password,String newPassword) {
        User user = userRepository.getOne(id);

        if(bcryptEncoder.matches(password,user.getPassword())){
            String newPass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(newPassword);
            user.setPassword(newPass);
            user.setCreatedDate(new Date(System.currentTimeMillis()));
            return"true";
        }
            else {
                return "false";
            }
    }

    @Override
    public Integer countFriends(Long userId) {
        int halfFriend = userRepository.getCountFriendById(userId);
        int otherHalfFriend = userRepository.getCountFriendByIdPers(userId);
        return halfFriend + otherHalfFriend;
    }

    @Override
    public String isFriend(Long userId, Long otherUserId) {
        String  user = userRepository.isFriendFriendById(userId,otherUserId);
        String user1 = userRepository.isFriendFriendByIdPers(userId,otherUserId);

        if(user != null){
            return user;
        }
        else return Objects.requireNonNullElse(user1, "NotFriend");



    }

    String getAlphaNumericString(int n )
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Transactional
    public String register(@Valid RegistrationRequest registrationRequest) {

     Optional<User> userCheck = Optional.ofNullable(userRepository.findByEmail(registrationRequest.getEmail()));
     if(userCheck.isPresent()){
         return "Bu E-posta adresi veya kullanıcı adı ile kayıtlı bir kullanıcı bulunmaktadır";
     }
         User user = new User();
        user.setToken(getAlphaNumericString(100));
        user.setExpiryDate(user.calculateExpiryDate(60));
         user.setCreatedDate(new Date(System.currentTimeMillis()));
         user.setEmail(registrationRequest.getEmail());
         user.setUsername(registrationRequest.getUserName());
         user.setPassword(bcryptEncoder.encode(registrationRequest.getPassword()));
         user.setRole("USER");
         user.setActive(false);


         //send email
         String subject = "When uygulamasına kayıt olduğunuz için teşekkür ederiz";
         String text = "Lütfen uygulamayı kullanmaya devam edebilmek için bu linkten hesabınızı doğrulayınız";
         String validationLink = ApiPaths.BASE_URL+"api/token/validate?id=" + user.getId() + "&token=" + user.getToken();
        boolean isEmailReal= mailService.sendEmail(registrationRequest.getEmail(), validationLink,subject,text);
        if(!isEmailReal){
            return registrationRequest.getEmail() + " adlı E-posta adresi geçerli bir E-posta adresi değildir. Lütfen geçerli bir E-posta adresi girin";
        }

        User user1 =  userRepository.save(user);

        return "";


    }
    @Transactional
    public boolean validate(Long id,String token){
        boolean isOkay;
        try {
              User user =  userRepository.getOne(id);
            if(user.getToken().equals(token)){
               if (new Date(System.currentTimeMillis()).before(user.getExpiryDate())){
                   user.setActive(true);
                   isOkay = true;
               }
            }
            String subject = "When Hesap Onaylama";
            String text = "";
            mailService.sendEmail(user.getEmail(),"Hesap aktivasyon linkinin süresi dolmuştur. Lütfen yeniden kayıt olunuz.",subject,text);
            userRepository.delete(user);
            isOkay = false;
            return isOkay;
        }catch (Exception e){
            log.error(String.valueOf(e));
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROlE_" + user.getRole() ));

        return authorities;
    }
}
