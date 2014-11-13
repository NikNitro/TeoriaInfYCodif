package PracticaObligatoria;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;



public class lwz {
	private String texto;
	private List<Integer> codificacion;
	private List<String> diccionario;
	private String descodificacion;
	
	public lwz (String nombreArchivo) {
		codificacion = new ArrayList<Integer>();
		diccionario  = new ArrayList<String>(); 
		descodificacion = "";
		try {
			BinaryIn In = new BinaryIn(nombreArchivo);
			texto = In.readString();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		
		//hay que meter comas, puntos y demás... Y MAYUSCULAS
		/*
		for(char c = 'a'; c <= 'z'; c++ ) {
			diccionario.add(String.valueOf(c));
		}
		diccionario.add("0");
		*/
		diccionario.add("a");
		diccionario.add("b");
		diccionario.add("c");
		diccionario.add("d");
		diccionario.add("r");
		
		
	}
	
	
	public void Comprimir() {
		char[] array = "abracadabraabracadabra".toCharArray();//texto.toCharArray();
		String auxiliar;
		int contadorParaElTexto = 0, maximoTamano = 2;
		
		while(contadorParaElTexto<array.length) {
			int tamano;
			tamano= maximoTamano;
			if(contadorParaElTexto+maximoTamano>= array.length) tamano = array.length - contadorParaElTexto;
			auxiliar = take(tamano, contadorParaElTexto, array);
			boolean seguir = true;

			//Ya tenemos la siguiente subCadena. Ahora buscaremos si está en el diccionario.
			while(seguir) {
				System.out.println(auxiliar);/////////////
				if(!diccionario.contains(auxiliar)) {
					tamano--;
					System.out.println("\t lo añade");
					diccionario.add(auxiliar);
					auxiliar = auxiliar.substring(0, auxiliar.length()-1);
					
				} else {
					seguir = false;
					if(contadorParaElTexto + tamano < array.length){
						String aux = take(tamano+1, contadorParaElTexto, array );
						if(!diccionario.contains(aux)) diccionario.add(aux);
					}
					codificacion.add(diccionario.indexOf(auxiliar)+1);//El +1 es para que nos guarde un numero empezando por 1
					
					contadorParaElTexto += tamano;
					if(auxiliar.length()>=maximoTamano)
						maximoTamano++;
					
				}
			}
			System.out.println("    Sale del bucle.");/////////////
			
			
			
		}
		
		for(int i = 0; i < codificacion.size(); i++) {
			System.out.print(codificacion.get(i)+ ", ");
		}
		System.out.println();
		for(int i = 0; i < diccionario.size(); i++){
			System.out.print(diccionario.get(i)+ ", ");
		}
		System.out.println();//Para que acabe la línea
		
		//Hay que hacer un if contadorPar...<array.length-1 por si nos dejamos alguno

	}
	
	/**
	 * Coge i caracteres de un array desde principio
	 * @param i
	 * @return
	 */
	private String take(int i, int principio, char[] array) {
		char[] res = new char[i];
		System.arraycopy(array, principio, res, 0, i);
		return String.copyValueOf(res);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void Descomprimir() {
		//Tenemos nuestra cadena de números que nos va a fallar si algún número es de más de una cifra
		char[] array = "1251314681391115".toCharArray();//"125131468".toCharArray();//1, 2, 5, 1, 3, 1, 4, 6, 8, "121813142830".toCharArray(); 		//1, 2, 18, 1, 3, 1, 4, 28, 30
		int contador = 0;
		int aux = 0, numActual;
		boolean puedeSeguir = true;
		while(contador<array.length) {
			System.out.println("---Entramos al primer bucle");
			numActual = Integer.parseInt(array[contador]+"");
			descodificacion +=diccionario.get(numActual-1);
			contador++;
			
			//Así buscamos el número de más cifras
			aux=numActual;
			while(contador<array.length && puedeSeguir) {
				System.out.println("               "+aux);
				aux = aux*10 + Integer.parseInt(array[contador]+"");
				if(aux<diccionario.size()) {		//Esto puede dar error porque los números empiezan en 1 y dicc en 0
					contador++;
				} else {
					aux = numActual;
					puedeSeguir=false;
				}
			}
			
			
			

			numActual = aux;
			System.out.println(numActual);
			
		}
		
		
		System.out.println(descodificacion);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] std) {
		lwz l = new lwz("prueba2.txt");
		l.Comprimir();
		l.Descomprimir();
	}
	

}
