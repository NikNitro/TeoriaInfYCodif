package PracticaObligatoria;

public class NodoListaDiccionario {
	private NodoListaDiccionario anterior;
	private NodoListaDiccionario siguiente;
	private String palabra;
	private int index;
	
	
	public NodoListaDiccionario(String std, int i) {
		anterior = null;
		palabra = std;
		siguiente = null;
		index = i;
	}
	
	public NodoListaDiccionario(NodoListaDiccionario ant, String std, int i) {
		anterior = ant;
		palabra = std;
		siguiente = null;
		index = i;
	}

	public int getIndex() {
		return index;
	}
	public NodoListaDiccionario getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoListaDiccionario anterior) {
		this.anterior = anterior;
	}

	public NodoListaDiccionario getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoListaDiccionario siguiente) {
		this.siguiente = siguiente;
	}

	public String getPalabra() {
		return palabra;
	}
	
	
}
