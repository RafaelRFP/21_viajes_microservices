package service;

import java.util.List;

import model.Vuelo;

public interface VuelosService {
	List<Vuelo> obtenerListaVuelos(int plazas);
	
	boolean actualizarPlazasVuelo(int idvuelo, int plazas);

}
