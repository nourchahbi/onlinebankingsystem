package com.onlinebank.demo.sec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebank.demo.domain.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*Génération du token*/

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager=authenticationManager;
	}
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			{
		AppUser appuser=null;
		try {
			appuser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appuser.getUsername(), appuser.getPassword()));

		} catch (Exception e) {
			throw  new BadCredentialsException("Username ou Password Incorrect",e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
		
		User springUser = (User) authResult.getPrincipal();
		List<String> roles= new ArrayList<>();
		springUser.getAuthorities().forEach(a->{
			roles.add(a.getAuthority());
		});
		//creation du token
		String jwt = JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(springUser.getUsername())
				.withArrayClaim("roles", roles.toArray(new String [roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
				.sign(Algorithm.HMAC256(SecurityParams.SECRET));
		
		//envoi du token dans le header
		response.addHeader(SecurityParams.HEADERNAME, jwt);
		
	}

}
