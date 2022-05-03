package trodriguesr.com.github.sales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trodriguesr.com.github.sales.exception.InvalidPasswordException;
import trodriguesr.com.github.sales.models.entities.AuthenticationUser;
import trodriguesr.com.github.sales.models.repositories.AuthenticationUserRepository;

@Service
public class AuthenticationUserServiceImpl implements UserDetailsService {
	
	@Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationUserRepository repository;

    @Transactional
    public AuthenticationUser save(AuthenticationUser user){
        return repository.save(user);
    }
    
    public UserDetails authenticating( AuthenticationUser user ){
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean comparingPassword = encoder.matches( user.getPassword(), userDetails.getPassword() );

        if(comparingPassword){
            return userDetails;
        }

        throw new InvalidPasswordException();
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 AuthenticationUser user = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = user.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User.builder()
        		.username(user.getLogin())
        		.password(user.getPassword())
        		.roles(roles)
        		.build();
                
    }
	}


