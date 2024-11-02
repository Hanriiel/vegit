package vegit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vegit.domain.AppUser;
import vegit.repository.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPasswordHash())
                .roles(appUser.getRole()) 
                .build();
    }

}
