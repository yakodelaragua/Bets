package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bet {
@Id @GeneratedValue
	private int cantNumber;
	private User usuario;
	private double cantidad;
	private Question preguntaAsociada;
	private Event eventoAsociado;
	
	public Event getEventoAsociado() {
		return eventoAsociado;
	}

	public void setEventoAsociado(Event eventoAsociado) {
		this.eventoAsociado = eventoAsociado;
	}

	public Bet(double cantidad, User u, Question pregunta, Event eventoAsociado) {
		super();
		this.cantidad = cantidad;
		this.usuario = u;
		this.preguntaAsociada= pregunta;
		this.eventoAsociado= eventoAsociado;
	}

	public Question getPreguntaAsociada() {
		return preguntaAsociada;
	}

	public void setPreguntaAsociada(Question preguntaAsociada) {
		this.preguntaAsociada = preguntaAsociada;
	}

	public int getCantNumber() {
		return cantNumber;
	}

	public void setCantNumber(int cantNumber) {
		this.cantNumber = cantNumber;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
}
	
	