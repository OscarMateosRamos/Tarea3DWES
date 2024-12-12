package com.Tarea3DWESOscar.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Tarea3DWESOscar.servicios.ServiciosEjemplar;
import com.Tarea3DWESOscar.servicios.ServiciosPlanta;

@Component
public class ViveroFachadaPersonal {
	@Autowired
	ServiciosPlanta servPlanta;

	@Autowired
	ServiciosEjemplar servEjemplar;
	
	@Autowired
	ViveroFachadaAdmin fachadaAdmin;

	public void mostrarMenuPrincipal() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("--Menu Personal--");
			System.out.println("Seleccione una opcion:");
			System.out.println("1. Ver todas las Plantas");
			System.out.println("2. Gestionar Ejemplares.");
			System.out.println("3. Gestionar Mensajes.");
			System.out.println("4. Salir");

			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					fachadaAdmin.ListarPlantas();
					break;
				case 2:

					fachadaAdmin.menuEjemplares();
					break;
				case 3:
					fachadaAdmin.menuMensajes();
					break;
				case 4:
					System.out.println("Salida del menu del menu personal...");
					System.out.println("");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero.... Repita el proceso");
				sc.nextLine();
				opcion = 0;
			}

		} while (opcion != 4);

	}

//
//	public void menuEjemplares() {
//		int opcion = 0;
//		Scanner sc = new Scanner(System.in);
//		do {
//			System.out.println("---Gestion de Ejemplares:---");
//			System.out.println("1.  Crear ejemplar");
//			System.out.println("2.  Ver ejemplares por tipo de planta.");
//			System.out.println("3.  Ver mensajes de un ejemplar.");
//			System.out.println("4.  Salir");
//			try {
//				opcion = sc.nextInt();
//				if (opcion < 1 || opcion > 4) {
//					System.out.println("Opción incorrecta.");
//					continue;
//				}
//				switch (opcion) {
//				case 1:
//					Ejemplar ej = new Ejemplar();
//					servEjemplar.insertarEjemplar(ej);
//					break;
//				case 2:
//
//					break;
//				case 3:
//
//					break;
//
//				}
//			} catch (InputMismatchException e) {
//				System.out.println("Introduce un numero.... Repita el proceso");
//				sc.nextLine();
//				opcion = 0;
//
//			}
//
//		} while (opcion != 4);
//
//	}

//
//	public void crearEjemplar() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("***Creando ejemplar");
//		Ejemplar ej = new Ejemplar();
//		System.out.print("Código de la planta del ejemplar: ");
//		String codigo = sc.next().trim().toUpperCase();
//
//		if (Controlador.getServicios().getServiciosPlanta().existePorCodigo(codigo)) {
//			System.out.println("Existe el codigo");
//			ej.setIdPlanta(codigo);
//			ej.setNombre(codigo);
//
//			long idNuevo = Controlador.getServicios().getServiciosEjemplar().insertarEjemplar(ej);
//
//			String nuevoNombre = codigo + "_" + idNuevo;
//			Controlador.getServicios().getServiciosEjemplar().modificarNombreEjemplar(idNuevo, nuevoNombre);
//
//			LocalDateTime fechahora = LocalDateTime.now();
//			String username = Controlador.getServicios().getUsername();
//			String mensaje = "Añadido nuevo ejemplar " + ej.getNombre() + " por " + username + " (" + fechahora + " ).";
//			long idCredencial = Controlador.getServicios().getServiciosCredenciales().buscarIdPorUsuario(username);
//			long idPersona = Controlador.getServicios().getServiciosPersona().buscarPorIDCredencial(idCredencial)
//					.getId();
//
//			Mensaje m = new Mensaje(fechahora, mensaje, idNuevo, idPersona);
//
//			Controlador.getServicios().getServiciosMensaje().insertarMensaje(m);
//		} else {
//			System.out.println("--- NO Existe el codigo");
//
//		}
//
//	}
//
//	
//	public void mostrarMensajeTipoPlanta() {
//		try {
//			Scanner sc = new Scanner(System.in);
//			System.out.println("Introduce el código de la planta");
//			String codigo = sc.next().trim().toUpperCase();
//
//			boolean codigoOk = Controlador.getServicios().getServiciosPlanta().existePorCodigo(codigo);
//			if (codigoOk) {
//				ArrayList<Mensaje> mensajes = Controlador.getServicios().getServiciosMensaje()
//						.mostrarMensajeTipoPlanta(codigo);
//
//				if (mensajes.isEmpty()) {
//					System.out.println("No hay mensajes con código: " + codigo);
//				} else {
//					System.out.println("---- Mensajes con código: " + codigo);
//					for (Mensaje m : mensajes) {
//						System.out.println(m);
//					}
//				}
//
//			} else {
//				System.out.println("No hay plantas con el codigo : " + codigo);
//
//			}
//		} catch (Exception e) {
//			System.out.println("Error al  mostar mensajes por codigo planta " + e.getMessage());
//		}
//
//	}
//
//	public void mostrarMensajePorEjemplar() {
//		try {
//			Scanner sc = new Scanner(System.in);
//			System.out.println("Introduce el nombre del Ejemplar");
//			String nombre = sc.next().trim();
//
//			boolean nombreOk = Controlador.getServicios().getServiciosEjemplar().existePorNombre(nombre);
//			if (nombreOk) {
//				long id = controlador.getServiciosEjemplar().buscarIDPorNombre(nombre);
//				ArrayList<Mensaje> mensajes = Controlador.getServicios().getServiciosMensaje()
//						.mostrarMensajeIdEjemplar(id);
//
//				if (mensajes.isEmpty()) {
//					System.out.println("No hay mensajes con el nombre: " + nombre);
//				} else {
//					System.out.println("---- Mensajes del ejemlar : " + nombre);
//					for (Mensaje m : mensajes) {
//						System.out.println(m);
//					}
//				}
//
//			} else {
//				System.out.println("No hay ejemplares con el nombre: " + nombre);
//
//			}
//		} catch (Exception e) {
//			System.out.println("Error al  mostar mensajes por codigo planta " + e.getMessage());
//		}
//
//	}
//
//
//	public void crearMensaje() {
//		Scanner sc = new Scanner(System.in);
//		Mensaje nuevoMensaje = new Mensaje();
//		String nombreEj = null;
//		String texto = null;
//
//		try {
//			System.out.println("Introduce el nombre del ejemplar");
//			nombreEj = sc.next();
//
////			boolean nombreOk = Controlador.getServicios().getServiciosEjemplar().existePorNombre(nombreEj);
//			if (nombreOk) {
//				sc.nextLine();
//				System.out.println("Introduce el mensaje: ");
//				texto = sc.nextLine();
//				LocalDateTime fechahora = LocalDateTime.now();
//				String username = Controlador.getServicios().getUsername();
//				long idCredencial = Controlador.getServicios().getServiciosCredenciales().buscarIdPorUsuario(username);
//				long idPersona = Controlador.getServicios().getServiciosPersona().buscarPorIDCredencial(idCredencial)
//						.getId();
//				long idEjemplar = Controlador.getServicios().getServiciosEjemplar().buscarIDPorNombre(nombreEj);
//
//				Mensaje m = new Mensaje(fechahora, texto, idEjemplar, idPersona);
//				Controlador.getServicios().getServiciosMensaje().insertarMensaje(m);
//
//			} else {
//				System.out.println("No existe el nombre de ejemplar: " + nombreEj);
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//	}

}
