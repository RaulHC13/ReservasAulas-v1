package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {

	private int capacidad;
	private int tamano;
	private Profesor[] coleccionProfesores;

	public Profesores(int capacidad) {
		
		if (capacidad <= 0 ) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		this.tamano = 0;
		coleccionProfesores = new Profesor[capacidad];
	}
	public Profesor[] get() {
		
		return copiaProfundaProfesores();
	}
	private Profesor[] copiaProfundaProfesores() {
		
		Profesor[] copiaProfunda = new Profesor[capacidad];
		
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionProfesores[i] != null) {
				copiaProfunda[i] = new Profesor(coleccionProfesores[i]);
			}
		}
		return coleccionProfesores;
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		}
		int indice = buscarIndice(profesor);
		
		if (tamanoSuperado(indice)) {
			coleccionProfesores[tamano] = new Profesor(profesor);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
		tamano++;
	}
	
	public Profesor buscar(Profesor profesor) {
		
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Profesor(coleccionProfesores[indice]);
		}
	}
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		
		int indice;
		
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		indice = buscarIndice(profesor);
		
		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		}
		desplazarUnaPosicionHaciaIzquierda(indice);
		tamano--;
	}
	private int buscarIndice(Profesor profesor) {
		
		boolean buscar = false;
		int indice = tamano + 1;
		
		for (int i = 0; i < tamano&&!buscar; i++) {
			if (coleccionProfesores[i].equals(profesor)) {
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
		
		for (i = indice; i < coleccionProfesores.length - 1; i++) {
			coleccionProfesores[i] = coleccionProfesores[i+1];
		}
		coleccionProfesores[i] = null;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public int getTamano() {
		return tamano;
	}
	public String[] representar() {
		
		String[] representar = new String[tamano];/*Se crea un array de string en el que se copian
		los valores del array coleccion en cada iteracion */
		
		for (int i = 0; !tamanoSuperado(i) ;i++) {
			
			representar[i] = coleccionProfesores[i].toString();
		}
		return representar;
		}
}