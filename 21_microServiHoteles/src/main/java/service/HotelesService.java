package service;

import java.util.List;

import model.Hotel;

public interface HotelesService {
	List<Hotel> obtenerListaHoteles();
	
	//buscar Hotel por nombre
	List<Hotel> buscarHotel(String nombre);

}
