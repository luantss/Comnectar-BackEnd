package com.generation.comnectar.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.comnectar.model.Usuario;
import com.generation.comnectar.model.UsuarioLogin;
import com.generation.comnectar.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByLoginUsuario(usuario.getLoginUsuario()).isPresent())
			return Optional.empty();

		usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<Usuario> buscaLoginUsuario = usuarioRepository.findByLoginUsuario(usuario.getLoginUsuario());

			if (buscaLoginUsuario.isPresent()) {				
				if (buscaLoginUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
			}

			usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));

			return Optional.of(usuarioRepository.save(usuario));
		} 

		return Optional.empty();
	}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByLoginUsuario(usuarioLogin.get().getLoginUsuario());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNomeUsuario(usuario.get().getNomeUsuario());
				usuarioLogin.get().setLoginUsuario(usuario.get().getLoginUsuario());
				usuarioLogin.get().setLocalUsuario(usuario.get().getLocalUsuario());
				usuarioLogin.get().setSenhaUsuario(usuario.get().getSenhaUsuario());
				usuarioLogin.get().setFoto(usuario.get().getFotoUsuario());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getLoginUsuario(), usuarioLogin.get().getSenhaUsuario()));

				return usuarioLogin;

			}
		}	

		return Optional.empty();

	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}

	private boolean compararSenhas(String senhaDigitada, String senhaBD) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBD);

	}

	private String gerarBasicToken(String email, String password) {

		String tokenBase = email + ":" + password;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}


}
