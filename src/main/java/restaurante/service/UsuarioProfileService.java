package restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurante.dao.UsuarioProfileDao;
import restaurante.model.UsuarioProfile;

@Service
public class UsuarioProfileService {
	
	@Autowired
	UsuarioProfileDao usuarioProfileDao;
	
	public UsuarioProfile getById(Integer id)
	{
		return usuarioProfileDao.findOne(id);
	}
	
	public UsuarioProfile getByTipo(String tipo)
	{
		return usuarioProfileDao.findByTipo(tipo);
	}
	public List<UsuarioProfile> getAll()
	{
		return (List<UsuarioProfile>) usuarioProfileDao.findAll();
	}

}
