package com.anhbui.demosecurity.service.impl;

import com.anhbui.demosecurity.entity.Role;
import com.anhbui.demosecurity.repository.RoleRepository;
import com.anhbui.demosecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
