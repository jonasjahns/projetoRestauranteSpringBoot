package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import restaurante.dao.UsuarioDao;
import restaurante.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario getById(Integer id) {
		return usuarioDao.findOne(id);
	}

	public List<Usuario> getAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	public void save(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioDao.save(usuario);
	}

	public void remover(Integer id) {
		usuarioDao.delete(id);
	}

	public Usuario findByCPF(String CPF) {
		return usuarioDao.findByCpf(CPF);
	}

	public boolean CPFUsuarioUnico(Integer id, String CPF) {
		Usuario user = findByCPF(CPF);
		return (user == null || ((id != null) && (user.getId() == id)));
	}
	
	public void remover(String CPF)
	{
		usuarioDao.deleteByCpf(CPF);
	}

}
