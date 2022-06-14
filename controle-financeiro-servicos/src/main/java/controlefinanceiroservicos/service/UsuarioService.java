package controlefinanceiroservicos.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import controlefinanceiroservicos.model.Usuario;
import controlefinanceiroservicos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
    private PasswordEncoder encoder;
	
	public Integer inserir(Usuario usuario) {
		validacoes(usuario);
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		usuarioRepository.save(usuario);
		return usuario.getId();
	}
	

	public void inserirFoto(MultipartFile foto, Integer id) throws IOException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		if (usuarioOptional.isPresent()) {			
			Usuario usuario = usuarioOptional.get();
			usuario.setFoto(foto.getBytes());
		} else {
			throw new RuntimeException("Usuário não encontrado!");
		}
	}
		
	private void validacoes(Usuario usuario){
		if (usuario.getNome().isEmpty()) {
			throw new RuntimeException("O campo nome é de preenchimento obrigatório!");
		} else if (usuario.getNome().length() < 3) {
			throw new RuntimeException("O nome não pode ter menos do que 3 caracteres!");
		} else if (usuario.getNome().length() > 50) {
			throw new RuntimeException("O nome não pode ter mais do que 20 caracteres!");
		}
		
		//TODO: Validar se é um CPF válido
		if (usuario.getCpf().isEmpty()) {
			throw new RuntimeException("O campo CPF é de preenchimento obrigatório!");
		} else if (usuario.getCpf().length() != 11) {
			throw new RuntimeException("CPF inválido!");
		}
		
		if (usuario.getEmail().isEmpty()) {
			throw new RuntimeException("O campo email é de preenchimento obrigatório!");
		} else if (usuario.getEmail().length() > 50) {
			throw new RuntimeException("O email não pode ter mais do que 50 caracteres!");
		} else if (!usuario.getEmail().contains("@") || !usuario.getEmail().contains(".com")) {
			throw new RuntimeException("Email inválido!");
		}

		if (usuario.getSenha().isEmpty()) {
			throw new RuntimeException("O campo senha é de preenchimento obrigatório!");
		} else if (usuario.getSenha().length() < 6 || usuario.getSenha().length() > 8) {
			throw new RuntimeException("A senha deve conter entre 6 e 8 caracteres!");
		}
				
	}


	public Usuario retornarUsuarioId(Integer id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		
		throw new RuntimeException("Usuário não encontrado!");
	}


	public void editar(Integer id, Usuario usuarioNovo) {
		Optional<Usuario> optionalUsuarioAntigo = usuarioRepository.findById(id);
		
		if (optionalUsuarioAntigo.isPresent()) {
			Usuario usuarioAntigo = optionalUsuarioAntigo.get();
			validacoes(usuarioNovo);
			usuarioAntigo.setNome(usuarioNovo.getNome());
			usuarioAntigo.setCpf(usuarioNovo.getCpf());
			usuarioAntigo.setEmail(usuarioNovo.getEmail());
			usuarioAntigo.setSenha(encoder.encode(usuarioNovo.getSenha()));			
		} else {
			throw new RuntimeException("Usuário não foi encontrado para edição!");
		}
	}

	public void excluir(Integer id){
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		
		if (optionalUsuario.isPresent()) {	
			usuarioRepository.deleteById(id);
		} else {
			throw new RuntimeException("Usuário não foi encontrado para a exclusão!");
		}
	}
}
