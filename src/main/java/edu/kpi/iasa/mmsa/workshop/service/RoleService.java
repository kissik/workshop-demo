package edu.kpi.iasa.mmsa.workshop.service;

import edu.kpi.iasa.mmsa.workshop.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.workshop.model.Role;
import edu.kpi.iasa.mmsa.workshop.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByCode(String code) {
        return roleRepository.findRoleByCode(code).orElseThrow(RoleNotFoundException::new);
    }
}
