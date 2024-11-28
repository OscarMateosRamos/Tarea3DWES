package com.Tarea3DWESOscar.modelo;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "personas")

public class Persona {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
	private long id;

//	@Column(name = "nombre")
	private String nombre;

//	@Column(name = "email")
	private String mensaje;

	public Persona() {

	}

	public Persona(long id, String nombre, String mensaje) {
		this.id = id;
		this.nombre = nombre;
		this.mensaje = mensaje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, mensaje, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return id == other.id && Objects.equals(mensaje, other.mensaje) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", mensaje=" + mensaje + "]";
	}

}
