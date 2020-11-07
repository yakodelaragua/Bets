package domain;

import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	private String nombre;
	private String contrasena;
	private String DNI;
	@Id
	private String nombreUsuario;
	private double cartera;
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Bet> apuestas = new Vector<Bet>();

	public User(String nombre, String contrasena, String dNI, String nombreUsuario) {
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.DNI = dNI;
		this.nombreUsuario = nombreUsuario;
		this.cartera = 0;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public double getCartera() {
		return cartera;
	}

	public void setCartera(double cartera) {
		this.cartera = cartera;
	}
	
	public Vector<Bet> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Vector<Bet> apuestas) {
		this.apuestas = apuestas;
	}

	public void anadirApuesta(Bet apuesta) {
		this.apuestas.add(apuesta);
	}
	
	public void eliminarApuesta(Bet apuesta) {
		this.apuestas.remove(apuesta);
	}

}
