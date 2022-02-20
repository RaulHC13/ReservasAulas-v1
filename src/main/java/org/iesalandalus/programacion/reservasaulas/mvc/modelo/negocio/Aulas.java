package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	
	private List<Aula> coleccionAulas;
	
	
	public Aulas() {
		
		coleccionAulas = new ArrayList<>();
	}
	public Aulas(Aulas aulas) {
		
	}
	
	private void setAulas(Aulas aulas) {
		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}
		coleccionAulas = copiaProfundaAulas(coleccionAulas);
	}
	public List<Aula> getAulas() {
		
		return copiaProfundaAulas(coleccionAulas);
	}
	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
		
		List<Aula> copiaAulas = new ArrayList<>();
		
		for(Iterator<Aula> I = aulas.iterator(); I.hasNext();) {
			Aula aula = I.next();
			copiaAulas.add(new Aula(aula));
		}
		
		return copiaAulas;
	}
	
	public int getNumAulas() {
		
		return coleccionAulas.size();
	}
	
	public void insertar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("asd");
		}
		if (coleccionAulas.contains(aula)) {
			throw new OperationNotSupportedException("asdw");
		} else {
			coleccionAulas.add(new Aula(aula));
		}
	}
	
	public Aula buscar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("asd");
		}
		if (coleccionAulas.contains(aula)) {
			System.out.println("Se ha encontrado el aula: ");
			return new Aula(aula);
		}else {
			System.out.println("No se ha encontrado el aula.");
			return null;
		}
		
	}

	public String[] representar() {
		
		String[] representar = new String[tamano];/*Se crea un array de string en el que se copian
		los valores del array coleccion en cada iteracion */
		
		for (int i = 0; !tamanoSuperado(i) ;i++) {
			
			representar[i] = coleccionAulas[i].toString();
		}
		return representar;	
	}
}