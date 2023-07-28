package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Vuelo;

public interface VueloDao extends JpaRepository<Vuelo, Integer>{

	//Buscar Vuelos con el número de plazas disponibles
	@Query("select v from Vuelo v where v.plazas>=?1")
	List<Vuelo> vuelosConPlazas(int plazas);
	
	//
}
