/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package practica0;

/**
*
* @author 
*/
public class NIF extends Codificacion {


    private static final String NIF_TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";


  

    @Override
    public boolean verificar(String nif) {
       int dni = Integer.parseInt(nif.substring(0, nif.length()-1));
       
       try {
           return NIF_TABLA.charAt(dni % NIF_TABLA.length()) == nif.charAt(nif.length()-1);
       } catch (NumberFormatException e) {
           return false;
       }
    }


    /*************
     * 
     * @param codigo
     * @return
     */
    public String generarCodigoControl(String codigo) {
       
       if (codigo!=null){
        int dni = Integer.parseInt(codigo, 10);
        return codigo+ NIF_TABLA.charAt(dni % NIF_TABLA.length());
       }
       else return null;
    }

    
    public String [] corregirDatos(String codigo) {
    //    throw new UnsupportedOperationException("Not supported yet.");
    	
    	String[] std= new String[8];
    	int contador = 0;
    	//Calculamos cuanto debe dar la suma:
    	int sumaFinal = NIF_TABLA.indexOf(codigo.charAt(8)) % 23;
 //   	System.out.println(sumaFinal);
    	int sumaInicial = Integer.parseInt(codigo.substring(0, codigo.length()-1));
    	sumaInicial = sumaInicial%23;
 //   	System.out.println(sumaInicial);
    	
    	char[] aux = codigo.toCharArray();
    	for(int i = 0; i < 8; i++) {					//Vamos a probar con cada una de las 8 cifras
   // 		int cifraInic = aux[i];
    		for(int j = 0; j < 10; j++) {				//Vamos a probar a cambiarla por cada posibilidad
    			aux[i]=(char)(j+48);
    			String posible = String.copyValueOf(aux);
    			if(verificar(posible)){ 		//Si modulo 23 es igual entonces se acepta
    				std[contador] = posible;
    				contador++;
    			}
    			aux = codigo.toCharArray();
    		}
    		sumaInicial+=Integer.parseInt(codigo.substring(i, i+1));
    	}
    	
    	
    	
    	
    	return std;
    }
}
