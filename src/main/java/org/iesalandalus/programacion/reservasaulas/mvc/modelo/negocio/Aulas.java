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
		setAulas(aulas);
	}
	
	private void setAulas(Aulas aulas) {
		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}
		coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);
	}
	
	public List<Aula> getAulas() {
		
		return copiaProfundaAulas(coleccionAulas);
	}
	
	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
		
		List<Aula> copiaAulas = new ArrayList<>();
		Iterator<Aula> I = aulas.iterator();
		
		for(aulas.iterator(); I.hasNext();) {
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
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		if (coleccionAulas.contains(aula)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		} else {
			coleccionAulas.add(new Aula(aula));
		}
	}
	
	public Aula buscar (Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		if (coleccionAulas.contains(aula)) {
			System.out.println("Se ha encontrado el " + aula + " en el índice " + coleccionAulas.indexOf(aula) + "  ");
			return new Aula(aula);
		}else {
			System.out.println("No se ha encontrado el aula.");
			return null;
		}
	}
	
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		if (coleccionAulas.contains(aula)) {
			coleccionAulas.remove(aula);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}
	}

	public List<String> representar() {
		
		List<String> representar = new ArrayList<>();
		Iterator<Aula> I = getAulas().iterator();
		
		for (getAulas().iterator(); I.hasNext();) {
			
			representar.add(I.next().toString());
		}
		return representar;	
	}
}