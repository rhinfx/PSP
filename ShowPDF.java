
public class ShowPDF {  
	
  public static void main(String[] args) throws Exception {  
    Process p =   
      Runtime.getRuntime()  
        .exec("rundll32 url.dll,FileProtocolHandler c:/pdf/ADT3.pdf"); //c:... ruta del archivo
    p.waitFor();  
    System.out.println("Done.");  
  }  
  
} 
