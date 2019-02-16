import java.io.IOException;  
  
class StartExcel {  
    public static void main(String args[])  
        throws IOException  
    {  
    	/*Ruta del archivo*/
        String fileName = "C:\\Users\\vesprada\\Desktop\\Nuevo Hoja de cálculo de OpenDocument.ods";
        
        String[] commands = {"cmd", "/c", "start", "\"DummyTitle\"",fileName};  
        Runtime.getRuntime().exec(commands);  
    }  
} 
