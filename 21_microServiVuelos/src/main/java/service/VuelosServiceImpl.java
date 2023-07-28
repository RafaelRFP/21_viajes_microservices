package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.VueloDao;
import model.Vuelo;

@Service
public class VuelosServiceImpl implements VuelosService {
	@Autowired
	VueloDao vueloDao;
	
	@Override
	public List<Vuelo> obtenerListaVuelos(int plazas) {
		
		//para sacar solo los vuelos con el número de plazas 
		return vueloDao.vuelosConPlazas(plazas);
	}

	
	@Override
	public boolean actualizarPlazasVuelo(int idvuelo, int plazas) {
			
		if (vueloDao.findById(idvuelo).isPresent()) {
			
			Vuelo vuelo=vueloDao.findById(idvuelo).get();
			if(plazas <= vuelo.getPlazas()) {
				vuelo.setPlazas(vuelo.getPlazas() - plazas);
				vueloDao.save(vuelo);
				return true;
			}else {
				return false;
			}
		}else{
			return false;
		}
			
	}
	
}
