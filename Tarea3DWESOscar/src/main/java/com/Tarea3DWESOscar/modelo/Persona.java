package com.Tarea3DWESOscar.modelo;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas")

public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idcredencial")
	private Credenciales idceredencial;
	
	
	public Persona() {

	}

	
	public Persona(long id, String nombre, Credenciales idceredencial) {
		this.id = id;
		this.nombre = nombre;
		this.idceredencial = idceredencial;
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


	public Credenciales getIdceredencial() {
		return idceredencial;
	}


	public void setIdceredencial(Credenciales idceredencial) {
		this.idceredencial = idceredencial;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, idceredencial, nombre);
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
		return id == other.id && idceredencial == other.idceredencial && Objects.equals(nombre, other.nombre);
	}


	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", idceredencial=" + idceredencial + "]";
	}


	
	

}
