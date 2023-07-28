package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Reserva;
import service.ReservasService;

@RestController
@CrossOrigin("*")
public class ReservasController {
	@Autowired
	ReservasService reservasService;
	
	
	
	@PostMapping(value="Reservar/{plazas}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String nuevaReserva(@RequestBody Reserva reserva, @PathVariable("plazas") int plazas) {
		reservasService.altaReserva(reserva, plazas);
		return "¡¡ Reserva Realizada Correctamente !!";
	}
	
	
	@GetMapping(value="ListarReservas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Reserva> listar() {
		
		return reservasService.ListarTodasReservas();
	}
	
	
}
