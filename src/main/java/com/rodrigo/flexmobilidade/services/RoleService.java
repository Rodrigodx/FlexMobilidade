package com.rodrigo.flexmobilidade.services;

import com.rodrigo.flexmobilidade.model.role.Role;
import com.rodrigo.flexmobilidade.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService{

    private final RoleRepository roleRepository;

    @Transactional
    public Role save(Role role){
        return roleRepository.save(role);
    }

    @ReadOnlyProperty
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

}
