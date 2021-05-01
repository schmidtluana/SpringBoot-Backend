package com.luana.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luana.cursomc.domain.Cliente;
import com.luana.cursomc.repositories.ClienteRepository;
import com.luana.cursomc.security.UserSS;

@Service
public class UserDetailsServicelmpl implements UserDetailsService{

	@Autowired
	private ClienteRepository repo;
	
	@Override
	//buscar usuário por email
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = repo.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(),cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}
