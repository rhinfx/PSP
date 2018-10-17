'''java'''
	public static boolean ValidarNIF(String Nif) {
		boolean resultado = false;
		String[] letrasDNI = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		
		if(Nif.length()!=9) {
			System.out.println("Longitud de nif ha de ser de 9");
		}
		
		else if(!Nif.substring(0,8).matches("\\d+")){
			System.out.println("Los primeros 8 carácteres han de ser digitos del 0-9");
		}
		
		else if(!Nif.substring(8).matches("[A-Za-z]")) {
			System.out.println("El último carácter ha de ser una letra de la [A-z]");
		}
		
		else {
			if(letrasDNI[Integer.parseInt(Nif.substring(0, 8))%23].equalsIgnoreCase(Nif.substring(8))) {
				resultado = true;
			}
		}
		
		return resultado;
	}
