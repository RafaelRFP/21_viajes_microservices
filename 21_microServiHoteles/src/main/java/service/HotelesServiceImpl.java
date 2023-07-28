package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.HotelDao;
import model.Hotel;

@Service
public class HotelesServiceImpl implements HotelesService {
	@Autowired
	HotelDao hotelDao;
	
	@Override
	public List<Hotel> obtenerListaHoteles() {
		//para sacar todos los hotrlrd
		//return hotelDao.findAll();
		//para sacar solo los hoteles disponibles
		return hotelDao.hotelesDisponibles();
	}

	@Override
	public List<Hotel> buscarHotel(String nombre) {
		
		return hotelDao.findByNombre(nombre);
	}

	
}
