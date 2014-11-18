package PracticaObligatoria;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LZW {
	private String texto;
	//private List<Integer> codificacion;
	private static ListaDiccionario diccionario;
	private String descodificacion;
	private BinaryOut out ;
	private BinaryIn in;

	
	public LZW () {
		//codificacion = new ArrayList<Integer>();
		descodificacion = "";
		inicializarDiccionario();
	}
	
	public static void inicializarDiccionario(){
		diccionario  = new ListaDiccionario(Character.toString((char)0)); 
		
		/*
		diccionario.add("a");
		diccionario.add("b");
		diccionario.add("c");
		diccionario.add("d");
		diccionario.add("r");
			*/	
		//hay que meter comas, puntos y demás... Y MAYUSCULAS
		/*
		for(char c = 'a'; c <= 'z'; c++ ) {
			diccionario.add(String.valueOf(c));
		}
		*/
		
		for(int i =1; i < 256; i++){
			diccionario.insertarNodo(Character.toString((char)i));
		}


	}
	
	
	public void Comprimir(String fileIn, String fileOut) throws FileNotFoundException {
    	in = new BinaryIn(fileIn);
        out = new BinaryOut(fileOut);
		texto = in.readString();

		
		char [] array = texto.toCharArray();

		String auxiliar;
		int contadorParaElTexto = 0, maximoTamano = 2;
		
		while(contadorParaElTexto<array.length) {
			int tamano;
			tamano= maximoTamano;
			if(contadorParaElTexto+maximoTamano>= array.length) tamano = array.length - contadorParaElTexto;
			auxiliar = take(tamano, contadorParaElTexto, array);	
			boolean seguir = true;

			while(seguir) {
				int indexDelAuxiliar = diccionario.contains(auxiliar);
				if(indexDelAuxiliar==-1) {
					
					tamano--;

					auxiliar = auxiliar.substring(0, auxiliar.length()-1);
					
				} else {
					seguir = false;
					if(contadorParaElTexto + tamano < array.length){
						String aux = take(tamano+1, contadorParaElTexto, array );
			
						if(diccionario.contains(aux)==-1) {
							diccionario.insertarNodo(aux);
							System.out.println(aux);
						}
					}
					//codificacion.add(diccionario.indexOf(auxiliar)+1);//El +1 es para que nos guarde un numero empezando por 1
					int size = diccionario.getSize();
			
					out.write(indexDelAuxiliar+1, potenciaMayor(size));
					contadorParaElTexto += tamano;
					if(auxiliar.length()>=maximoTamano)
						maximoTamano++;
					
				}
			}

			
			
			
		}
		
		out.write(0, potenciaMayor(diccionario.getSize()));
		out.close();
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
	
	private int potenciaMayor(int numero){
		int resultado= 0;
		while((2<<resultado) <= numero){
			resultado++;
			
		}
		resultado++;
		return resultado;
	}
	
	public String mostrarDiccionario() {
		return diccionario.toString();
	}
	
	
	public void Descomprimir(String fileIn, String fileOut) throws FileNotFoundException {
		////System.out.println("Descomprimir");
		inicializarDiccionario();
		in = new BinaryIn(fileIn);
		out = new BinaryOut(fileOut);
	
		

		int cuantoslee = potenciaMayor(diccionario.getSize()+2);


		int actualn = in.readInt(cuantoslee);
		String w = diccionario.get(actualn -1);
		descodificacion = descodificacion + w ;
	
	
		String aux;
		
		while(!in.isEmpty()){

			 
			actualn = in.readInt(cuantoslee);
			if(actualn == 0) break;
			if(diccionario.getSize() < actualn){//no está
				
				diccionario.insertarNodo(w+ w.charAt(0));
				w = diccionario.get(actualn -1);

				
			}else{ // está
				
				aux = w;
				w = diccionario.get(actualn -1);
				diccionario.insertarNodo(aux+w.charAt(0));
//				System.out.println(aux+w.charAt(0));
			}
			descodificacion = descodificacion + w ;


			cuantoslee = potenciaMayor(diccionario.getSize()+2);

		}
		for(int i =0; i < descodificacion.length(); i++){
	
			out.write(descodificacion.charAt(i));
		}
		out.close();

	}
	
	public static void main(String[] std) throws FileNotFoundException {
		LZW l = new LZW();
		l.Comprimir("Alice.txt", "salida.txt" );
		//System.out.println(l.mostrarDiccionario());
		l.Descomprimir("salida.txt", "salidaDes.txt");
	}
	

}
