package com.Tarea3DWESOscar.vista;

import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Tarea3DWESOscar.modelo.Credencial;
import com.Tarea3DWESOscar.modelo.Ejemplar;
import com.Tarea3DWESOscar.modelo.Mensaje;
import com.Tarea3DWESOscar.modelo.Persona;
import com.Tarea3DWESOscar.modelo.Planta;
import com.Tarea3DWESOscar.repositories.CredencialRepository;
import com.Tarea3DWESOscar.repositories.EjemplarRepository;
import com.Tarea3DWESOscar.repositories.PersonaRepository;
import com.Tarea3DWESOscar.repositories.PlantaRepository;
import com.Tarea3DWESOscar.servicios.ServiciosCredenciales;
import com.Tarea3DWESOscar.servicios.ServiciosEjemplar;
import com.Tarea3DWESOscar.servicios.ServiciosMensaje;
import com.Tarea3DWESOscar.servicios.ServiciosPersona;
import com.Tarea3DWESOscar.servicios.ServiciosPlanta;

@Component
public class ViveroFachadaAdmin {

	@Autowired
	ServiciosPlanta servPlanta;

	@Autowired
	ServiciosPersona servPersona;

	@Autowired
	ServiciosCredenciales servCredencial;

	@Autowired
	ServiciosEjemplar servEjemplar;

	@Autowired
	ServiciosMensaje servMensaje;

	@Autowired
	PlantaRepository plantarepo;

	@Autowired
	PersonaRepository personarepo;

	@Autowired
	CredencialRepository credencialrepo;

	@Autowired
	EjemplarRepository ejemplarrepo;

	public void mostrarMenuPrincipal() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Seleccione una opcion:");
			System.out.println("1.  Gestionar Plantas.");
			System.out.println("2.  Gestionar Personas.");
			System.out.println("3.  Gestionar Mensajes.");
			System.out.println("4.  Gestionar Ejemplares.");
			System.out.println("5.  Salir");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 5) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					menuPlantas();
					break;
				case 2:
					menuPersonas();
					break;
				case 3:
					menuMensajes();
					break;
				case 4:
					menuEjemplares();
					break;

				case 5:
