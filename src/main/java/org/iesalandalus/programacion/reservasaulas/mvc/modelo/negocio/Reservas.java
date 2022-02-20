package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	
	private int capacidad;
	private int tamano;
	private Reserva[] coleccionReservas;
	
	public Reservas(int capacidad) {
		
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		this.tamano = 0;
		coleccionReservas = new Reserva[capacidad];
	}
	public Reserva[] get() {
		
		return copiaProfundaReservas();
	}
	private Reserva[] copiaProfundaReservas() {
		
		Reserva[] copiaProfunda = new Reserva[capacidad];
		
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i] != null) {
				copiaProfunda[i] = new Reserva(coleccionReservas[i]);
			}
		}
		return coleccionReservas;
	}
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		}
		int indice = buscarIndice(reserva);
		
		if (tamanoSuperado(indice)) {
			coleccionReservas[tamano] = new Reserva(reserva);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un reserva con ese nombre.");
		}
		tamano++;
	}
	public Reserva buscar(Reserva reserva) {
		
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}
		int indice = buscarIndice(reserva);
		
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Reserva(coleccionReservas[indice]);
		}
	}
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		
		int indice;
		
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}
		indice = buscarIndice(reserva);
		
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
		}
		desplazarUnaPosicionHaciaIzquierda(indice);
		tamano--;
	}
	private int buscarIndice(Reserva reserva) {
		
		boolean buscar = false;
		int indice = tamano + 1;
		
		for (int i = 0; i < tamano&&!buscar; i++) {
			
			if (coleccionReservas[i].equals(reserva)) {
				buscar = true;
				indice = i;
			}
		}
		return indice;
	}
	private boolean tamanoSuperado(int superaTamano) {
		boolean tamanoSuperado = false;
		
		if (superaTamano >= tamano) {
			 tamanoSuperado = true;
		} else if (superaTamano < tamano) {
			 tamanoSuperado = false;
		}
		return tamanoSuperado;
	}
	private boolean capacidadSuperada(int superaCapacidad) {
		
		boolean capacidadSuperada = false;;
		
		if (superaCapacidad >= capacidad) {
			capacidadSuperada = true;
		} else if (superaCapacidad < capacidad) {
			capacidadSuperada = false;
		}
		return capacidadSuperada;
	}
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		int i;
		
		for (i = indice; i < coleccionReservas.length - 1; i++) {
			coleccionReservas[i] = coleccionReservas[i+1];
		}
		coleccionReservas[i] = null;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public int getTamano() {
		return tamano;
	}
	public String[] representar() {
		
		String[] representar = new String[tamano];
		
		for (int i = 0; !tamanoSuperado(i) ;i++) {
			
			representar[i] = coleccionReservas[i].toString();
		}
		return representar;
	}
	public Reserva[] getReservasProfesor(Profesor profesor) {
		//Se acepta un profesor como parametro 
		Reserva[] reserva = new Reserva[capacidad];
		
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getProfesor().equals(profesor)) {
				reserva[j++] = coleccionReservas[i];
	/*El bucle recorre el array y por cada coincidencia con profesor suma una unidad en j 
	 * y le asigna el valor de esa posicion del array coleccion*/
			}
		}
		return reserva;
	}
	public Reserva[] getReservasAula(Aula aula) {
		
		Reserva[] reserva = new Reserva[capacidad];
		
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				reserva[j++] = coleccionReservas[i];
				//Mismo funcionamiento que getReservasProfesor
			}
		}
		return reserva;
	}
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
	
	Reserva[] reserva = new Reserva[capacidad];
	
	int j = 0;
	for (int i = 0; !tamanoSuperado(i); i++) {
		if (coleccionReservas[i].getPermanencia().equals(permanencia)) {
			reserva[j++] = coleccionReservas[i];
		} //Mismo funcionamiento que getReservasProfesor
	}
	return reserva;
	}
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		
		boolean disponible = true;
		
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}
		if(permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
			for (int i = 0; !tamanoSuperado(i); i++) {
				
				if(coleccionReservas[i].getAula().equals(aula) && coleccionReservas[i].getPermanencia().equals(permanencia)) {
					disponible = false;	
			} //Consulta la disponibilidad introduciendo un aula y permanencia. Si son iguales devuelve false.
		}
		return disponible;
	}
}