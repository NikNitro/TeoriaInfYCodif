package PracticaObligatoria;

import java.lang.Character.Subset;

public class ListaDiccionario {
	private NodoListaDiccionario primero;
	private NodoListaDiccionario ultimo;
	private int size;
	
	public ListaDiccionario(String std) {
		primero = new NodoListaDiccionario(std, 0);
		ultimo = primero;
		size = 1;
	}
	
	public void insertarNodo(String std) {
		NodoListaDiccionario aux = new NodoListaDiccionario(ultimo, std, size++);
		ultimo.setSiguiente(aux);
		ultimo = aux;
	}
	
	public String toString() {
		String std = "";
		NodoListaDiccionario aux = primero;
		while(aux!=null) {
			std = std + aux.getPalabra() + "\n";
			aux = aux.getSiguiente();
		}
		
		return std;
	}
	
	public String get(int i) {
		NodoListaDiccionario aux;
		if(i>size || i < 0)
			return null;
		if(i>size/2) {
			aux = ultimo;
			for(int j = size-1; j>i; j--) 
				aux = aux.getAnterior();
			return aux.getPalabra();
		} else {
			aux = primero;
			for(int j = 0; j<i; j++) 
				aux = aux.getSiguiente();
			return aux.getPalabra();
		}
	}
	
	public int contains(String std) {
		String anterior;
		NodoListaDiccionario aux = ultimo;
		
		//Mira si esta std o su anterior
		if(std.length()>4) {
			while(aux!=null) {
				anterior= std.substring(std.length()-2);
				if(aux.getPalabra().equals(std))
					return aux.getIndex();
				else if(aux.getPalabra().equals(anterior))
					return aux.getIndex();
				else
					aux = aux.getAnterior();
			}
		//Solo busca std
		} else {
			while(aux!=null) {
				if(aux.getPalabra().equals(std))
					return aux.getIndex();
				else
					aux = aux.getAnterior();
			}
		}
		
		
		return -1;
	}
	public int getSize() {
		return size;
	}
	
	public static void main(String[] usseless) {
		ListaDiccionario l = new ListaDiccionario("Primera");
		l.insertarNodo("que pasa");
		l.insertarNodo("por");
		l.insertarNodo("\n");
		l.insertarNodo("Aquis");
		
		System.out.println(l);
	}
	
}
