package com.Tarea3DWESOscar.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="plantas")
public class Planta {

	private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String codigo;
	
	@Column
	private String nombreComun;
	
	@Column
	private String nombreCientifico;
//	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="idplanta")
//	private List<Ejemplar> listaEjemplares = new LinkedList<Ejemplar>();
	
	public Planta() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

//	public List<Ejemplar> getListaEjemplares() {
//		return listaEjemplares;
//	}
//
//	public void setListaEjemplares(List<Ejemplar> listaEjemplares) {
//		this.listaEjemplares = listaEjemplares;
//	}
	
	
	
	

	
}
