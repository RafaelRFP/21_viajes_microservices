package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.ReservaDao;
import model.Reserva;

@Service
public class ReservasServiceImpl implements ReservasService {
	
	String url="http://vuelos/"; //url de vuelos registrado en Eureka
	
	@Autowired
	ReservaDao reservaDao;
	
	@Autowired
	RestTemplate template;
	
	@Override
	public void altaReserva(Reserva reserva, int plazas) {
		reservaDao.save(reserva);
		template.put(url+"ActualizarPlazas/{vuelo}/{plazas}", null, reserva.getVuelo(),plazas);
	}

	@Override
	public List<Reserva> ListarTodasReservas() {
		return reservaDao.findAll();
	}


}
