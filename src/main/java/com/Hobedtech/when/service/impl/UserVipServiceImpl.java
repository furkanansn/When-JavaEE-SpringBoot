package com.Hobedtech.when.service.impl;


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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        UsrVp usrVp = userVipRepository.save(userVip);

        return usrVp.getId();
    }

    @Override
    public String confirm() {
        return null;
    }

    @Override
    public Events addEvent(Events events) {
     return eventRepository.save(events);
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
}
