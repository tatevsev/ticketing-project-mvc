package com.cydeo.service.impl;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl extends AbstractMapService<UserDTO,String> implements UserService  {


    @Override
    public UserDTO save(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO findById(String username) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public void deleteById(String username) {

    }
}
