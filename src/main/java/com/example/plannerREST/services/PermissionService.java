package com.example.plannerREST.services;

import com.example.plannerREST.dto.PermissionDTO;
import com.example.plannerREST.entities.Permission;

import java.util.List;

public interface PermissionService {

    List<PermissionDTO> getAllPermissions();
    PermissionDTO getPermission(int id);
    Permission savePermission(PermissionDTO permissionDTO);
    Permission updatePermission(PermissionDTO permissionDTO);
    void removePermission(int id);
}
