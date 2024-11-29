package com.Tarea3DWESOscar.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensajes")
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "fechahora")
	private LocalDateTime fechahora;
	@Column(name = "mensaje")
	private String mensaje;

	@ManyToOne
	@JoinColumn(name = "idejemplar")
	private long idejemplar;
	
	@ManyToOne
	@JoinColumn(name = "idpersona")
	private long idpersona;
	
	
	public Mensaje() {

	}

	public Mensaje(long id, LocalDateTime fechahora, String mensaje) {
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getFechahora() {
		return fechahora;
	}

	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechahora, id, mensaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(fechahora, other.fechahora) && id == other.id && Objects.equals(mensaje, other.mensaje);
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechahora=" + fechahora + ", mensaje=" + mensaje + "]";
	}

}
