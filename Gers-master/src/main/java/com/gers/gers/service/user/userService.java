package com.gers.gers.service.user;

import com.gers.gers.models.userInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userService {

    public userInfo createUser(userInfo user);


    userInfo findUsername(String name);

    List<userInfo> showAll();



}
