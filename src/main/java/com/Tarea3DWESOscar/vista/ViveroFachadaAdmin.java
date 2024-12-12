package com.Tarea3DWESOscar.vista;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.Tarea3DWESOscar.repositories.MensajeRepository;
import com.Tarea3DWESOscar.repositories.PersonaRepository;
import com.Tarea3DWESOscar.repositories.PlantaRepository;
import com.Tarea3DWESOscar.servicios.Controlador;
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
	Controlador controlador;

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

	public void menuEjemplares() {
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

					verMensajesPorEjemplar();
					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero.... Repita el proceso");
				sc.nextLine();
				opcion = 0;

			}

		} while (opcion != 4);

	}

	private void verMensajesPorEjemplar() {
		Scanner sc = new Scanner(System.in);
		ListarEjemplaresIdNombre();
		System.out.println("Introduce el id del Ejemplar: ");
		Long id = sc.nextLong();

		if (!servEjemplar.existeIdEjemplar(id)) {
			System.out.println("El id del Ejemplar no existe");
		} else {
			System.out.println("---LISTADO DE MENSAJES POR ID DE EJEMPLAR: " + id + "----");
			List<Mensaje> mensajes = servMensaje.listamensajesPorIdEjemplar(id);

			for (Mensaje m : mensajes) {
				System.out.println("Mensaje: " + m.getMensaje() + " Fecha: " + m.getFechahora() + " CREADO POR: "
						+ m.getPersona().getNombre());
			}
		}

	}

	private void crearEjemplar() {
		List<Planta> plantas = servPlanta.encontrartodasPlantas();
		System.out.println("-----LISTADO DE PLANTAS------");
		for (Planta p : plantas) {
			System.out.println("Id: " + p.getId() + "Codigo: " + p.getCodigo());
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("Creado nuevo ejemplar de: ");
		Ejemplar ej = new Ejemplar();
		System.out.print("Código de la planta del ejemplar: ");
		String codigo = sc.next().trim().toUpperCase();

		if (servPlanta.existeCodigoPlanta(codigo)) {

			ej.setPlanta(servPlanta.encontrarPlantasPorCodigo(codigo).get(0));
			ej.setNombre(codigo);

			servEjemplar.insertarEjemplar(ej);
			System.out.println("Creado ejemplar: " + codigo);

		} else {
			System.out.println("--- NO Existe el codigo");

		}
	}

	private void verEjemplaresPorTipoPlanta() {
		ListarPlantas();

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el codigo de la Planta: ");
		String codigo = sc.next();

		if (!servPlanta.existeCodigoPlanta(codigo)) {
			System.out.println("El codigo de la planta no existe");
		} else {
			System.out.println("---LISTADO DE EJEMPLARES POR TIPO DE PLANTA: " + codigo + "----");
			List<Ejemplar> ejemplares = servEjemplar.listaejemplaresPorTipoPlanta(codigo);
			if (ejemplares.size() == 0) {
				System.out.println("No hay ejemplares correspondientes a la planta " + codigo);
			} else {
				for (Ejemplar e : ejemplares) {
					System.out.println("Id: " + e.getId() + " Nombre: " + e.getNombre());
				}
			}
			System.out.println("--------------------------------");
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
					ListarPlantas();
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

	public void ListarPlantas() {
		System.out.println("----LISTADO DE PLANTAS-----");
		List<Planta> plantas = servPlanta.vertodasPlantas();

		for (Planta p : plantas) {
			System.out.println("Id: " + p.getId() + " Codigo: " + p.getCodigo() + " Nombre Comun: " + p.getNombrecomun()
					+ " Nombre Cientifico: " + p.getNombrecientifico());
		}
		System.out.println("-----------------------------");
	}

	public void ListarEjemplaresIdNombre() {
		System.out.println("----LISTADO DE EJEMPLARES-----");
		List<Ejemplar> ejemplares = servEjemplar.vertodosEjemplares();

		for (Ejemplar e : ejemplares) {
			System.out.println("Id: " + e.getId() + " Nombre: " + e.getNombre());
		}
		System.out.println("-----------------------------");
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
					List<Planta> plantas = servPlanta.encontrarPlantasPorCodigo(codigo);
					if (!plantas.isEmpty()) {
						sc.nextLine();
						System.out.println("Introduce el nuevo nombre comun");
						String nuevo = sc.nextLine();

						plantas.get(0).setNombrecomun(nuevo);
						servPlanta.modificarPlanta(plantas.get(0));
					} else {
						System.out.println("El codigo no existe");
					}
					break;
				case 2:
					System.out.println("Introduce el codigo de la planta a modificar");
					codigo = sc.next();
					List<Planta> plantas2 = servPlanta.encontrarPlantasPorCodigo(codigo);
					if (!plantas2.isEmpty()) {
						sc.nextLine();
						System.out.println("Introduce el nuevo nombre cientifico");
						String nuevo = sc.nextLine();

						plantas2.get(0).setNombrecientifico(nuevo);

						servPlanta.modificarPlanta(plantas2.get(0));
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
					System.out.println("---LISTADO DE MENSAJES-----");
					servMensaje.verTodosMensajes();
					System.out.println("---------------------------");
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

		if (!servEjemplar.existeNombreEjemplar(nombre)) {
			System.out.println("No existe el ejemplar");
		} else {
			Ejemplar ej = servEjemplar.buscarPorNombre(nombre);
			Persona p = servPersona.buscarPorNombre(controlador.getUsername());

			m.setPersona(p);
			m.setEjemplar(ej);

			System.out.println("Introduce el mensaje: ");
			String mensaje = sc.next();

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
			System.out.println("---Menu filtar mensaje:---");
			System.out.println("1.  Ver mensaje filtrado por rango de fechas.");
			System.out.println("2.  Ver mensaje filtrado por persona.");
			System.out.println("3.  Ver mensaje filtrado por tipo de planta.");
			System.out.println("4.  Salir.");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					// verMensajesPorFechas();
					break;
				case 2:
					verMensajesPorIdPersona();
					break;
				case 3:
					verMensajesPorTipoPlanta();

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

	private void verMensajesPorTipoPlanta() {
		Scanner sc = new Scanner(System.in);
		ListarPlantas();

		System.out.println("Introduce el codigo de la planta: ");
		String codigo = sc.next();

		if (!servPlanta.existeCodigoPlanta(codigo)) {
			System.out.println("No existe el codigo");
		} else {

			System.out.println("----LISTADO DE MENSAJES DE PLANTAS: " + codigo);
			List<Mensaje> mensaje = servMensaje.listamensajesPorCodigoPlanta(codigo);

			for (Mensaje m : mensaje) {
				System.out.println("Id: " + m.getId() + " Ejemplar: " + m.getEjemplar().getNombre() + " Fecha: "
						+ m.getFechahora() + " Mensaje: " + m.getMensaje());
			}
			System.out.println("-----------------------------");
		}
	}
//
//	private void verMensajesPorFechas() {
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("Introduce la fecha inicial: ");
//		String fechaInicial = sc.nextLine();
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate fecha = LocalDate.parse(fechaInicial, formatter);
//
//		System.out.println("Introduce la fecha final: ");
//		String fechaFinal = sc.nextLine();
//
//		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		fecha = LocalDate.parse(fechaFinal, formatter);
//
//		if (!servMensaje.validarFecha(fechaFinal) && !servMensaje.validarFecha(fechaInicial)) {
//			System.out.println("Has introducido una fecha con un formato invalido o no has introducido una fecha....");
//		} else {
//
//			System.out.println(
//					"----LISTADO DE MENSAJES DE FECHA INICIAL: " + fechaInicial + " Y FECHA FINAL: " + fechaFinal);
//			List<Mensaje> mensaje = servMensaje.listamensajesPorFechas(fechaInicial, fechaFinal);
//
//			for (Mensaje m : mensaje) {
//				System.out.println("Id: " + m.getId() + " Ejemplar: " + m.getEjemplar().getNombre() + " Fecha: "
//						+ m.getFechahora() + " Mensaje: " + m.getMensaje());
//			}
//			System.out.println("-----------------------------");
//		}
//	}

	public void verMensajesPorIdPersona() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el id de persona");
		long idPersona = sc.nextLong();

		if (!servPersona.existeidPersona(idPersona)) {
			System.out.println("El id de la persona no existe");
		} else {
			List<Mensaje> mensajes = servMensaje.listamensajesPorIdPersona(idPersona);

			System.out.println("----LISTADO DE MENSAJES DE ID: " + idPersona);

			for (Mensaje m : mensajes) {
				System.out.println("Id: " + m.getId() + " Ejemplar: " + m.getEjemplar().getNombre() + " Fecha: "
						+ m.getFechahora() + " Mensaje: " + m.getMensaje());
			}
			System.out.println("-----------------------------");
		}

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
					String email = sc.nextLine().trim();

					System.out.println("Introduce el nombre de usuario");
					String usuario = sc.nextLine().trim();

					System.out.println("Introduce el password");
					String password = sc.nextLine().trim();

					if (servCredencial.existeCredencial(usuario)) { // si ya existe el usuario en Credenciales
						System.out.println("Ya existe ese  usuario en credenciales: " + usuario);
					} else {
						personaValida = servPersona.validarPersona(nombre, email);
						if (personaValida) {
							System.out.println(" Persona datos no validos... Repita el proceso");
						} else {
							Credencial c = new Credencial();

							c.setUsuario(usuario);
							c.setPassword(password);

							if (!servCredencial.validarUsuarioPassword(c)) {
								System.out.println("Credenciales no validas");
							} else {

								p.setNombre(nombre);
								p.setEmail(email);
								p.setCredencial(c);
								servPersona.insertarPersona(p);
							}

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
