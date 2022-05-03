package trodriguesr.com.github.sales.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import trodriguesr.com.github.sales.dto.CredentialsDto;
import trodriguesr.com.github.sales.dto.TokenDto;
import trodriguesr.com.github.sales.exception.InvalidPasswordException;
import trodriguesr.com.github.sales.models.entities.AuthenticationUser;
import trodriguesr.com.github.sales.security.jwt.JwtService;
import trodriguesr.com.github.sales.services.AuthenticationUserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class AuthenticationUserController {

	@Autowired
	private AuthenticationUserServiceImpl userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AuthenticationUser save(@RequestBody @Valid AuthenticationUser authUser) {
		String encryptedPassword = passwordEncoder.encode(authUser.getPassword());
		authUser.setPassword(encryptedPassword);
		return userService.save(authUser);
	}

	@PostMapping("/auth")
	public TokenDto authenticating(@RequestBody CredentialsDto credentials) {

		try {

			AuthenticationUser authUser = new AuthenticationUser();
			authUser.setLogin(credentials.getLogin());
			authUser.setPassword(credentials.getPassword());

			UserDetails authenticatedUser = userService.authenticating(authUser);
			String token = jwtService.generatingToken(authUser);

			return new TokenDto(authUser.getLogin(), token);

		} catch (UsernameNotFoundException | InvalidPasswordException e) {

			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
