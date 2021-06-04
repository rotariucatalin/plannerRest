package com.example.plannerREST.service_implementation;

import com.example.plannerREST.dto.PermissionDTO;
import com.example.plannerREST.entities.Permission;
import com.example.plannerREST.repositories.PermissionRepository;
import com.example.plannerREST.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Permission savePermission(PermissionDTO permissionDTO) {

        Permission permission = new Permission();
        permission.setName(permissionDTO.getName());

        permissionRepository.save(permission);

        return permission;
    }

    @Override
    public PermissionDTO getPermission(int id) {

        Permission permission = permissionRepository.findById(id).get();
        return convertToDTO(permission);
    }

    @Override
    public Permission updatePermission(PermissionDTO permissionDTO) {

        Permission permission = new Permission();
        permission.setId(permissionDTO.getId());
        permission.setName(permissionDTO.getName());

        permissionRepository.save(permission);

        return permission;
    }

    @Override
    public void removePermission(int id) {
        permissionRepository.deleteById(id);
    }

    private PermissionDTO convertToDTO(Permission permission) {

        PermissionDTO permissionDTO = new PermissionDTO();

        permissionDTO.setId(permission.getId());
        permissionDTO.setName(permission.getName());

        return permissionDTO;
    }
}
