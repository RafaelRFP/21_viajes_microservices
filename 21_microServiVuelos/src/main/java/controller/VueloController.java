package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Vuelo;
import service.VuelosService;

@RestController
@CrossOrigin("*")
public class VueloController {
	
	@Autowired
	VuelosService vuelosService;
	
	@GetMapping(value="Vuelos/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> listaVuelos(@PathVariable("plazas") int plazas) {
		
		return vuelosService.obtenerListaVuelos(plazas);
	}
	
	
	@PutMapping(value="ActualizarPlazas/{vuelo}/{plazas}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String modificaVuelo(@PathVariable("vuelo") int idvuelo,
								@PathVariable("plazas") int plazas) {
		
		if (vuelosService.actualizarPlazasVuelo(idvuelo, plazas)==true) {
			return "¡¡ Vuelo Actualizado Correctamente !!";
		} else {
			return "¡¡ Vuelo No encontrado o El número de plazas es superior a que tenemos en la Compañía!!";
		}
	}
	
	
}
