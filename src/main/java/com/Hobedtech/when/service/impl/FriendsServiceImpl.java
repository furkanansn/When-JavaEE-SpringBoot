package com.Hobedtech.when.service.impl;

import com.Hobedtech.when.dto.FriendDto;
import com.Hobedtech.when.dto.FriendShipDto;
import com.Hobedtech.when.entity.Friends;
import com.Hobedtech.when.entity.FriendsStatus;
import com.Hobedtech.when.entity.User;
import com.Hobedtech.when.repository.FriendsRepository;
import com.Hobedtech.when.repository.FriendsUserRepository;
import com.Hobedtech.when.service.FriendsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import static java.util.Objects.isNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * When Created by furkanansin on Oct, 2020
 */
@Service
public class FriendsServiceImpl implements FriendsService {
    private final FriendsUserRepository friendsUserRepository;
    private final FriendsRepository friendsRepository;
    private final ModelMapper modelMapper;

    public FriendsServiceImpl(FriendsUserRepository friendsUserRepository, FriendsRepository friendsRepository, ModelMapper modelMapper) {
        this.friendsUserRepository = friendsUserRepository;
        this.friendsRepository = friendsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FriendDto> getFriendsById(Long userId) {
        List<User> users = friendsUserRepository.getFriendById(userId);
        List<User> users1 = friendsUserRepository.getFriendByIdPers(userId);
        List<Object> list = Stream.concat(users.stream(),users1.stream())
                .collect(Collectors.toList());
        return Arrays.asList(modelMapper.map(list,FriendDto[].class));
    }

    @Override
    public List<FriendDto> getFriendsByIdAndName(Long userId, String username) {
        List<User> users = friendsUserRepository.getFriendByIdAndUsername(userId,username);
        List<User> users1 = friendsUserRepository.getFriendByIdPersAndUserName(userId,username);
        List<Object> list = Stream.concat(users.stream(),users1.stream())
                .collect(Collectors.toList());
        return Arrays.asList(modelMapper.map(list,FriendDto[].class));
    }

    @Override
    public Boolean save(FriendShipDto friends) {
        Optional<Friends> friends1 = Optional.ofNullable(friendsRepository.findTopBy(friends.getFriendOne(), friends.getFriendTwo(), friends.getFriendOne(), friends.getFriendTwo()));
        if(friends1.isPresent()){
        System.out.println("Böyle bir arkadaşlık var zaten");
            return false;
        }
        else{
            Friends friends2 = modelMapper.map(friends,Friends.class);

            friends2.setFriendsStatus(FriendsStatus.ACTIVE);
            friendsRepository.save(friends2);
            return true;
        }
    }

    @Override
    public Friends update(FriendShipDto friends) {
        Friends friends1 = friendsRepository.findTopBy(friends.getFriendOne(),friends.getFriendTwo(),friends.getFriendOne(),friends.getFriendTwo());
        friends1.setFriendsStatus(FriendsStatus.ACTIVE);
        return friendsRepository.save(friends1);
    }


    @Override
    public void delete(FriendShipDto friends) {
        Friends friends1 = friendsRepository.findTopBy(friends.getFriendOne(),friends.getFriendTwo(),friends.getFriendOne(),friends.getFriendTwo());

        friendsRepository.delete(friends1);
    }
}
