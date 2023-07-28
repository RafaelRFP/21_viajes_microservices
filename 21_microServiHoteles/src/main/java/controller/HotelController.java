package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Hotel;
import service.HotelesService;

@RestController
@CrossOrigin("*")
public class HotelController {
	
	@Autowired
	HotelesService hotelesService;
	
	@GetMapping(value="Hoteles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> listaHoteles() {
		
		return hotelesService.obtenerListaHoteles();
	}

	@GetMapping(value="Hotel/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> listaHoteles(@PathVariable("nombre") String nombre) {
		
		return hotelesService.buscarHotel(nombre);
	}
}

