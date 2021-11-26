package edu.kpi.iasa.mmsa.workshop.service;


import edu.kpi.iasa.mmsa.workshop.configuration.security.UserPrincipal;
import edu.kpi.iasa.mmsa.workshop.exception.UserNotFoundException;
import edu.kpi.iasa.mmsa.workshop.model.Account;
import edu.kpi.iasa.mmsa.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return new UserPrincipal(user);
    }
}
