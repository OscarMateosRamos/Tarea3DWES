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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "mensajes")
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "fechahora")
	@Temporal(TemporalType.TIME)
	private LocalDateTime fechahora;
	@Column(name = "mensaje")
	private String mensaje;

	@ManyToOne
	@JoinColumn(name = "idejemplar")
	private Long idejemplar;

	@ManyToOne
	@JoinColumn(name = "idpersona")
	private Persona persona;

	public Mensaje() {

	}

	public Mensaje(Long id, LocalDateTime fechahora, String mensaje, Long idejemplar, Persona persona) {
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.idejemplar = idejemplar;
		this.persona = persona;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return Objects.hash(fechahora, id, idejemplar, mensaje, persona);
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
		return Objects.equals(fechahora, other.fechahora) && Objects.equals(id, other.id)
				&& Objects.equals(idejemplar, other.idejemplar) && Objects.equals(mensaje, other.mensaje)
				&& Objects.equals(persona, other.persona);
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechahora=" + fechahora + ", mensaje=" + mensaje + ", idejemplar=" + idejemplar
				+ ", persona=" + persona + "]";
	}

}
