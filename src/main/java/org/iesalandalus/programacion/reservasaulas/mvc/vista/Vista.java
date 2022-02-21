package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista {
	
	private static final String ERROR = "Ha habido un error";
	private static final String NOMBRE_VALIDO = "";
	private static final String CORREO_VALIDO = "[a-zñÑA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?(?:\\.[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?)";
	private Controlador controlador;
	
	public Vista() {
		Opcion.setVista(this);
	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	public void comenzar() {
		int ordinalOpcion;
		//Bucle que solo acaba al seleccionar la opcion salir. Ordinal 0
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	public void salir() {
		controlador.terminar();
	}
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		
		try {
			Aula aula = Consola.leerAula();
			controlador.insertarAula(aula);
			System.out.println("Se ha insertado el aula.");
		} catch (OperationNotSupportedException e) {
			System.out.printf("%n%s", e.getMessage(), ERROR);
		}
	}
	public void borrarAula() { 
		Consola.mostrarCabecera("Borrar aula");
		
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Se ha borrado el aula.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.printf("%n%s", e.getMessage(), ERROR);
		}
	}	
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		
		try {
			Aula aulaBuscar = Consola.leerAula();
			controlador.buscarAula(aulaBuscar);
			if (aulaBuscar != null) {/*Si no se verifica que no es null, al buscar dará el resultado correcto y 
			tambien tantos valores null como capacidad de array restantes.*/
				aulaBuscar.toString();
			} else {
				System.out.println("No se ha encontrado el aula.");
			}
		} catch (OperationNotSupportedException e) {
			System.out.printf("%n%s", e.getMessage(), ERROR);
		}
	}
	public void listarAulas() {
		Consola.mostrarCabecera("Listar aulas");
		//Se crea un array de string y se llama al metodo para representar
		String[] string = controlador.representarAulas();
		if (string.length > 0) {
			for (String aula : string) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay ningún aula registrada.");
		}
	}
	
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.insertarProfesor(profesor);
			System.out.println("Se ha insertado el profesor.");
		} catch (OperationNotSupportedException e) {
			System.out.printf("%n%s", e.getMessage(), ERROR);
		}
	}
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar profesor");
		
		try {
			Profesor profesorBorrar = Consola.leerProfesor();
			controlador.borrarProfesor(profesorBorrar);
			System.out.println("Se ha borrado el profesor.");
		} catch (OperationNotSupportedException e) {
			System.out.printf("%n%s", e.getMessage(), ERROR);
		}
	}
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		
		try {
			Profesor profesorBuscar = Consola.leerProfesor();
			controlador.buscarProfesor(profesorBuscar);
			if (profesorBuscar != null) {
				System.out.println("Se ha encontrado el profesor: " + profesorBuscar.toString());;
			} else {
				System.out.println("No se ha encontrado el profesor.");
			}
		} catch (OperationNotSupportedException e) {
			System.out.printf("%n%s", e.getMessage(), ERROR);
		}
	}
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");
		
		String[] string = controlador.representarProfesores();
		if (string.length > 0) {
			for (String profesor : string) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay ningún profesor registrada.");
		}
	}
	
	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar reserva");
		
		try {
			Profesor profesor = Consola.leerProfesor();
			Reserva reserva = leerReserva(profesor);
			controlador.realizarReserva(reserva);
			System.out.println("Se ha realizado la reserva.");
		} catch (OperationNotSupportedException e) {
			System.out.printf("%n%s", e.getMessage(), ERROR);
		}
	}
	private Reserva leerReserva(Profesor profesor) {
		
		Aula aula = Consola.leerAula();
		Permanencia permanencia = new Permanencia(Consola.leerDia(),Consola.leerTramo());
		Reserva reserva = new Reserva(profesor, aula, permanencia);
		
		return reserva;
	}
	
	public void anularReserva() {
		Consola.mostrarCabecera("Anular reserva");
		
		try {
			Profesor profesor = Consola.leerProfesor();
			Reserva reserva = leerReserva(profesor);
			controlador.anularReserva(reserva);
			System.out.println("Se ha realizado la reserva.");
		} catch (OperationNotSupportedException e) {
			System.out.printf("%n%s", e.getMessage(), ERROR);
		}
	}
	
	public void listarReservas() {
		Consola.mostrarCabecera("Listar reservas");
		
		String[] string = controlador.representarReservas();
		if (string.length > 0) {
			for (String reservas : string) {
				System.out.println(reservas);
			}
		} else {
			System.out.println("No hay ningún aula registrada.");
		}
	}
	
	public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas por aula");
		
		Aula aulaReserva = Consola.leerAula();
		Reserva[] reservas = controlador.getReservasAula(aulaReserva);
		if (reservas.length > 0) {
			for (Reserva reservas2 : reservas) {
				if (reservas2 !=null) {
					System.out.println(reservas2);
				}
			}
			
		} else {
			System.out.println("No hay reservas para este aula.");
		}
	}
	
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listar reservas por profesor");
		
		Profesor profesor = Consola.leerProfesor();
		Reserva[] reservas = controlador.getReservasProfesor(profesor);
		if(reservas.length > 0) {
			for (Reserva reserva4 : reservas) {
				if (reserva4 != null) {
					System.out.println(reserva4);
				}
			}
		} else {
			System.out.println("Este profesor no tiene ninguna reserva");
		}
		}
	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar reservas por permanencia");
		
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		Reserva[] reservas = controlador.getReservasPermanencia(permanencia);
		if (reservas.length > 0) {
			for (Reserva reserva6 : reservas) {
				if (reserva6 != null) {
					System.out.println(reserva6);
				}
			}
		} else {
			System.out.println("No hay ninguna reserva para esta permanencia");
		}
	}
	
	public void consultarDisponibilidad() {
		//Se lee aula y permanencia. Se compara utilizando el metodo. Si esta disponible sera true.
		Aula aula = Consola.leerAula();
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		if (controlador.consultarDisponibilidadAula(aula, permanencia) == true) {
			System.out.println("Este aula esta disponible en la permanencia introducida");
		} else{
			System.out.println("Este aula no esta disponible durante la permanencia introducida");
		};
	}	
}