//					Controlador.getServicios().setUsername("invitado");
					System.out.println("Salida del menu de Administrador...");
					break;
				}
			} catch (InputMismatchException e) {

				System.out.println("Introduce un número.... Repita el proceso");
				sc.nextLine();
				opcion = 0;
			}

		} while (opcion != 5);

	}

	private void menuEjemplares() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---Gestion de Ejemplares:---");
			System.out.println("1.  Crear ejemplar");
			System.out.println("2.  Ver ejemplares por tipo de planta.");
			System.out.println("3.  Ver mensajes de un ejemplar.");
			System.out.println("4.  Salir");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					crearEjemplar();
					break;
				case 2:
					verEjemplaresPorTipoPlanta();
					break;
				case 3:
					// verMensajesPorEjemplar();
					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero.... Repita el proceso");
				sc.nextLine();
				opcion = 0;

			}

		} while (opcion != 4);

	}

	private void crearEjemplar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("***Creando ejemplar");
		Ejemplar ej = new Ejemplar();
		System.out.print("Código de la planta del ejemplar: ");
		String codigo = sc.next().trim().toUpperCase();

		if (servPlanta.existeCodigoPlanta(codigo)) {
			System.out.println("Existe el codigo");

			ej.setPlanta(plantarepo.findByCodigo(codigo).get());
			ej.setNombre(codigo);

			servEjemplar.insertarEjemplar(ej);

		} else {
			System.out.println("--- NO Existe el codigo");

		}

	}

	private void verEjemplaresPorTipoPlanta() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el tipo de planta: ");
		String tipo = sc.next();

		List<Ejemplar> ejemplares = servEjemplar.listaejemplaresPorTipoPlanta(tipo);

		for (Ejemplar e : ejemplares) {
			System.out.println("Codigo: " + e.getNombre());
		}
	}

	private void menuPlantas() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---Gestion de plantas:---");
			System.out.println("1.  Ver Plantas.");
			System.out.println("2.  Crear una Planta.");
			System.out.println("3.  Modificar Plantas.");
			System.out.println("4.  Salir");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					servPlanta.vertodasPlantas();
					break;
				case 2:

					String codigo;
					String nombrecomun;
					String nombrecientifico;

					Planta pl = new Planta();
					do {
						sc = new Scanner(System.in);

						System.out.println("Dame el codigo de la nueva Planta");
						codigo = sc.nextLine();

						pl.setCodigo(codigo.trim().toUpperCase());

						System.out.println("Dame el nombre común de la nueva Planta ");
						nombrecomun = sc.nextLine();
						pl.setNombrecomun(nombrecomun);

						System.out.println("Dame el nombre científico de la nueva Planta ");
						nombrecientifico = sc.nextLine();
						pl.setNombrecientifico(nombrecientifico);

						if (!servPlanta.validarPlanta(pl)) {
							System.out.println("Planta invalida... Repita el proceso");
						}

					} while (!servPlanta.validarPlanta(pl));

					servPlanta.insertarPlanta(pl);
					break;
				case 3:
					menuModificarPlanta();
					break;

				}
			} catch (InputMismatchException e) {

				System.out.println("Introduce un número.... Repita el proceso");
				sc.nextLine();
				opcion = 0;
			}

		} while (opcion != 4);

	}

	private void menuModificarPlanta() {

		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---Modificar Planta:---");
			System.out.println("1.  Modificar nombre comun.");
			System.out.println("2.  Modificar nombre cientifico.");
			System.out.println("3.  Salir");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 3) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					System.out.println("Introduce el codigo de la planta a modificar");
					String codigo = sc.next();
					Optional<Planta> pl = plantarepo.findByCodigo(codigo);
					if (!pl.isEmpty()) {
						sc.nextLine();
						System.out.println("Introduce el nuevo nombre comun");
						String nuevo = sc.nextLine();
						pl.get().setNombrecomun(nuevo);
						servPlanta.modificarPlanta(pl.get());
					} else {
						System.out.println("El codigo no existe");
					}
					break;
				case 2:
					System.out.println("Introduce el codigo de la planta a modificar");
					codigo = sc.next();
					Optional<Planta> pl2 = plantarepo.findByCodigo(codigo);
					if (!pl2.isEmpty()) {
						sc.nextLine();
						System.out.println("Introduce el nuevo nombre cientifico");
						String nuevo = sc.nextLine();
						pl2.get().setNombrecientifico(nuevo);
						servPlanta.modificarPlanta(pl2.get());
					} else {
						System.out.println("El codigo no existe");
					}

					break;

				case 3:
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Introduce un número.... Repita el proceso");
				sc.nextLine();
				opcion = 0;
			}
		} while (opcion != 3);

	}

	public void menuMensajes() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---Gestion de Mensajes:---");
			System.out.println("1.  Crear Mensaje");
			System.out.println("2.  Ver todos los Mensajes (SIN FILTRAR).");
			System.out.println("3.  Ver Mensajes (FILTRADO).");
			System.out.println("4.  Salir");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					crearMensaje();
					break;
				case 2:
					servMensaje.verTodosMensajes();
					break;
				case 3:
					menuFiltrarMensaje();

					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero.... Repita el proceso");
				sc.nextLine();
				opcion = 0;

			}

		} while (opcion != 4);

	}

	private void crearMensaje() {
		Mensaje m = new Mensaje();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el nombre del ejemplar");
		String nombre = sc.next();

		List<Ejemplar> ejemplares = ejemplarrepo.findAll();
		Ejemplar ej = new Ejemplar();
		Boolean existe = false;
		for (Ejemplar e : ejemplares) {
			if (e.getNombre().equals(nombre)) {
				ej = e;
				existe = true;
			}
		}
		if (existe == false) {
			System.out.println("No existe el ejemplar");
		} else {
			System.out.println("Introduce el mensaje: ");
			String mensaje = sc.next();

			Optional<Persona> personas = servPersona.buscarPorId(Long.valueOf(1));
			m.setPersona(personas.get());
			m.setEjemplar(ej);

			LocalDate fechahora = LocalDate.now();
			Date date = Date.valueOf(fechahora);
			m.setFechahora(date);
			m.setMensaje(mensaje);

			servMensaje.insertar(m);
		}
	}

	private void menuFiltrarMensaje() {

		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---Ver Mensaje:---");
			System.out.println("1.  Ver mensaje filtrado por rango de fechas.");
			System.out.println("2.  Ver mensaje filtrado por persona.");
			System.out.println("3.  Ver mensaje filtrado por tipo de planta.");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					break;
				case 2:
					System.out.println("Introduce el id de persona");
					long idPersona = sc.nextLong();

					//mensajesServ.verMensajeIdPersona(idPersona);
					break;

				case 3:
					System.out.println("Introduce el id de Ejemplar");
					long idEjemplar = sc.nextLong();

					// mensajesServ.verMensajeIdEjemplar();//* Sacar la planta con el id de Ejemplar

					break;

				case 4:
					break;
				}
			} catch (InputMismatchException e) {

				System.out.println("Introduce un número.... Repita el proceso");
				sc.nextLine();
				opcion = 0;
			}

		} while (opcion != 4);

	}

	private void menuPersonas() {

		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---Gestion de personas:---");
			System.out.println("1.  Ver Personas.");
			System.out.println("2.  Añadir persona.");
			System.out.println("3.  Salir");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 3) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:

					servPersona.verTodasPersonas();

					break;
				case 2:
					boolean personaValida = false;
					boolean formatoOK = false;

					Persona p = new Persona();
					sc = new Scanner(System.in);

					System.out.println("Dame el nombre de la nueva Persona");
					String nombre = sc.nextLine();

					System.out.println("Dame el email de la nueva Persona");
					String email = sc.nextLine();

					System.out.println("Introduce el nombre de usuario");
					String usuario = sc.nextLine();

					System.out.println("Introduce el password");
					String password = sc.nextLine();

					if (servCredencial.existeCredencial(usuario)) { // si ya existe el usuario en Credenciales
						System.out.println("Ya existe ese  usuario en credenciales: " + usuario);
					} else {
						personaValida = servPersona.validarPersona(email, nombre);
						if (!personaValida) {
							System.out.println(" Persona datos no validos... Repita el proceso");
						} else {
							Credencial c = new Credencial();
							c.setUsuario(usuario);
							c.setPassword(password);

							p.setNombre(nombre);
							p.setEmail(email);
							p.setCredencial(c);
							servPersona.insertarPersona(p);
						}

					}

					break;
				case 3:

					break;
				}
			} catch (InputMismatchException e) {

				System.out.println("Introduce un número.... Repita el proceso");
				sc.nextLine();
				opcion = 0;
			}

		} while (opcion != 3);
	}

}
