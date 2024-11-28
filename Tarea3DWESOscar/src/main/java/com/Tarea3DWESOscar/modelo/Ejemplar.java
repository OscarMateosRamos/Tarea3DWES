package com.Tarea3DWESOscar.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
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
import jakarta.persistence.Table;

@Entity
@Table(name = "ejemplares")
public class Ejemplar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "idplanta")
	private Planta planta;

//	@OneToMany(cascade = CascadeType.ALL)
//
//	@JoinColumn(name = "idejemplar")
//	private List<Mensaje> mensajes = new LinkedList<Mensaje>();

	public Ejemplar() {

	}

	public Ejemplar(long id, String nombre, Planta planta, List<Mensaje> mensajes) {
		this.id = id;
		this.nombre = nombre;
		this.planta = planta;
//		this.mensajes = mensajes;
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

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

//	public List<Mensaje> getMensajes() {
//		return mensajes;
//	}
//
//	public void setMensajes(List<Mensaje> mensajes) {
//		this.mensajes = mensajes;
//	}

}
