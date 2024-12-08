package com.Tarea3DWESOscar.vista;

import java.util.InputMismatchException;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Tarea3DWESOscar.modelo.Planta;
import com.Tarea3DWESOscar.servicios.ServiciosPlanta;

@Component
public class ViveroFachadaInvitado {
	@Autowired
	ServiciosPlanta servPlanta;

	public void mostrarMenuPrincipal() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("--Menu Invitado--:");
			System.out.println("1.  Ver plantas.");
			System.out.println("2.  Salir.");

			try {
				opcion = sc.nextInt();
				if (opcion < 1 || opcion > 2) {
					System.out.println("Opción incorrecta.");
					continue;
				}
				switch (opcion) {
				case 1:

					servPlanta.vertodasPlantas();
					break;

				case 2:
					System.out.println("Salida del menu del menu invitado...");
					System.out.println("");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Introduce un numero.... Repita el proceso");
				sc.nextLine();
				opcion = 0;
			}

		} while (opcion != 2);

	}

}
