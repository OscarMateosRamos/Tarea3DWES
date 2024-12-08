package com.Tarea3DWESOscar;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Tarea3DWESOscar.servicios.Controlador;
import com.Tarea3DWESOscar.servicios.ServiciosPlanta;
import com.Tarea3DWESOscar.vista.ViveroFachadaAdmin;
import com.Tarea3DWESOscar.vista.ViveroFachadaInvitado;

@SpringBootApplication
public class Principal implements CommandLineRunner {
	@Autowired
	ServiciosPlanta servplanta;

	@Autowired
	ViveroFachadaInvitado fachadaInvitado;

	@Autowired
	ViveroFachadaAdmin fachadaAdmin;
	@Autowired
	Controlador controlador;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("INI");

		// fachadaAdmin.mostrarMenuPrincipal();

		int opcion = 0;

		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---MENU PRINCIPAL---");
			System.out.println("1.  ENTRAR COMO INVITADO");
			System.out.println("2.  PERSONAL.");
			System.out.println("3.  ADMINISTRADOR.");
			System.out.println("4.  Salir");
			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:
					fachadaInvitado.mostrarMenuPrincipal();
					break;
				case 2:
					/*
					 * sc = new Scanner(System.in); System.out.println(" Introduce Usuario"); String
					 * usuario = sc.nextLine(); System.out.println("Introduce Contraseña"); String
					 * password = sc.nextLine();
					 * 
					 * //boolean userOk =
					 * Controlador.getServicios().getServiciosCredenciales().validarCredencial(
					 * usuario, // password); //if (userOk) { // controlador.setUsername(usuario);
					 * System.out.println("Usuario y password Correcto");
					 * 
					 * //ViveroFachadaPersonal portalPersonal = ViveroFachadaPersonal.getPortal();
					 * //portalPersonal.getPortal().mostrarMenuPrincipal(); } else {
					 * System.out.println("Usuario y password Erroneo"); }
					 */
					break;

				case 3:

					sc = new Scanner(System.in);
					System.out.println(" Introduce Usuario");
					String usuario = sc.nextLine();
					System.out.println("Introduce Contraseña");
					String password = sc.nextLine();

					if (usuario.equals("admin") && password.equals("admin")) {
						System.out.println("--Bienvenido Admin--");
						controlador.setUsername("admin");

						controlador.setUsernameID(Long.valueOf(1));

						fachadaAdmin.mostrarMenuPrincipal();

					} else {
						System.out.println("Usuario y Password erroneas");
					}

					break;

				case 4:
					controlador.cerrarSesion();
					System.out.println("-----------------");
					System.out.println("Fin del programa");
					System.out.println("-----------------");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Tienes que introducir un numero....Repita el proceso");
				sc.nextLine();
				opcion = 0;
			}

		} while (opcion != 4);

		System.out.println("------------------------");

		System.out.println("FIN");

	}
}
