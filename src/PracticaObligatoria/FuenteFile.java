package PracticaObligatoria;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class FuenteFile {

String path="";
String compressSufix="C.txt";
String decompressSufix="D.txt";
LZW lzw;
String file;
long nanoCompIni,nanoCompFin;
long nanoDeCompIni,nanoDeCompFin;
	

  public FuenteFile(String filep) throws FileNotFoundException{
 	 file=filep;
 	 lzw = new LZW ();
 	 
 	 nanoCompIni=System.nanoTime();
	 lzw.Comprimir(path+file,path+file+compressSufix);
	 nanoCompFin=System.nanoTime();
		 
	 nanoDeCompIni=System.nanoTime();
	 lzw.Descomprimir(path+file+compressSufix,path+file+decompressSufix);
	 nanoDeCompFin=System.nanoTime();
		 
	
}
/*********	
 *  Metodo para Diff
 * @param filename
 * @return
 */
  private static List<String> fileToLines(String filename) {
      List<String> lines = new LinkedList<String>();
      String line = "";
      BufferedReader in;
      try {
           in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine()) != null) {
                  lines.add(line);
           }
      } catch (IOException e) {
                  e.printStackTrace();
      }
     return lines;
 }
/************
 * 
 */
public void stadistic(boolean printCodeTable){
  long regularL,compressL,decompressL;
  float ratio;
		
  System.out.println("********************** \t "+file+" \t **********************");
			
  File regular = new File(path+file);
  regularL=regular.length();
		 
  File compress = new File(path+file+compressSufix);
  compressL=compress.length();
		 
  ratio= (float) compressL / (float) regularL;
  System.out.println("Size regular: "+regularL+" Size compress: "+compressL+ " Ratio: "+ ratio);
		 
		 
  File decompress = new File(path+file+decompressSufix);
  decompressL=decompress.length();
		 
  List<String> regularDiff = fileToLines(path+file);
  List<String> decompressDiff  = fileToLines(path+file+decompressSufix);
         
  // Compute diff. Get the Patch object. Patch is the container for computed deltas.
   Patch patch = DiffUtils.diff(regularDiff, decompressDiff);
   List<Delta> diff= patch.getDeltas();
        
   if (diff.isEmpty()) {
   	 System.out.print("Fichero Descomprimido correctamente!!!");
     System.out.println("\t Size regular: "+regularL+" Size decompress: "+decompressL);
     System.out.println("Tiempo Comprimir: "+(nanoCompFin-nanoCompIni)+" nanosegundos.   Tiempo Descomprimir: "+(nanoDeCompFin-nanoDeCompIni)+" nanosegundos.");
  }
  else				 System.out.println("Error al Descomprir!!!");
         
         
 System.out.println("****************************************************************");

}
	

                        
public static void main(String[] args) {
	    	
   	try {
   		
		 FuenteFile f1= new FuenteFile("Alice.txt");
        f1.stadistic(true);
				 
   		/*
		FuenteFile f2= new FuenteFile("MobyDick.txt");
		f2.stadistic(true);
				
		FuenteFile f3= new FuenteFile("Quixote.txt");
		f3.stadistic(true);
		*/
				
    } catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	    	    
    }

}
