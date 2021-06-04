package com.example.plannerREST.service_implementation;

import com.example.plannerREST.dto.PermissionDTO;
import com.example.plannerREST.dto.UsersDTO;
import com.example.plannerREST.entities.Permission;
import com.example.plannerREST.entities.Users;
import com.example.plannerREST.repositories.PermissionRepository;
import com.example.plannerREST.repositories.UserRepository;
import com.example.plannerREST.services.PermissionService;
import com.example.plannerREST.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<UsersDTO> getAllUsers(int page, int size, String filter) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Users> usersPage = userRepository.findAll(pageable, filter);
        List<Users> users = usersPage.getContent();

        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UsersDTO getUser(int id) {

        Users user = userRepository.findById(id).get();
        List<PermissionDTO> permissions = new ArrayList<>();

        UsersDTO usersDTO = convertToDTO(user);

        user.getPermissions().forEach(permission -> {

            PermissionDTO permissionDTO = new PermissionDTO();
            permissionDTO.setId(permission.getId());
            permissionDTO.setName(permission.getName());

            permissions.add(permissionDTO);
        });

        usersDTO.setPermissions(permissions);

        return usersDTO;
    }

    @Override
    public Users saveUser(UsersDTO usersDTO) throws Exception {

        Users foundUser = userRepository.findByUserName(usersDTO.getUserName());

        if(foundUser == null) {

            List<Permission> permissions = new ArrayList<>();

            Users user = new Users();
            user.setFirstName(usersDTO.getFirstName());
            user.setLastName(usersDTO.getLastName());
            user.setActive(usersDTO.isActive());
            user.setEmail(usersDTO.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(usersDTO.getPassword()));

            usersDTO.getPermissions().stream().forEach(permissionDTO -> {

                Permission permission = permissionRepository.findById(permissionDTO.getId()).get();
                permissions.add(permission);
            });

            user.setPermissions(permissions);

            userRepository.save(user);

            return user;

        } else {
            throw new Exception("Username already exists");
        }
    }

    @Override
    public Users updateUser(UsersDTO usersDTO) {

        List<Permission>  permissions = new ArrayList<>();

        Users user = userRepository.findById(usersDTO.getId()).get();
        user.setEmail(usersDTO.getEmail());
        user.setFirstName(usersDTO.getFirstName());
        user.setLastName(usersDTO.getLastName());
        user.setActive(usersDTO.isActive());

        usersDTO.getPermissions().stream().forEach(permissionDTO -> {

            Permission permission = permissionRepository.findById(permissionDTO.getId()).get();

            permissions.add(permission);

        });

        user.setPermissions(permissions);

        userRepository.save(user);

        return user;
    }

    @Override
    public void removeUser(int id) {
        userRepository.deleteById(id);
    }

    private UsersDTO convertToDTO(Users users) {

        UsersDTO usersDTO = new UsersDTO();

        usersDTO.setId(users.getId());
        usersDTO.setFirstName(users.getFirstName());
        usersDTO.setLastName(users.getLastName());
        usersDTO.setActive(users.isActive());
        usersDTO.setEmail(users.getEmail());

        return usersDTO;
    }
}
