package com.Hobedtech.when.service.impl;


import com.Hobedtech.when.dto.EventDto;
import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.dto.UserVipDto;
import com.Hobedtech.when.entity.Events;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.entity.UsrVp;
import com.Hobedtech.when.repository.EventRepository;
import com.Hobedtech.when.repository.FriendsUserVipRepository;
import com.Hobedtech.when.repository.UserVipRepository;
import com.Hobedtech.when.service.UserVipService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service(value = "userVipService")
public class UserVipServiceImpl implements UserVipService, UserDetailsService {
    private final UserVipRepository userVipRepository;
    private final FriendsUserVipRepository friendsUserVipRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserVipServiceImpl(UserVipRepository userVipRepository, FriendsUserVipRepository friendsUserVipRepository, EventRepository eventRepository, ModelMapper modelMapper) {
        this.userVipRepository = userVipRepository;
        this.friendsUserVipRepository = friendsUserVipRepository;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public UserVipDto getById(Long id) {
        return modelMapper.map(userVipRepository.getOne(id),UserVipDto.class);
    }

    @Override
    public List<FriendDto> getByIdFollowers(Long id) {
        return Arrays.asList(modelMapper.map(friendsUserVipRepository.getfollowersForProfile(id),FriendDto[].class));
    }

    @Override
    public Integer follwersCount(Long id) {

        return userVipRepository.countFollowers(id);
    }

    @Override
    public Long register(UsrVp userVip) {
        userVip.setPassword(bcryptEncoder.encode(userVip.getPassword()));
        userVip.setRole("VENUE");
        Optional<UsrVp> usrVp = Optional.ofNullable(userVipRepository.getByEmail(userVip.getEmail()));
        if(usrVp.isPresent()){
            return 0L;
        }
        UsrVp usrVp1 = userVipRepository.save(userVip);
        return usrVp1.getId();
    }

    @Override
    public String confirm() {
        return null;
    }

    @SuppressWarnings("resource")
	@Override
    public Events addEvent(EventDto events) throws IOException {
    	
    	
     Events events1 = new Events();
     events1.setCity(events.getCity());
     events1.setDate(events.getDate());
     events1.setTitle(events.getTitle());
     
     Optional<UsrVp> userVp = userVipRepository.findById(events.getUserVipId());
     
     
     
     events1.setUserVips(userVp.get());
     events1.setEventImagePath(events.getImage()+events.getImageType());


        String base64Image = events.getImage().split(",")[1];

        byte[] imageByte=Base64.getDecoder().decode(base64Image);
        String image_path = userVp.get().getId()+getAlphaNumericString(10);
        String directory="/Users/furkanansin/IdeaProjects/images/"+ image_path+events.getImageType();

        new FileOutputStream(directory).write(imageByte);



        return eventRepository.save(events1);
    }

    @Override
    public Boolean deleteEvents(Long id) {
        Events event = eventRepository.getOne(id);
        try{
            eventRepository.delete(event);
            return true;
        }catch(Exception exception){
            return false;
        }

    }

    @Override
    public List<Events> getEvents(Long userVipId) {

        return eventRepository.getEventsVenue(userVipId);
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UsrVp user = userVipRepository.getByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }
    private Set<SimpleGrantedAuthority> getAuthority(UsrVp user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROlE_" + user.getRole() ));

        return authorities;
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

}
