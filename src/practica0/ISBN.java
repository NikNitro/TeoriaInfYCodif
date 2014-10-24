/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

/**
 *
 * @author 
 */
public class ISBN extends Codificacion {

    private static final int MODULO = 11;

/*************
 * 
 * @param codigo
 * @return
 */
    public boolean verificar(String codigo) {
        codigo = codigo.replaceAll("-", "");
        int resultado = 0;
        try {
            for (int i = 0; i < 9; i++) {
                resultado += Integer.parseInt(codigo.substring(i, i+1))*(i+1);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        if(codigo.charAt(9)== 'X')
        	return (resultado % MODULO == 10) ;
        else
        	return resultado % MODULO == Integer.parseInt(codigo.substring(9));
    }

/***************
 * 
 * @param codigo
 * @return
 */
    @Override
    public String generarCodigoControl(String codigo) {

     String retorno;
     int resultado = 0;
     codigo = codigo.replaceAll("-", "");
     retorno=codigo;
     
     if (codigo.length()==9){
    	 try {
             for (int i = 0; i < codigo.length(); i++) {
                 resultado += (Integer.parseInt(codigo.substring(i, i+1))*(i+1));
             }
             int control = resultado % MODULO;
             if(control == 10) 
            	 retorno = codigo+"X";
             else
            	 retorno = codigo+(resultado% MODULO);
         } catch (NumberFormatException e) {
             return null;
         } 
     }
          	
        return retorno;
    }


    
    @Override
    public String[] corregirDatos(String codigo) {
    	String[] std = new String[10];
    	char aux1 = codigo.charAt(9);				//Averiguamos cual es el código bueno
    	int control = 0;
    	if(aux1 == 'X')
    		control = 10;
    	else
    		control = (int) (aux1 - 48);
    	System.out.println("Control: "+ control);
    	codigo = codigo.replaceAll("-", "");       
        int actual = 0;
    	
   		try {
   			for (int i = 0; i < codigo.length()-1; i++) {
               	actual += (Integer.parseInt(codigo.substring(i, i+1))*(i+1));
            }
            actual = actual % MODULO;
       	} catch (NumberFormatException e) {
       	 	return null;
       	}
    	System.out.println("Actual: " + actual);
    	int diferencia = actual-control;
    	System.out.println("diferencia: " + diferencia);
    	char [] cadena = codigo.toCharArray();
    	cadena[0] = (char)((cadena[0] + diferencia)%10 + 48); //%10 es para que sea un número entre 0 y 9
    	System.out.println("Cadena Arreglada 1: "+String.copyValueOf(cadena));
    	if(diferencia%2==0) {
    		
    		cadena = codigo.toCharArray();
    	}
    	if(diferencia%3==0) {

    		cadena = codigo.toCharArray();
    	}
    	if(diferencia%4==0) {

    		cadena = codigo.toCharArray();
    	}
    	if(diferencia%5==0) {

    		cadena = codigo.toCharArray();
    	}
    	if(diferencia%6==0) {

    		cadena = codigo.toCharArray();
    	}
    	if(diferencia%7==0) {

    		cadena = codigo.toCharArray();
    	}
    	if(diferencia%8==0) {

    		cadena = codigo.toCharArray();
    	}
    	if(diferencia%9==0) {

    		cadena = codigo.toCharArray();
    	}
    	if(diferencia%10==0) {

    		cadena = codigo.toCharArray();
    	}
    	
    	
    	return std;
    }
    /**
     * 
     */


}

