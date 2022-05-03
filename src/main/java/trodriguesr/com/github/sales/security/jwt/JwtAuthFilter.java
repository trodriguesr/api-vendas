package trodriguesr.com.github.sales.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import trodriguesr.com.github.sales.services.AuthenticationUserServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private AuthenticationUserServiceImpl userService;

	public JwtAuthFilter(JwtService jwtService, AuthenticationUserServiceImpl userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorization = request.getHeader("Authorization");

		if (authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];
			boolean isValid = jwtService.validToken(token);

			if (isValid) {
				String loginUser = jwtService.getUserLogin(token);
				UserDetails userDetails = userService.loadUserByUsername(loginUser);
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}

		filterChain.doFilter(request, response);

	}
}
