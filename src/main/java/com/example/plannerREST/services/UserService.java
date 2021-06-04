package com.example.plannerREST.services;

import com.example.plannerREST.dto.UsersDTO;
import com.example.plannerREST.entities.Users;

import java.util.List;

public interface UserService {

    List<UsersDTO> getAllUsers(int page, int size, String filter);
    UsersDTO getUser(int id);
    Users saveUser(UsersDTO usersDTO) throws Exception;
    Users updateUser(UsersDTO usersDTO);
    void removeUser(int id);
}
