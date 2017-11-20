package restaurante.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restaurante.model.Usuario;
import restaurante.model.UsuarioProfile;
import restaurante.service.UsuarioService;

 
 
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
 
    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
     
    @Autowired
    private UsuarioService userService;
     
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String CPF)
            throws UsernameNotFoundException {
        Usuario user = userService.findByCPF(CPF);
        logger.info("User : {}", user);
        if(user==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(user.getCpf(), user.getPassword(), 
                 true, true, true, true, getGrantedAuthorities(user));
    }
 
     
    private List<GrantedAuthority> getGrantedAuthorities(Usuario user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(UsuarioProfile userProfile : user.getUsuarioProfile()){
            logger.info("UserProfile : {}", userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getTipo()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }
     
}
