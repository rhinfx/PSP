public class Quiniela {
	


	public static void main(String[] args) {
		
		int numApuesta=1;
		char[] resultados = {'1','X','2'};
		String salida;
		int cont1=0, contX=0, cont2=0;
		
		for (char r1 : resultados) {
			for (char r2 : resultados) {
				for (char r3 : resultados) {
					for (char r4 : resultados) {
						for (char r5 : resultados) {
							for (char r6 : resultados) {
								for (char r7 : resultados) {
									for (char r8 : resultados) {
										for (char r9 : resultados) {
											for (char r10 : resultados) {
												for (char r11 : resultados) {
													for (char r12 : resultados) {
														for (char r13 : resultados) {
															for (char r14 : resultados) {
																salida=""+r1+r2+r3+r4+r5+r6+r7+r8+r9+r10+r11+r12+r13+r14;
																
																cont1=salida.replace("X", "").replace("2", "").length();
																contX=salida.replace("1", "").replace("2", "").length();
																cont2=salida.replace("1", "").replace("X", "").length();
											
																if (cont1>5 && cont1 <11 && contX>2 && contX < 6 && cont2>3 && cont2<9) {
																	System.out.println(salida+" "+numApuesta);
																	numApuesta++;
																}
																
																else {
																	
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}