package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Hotel;

public interface HotelDao extends JpaRepository<Hotel, String> {
	
	//Buscar Hoteles disponibles
	@Query("select h from Hotel h where h.disponible=1")
	List<Hotel> hotelesDisponibles();
	
	//Buscar Hoteles por Nombre
	@Query("select h from Hotel h where h.nombre=?1")
	List<Hotel> findByNombre(String Nombre);

	
}				 
