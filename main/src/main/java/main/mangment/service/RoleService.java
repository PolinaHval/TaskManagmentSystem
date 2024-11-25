package main.mangment.service;

import lombok.AllArgsConstructor;
import main.mangment.model.Role;
import main.mangment.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

  private RoleRepository roleRepository;

  public Role getRole(String roleName){
    return roleRepository.getRoleByName(roleName);
  }
}
