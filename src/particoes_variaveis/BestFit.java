package particoes_variaveis;

import util.Memoria;
import util.Processo;


/**
 * @author César Muniz
 *
 */


public class BestFit extends Memoria {
	
	@Override
	public void runTipo(Processo processo) {
		boolean flag = true;
		int indiceMenor = memoria.length;
		int qtdMenor = memoria.length;
		int auxIndice = 0;
		int auxQtd = 0;
		
		if (isCompactar(processo.getTamanho())) {
			System.out.println("Sem Memória, Compactando...");
			compactar();
		}
		
		for (int i = 0; i < memoria.length; i++) {
			
			if(memoria[i] == 0) {				
				if(flag) {					
					auxIndice = i;
					flag = false;
				}
				auxQtd++;			
			}
			if(memoria[i] != 0 || i == 511) {				
				flag = true;
				if(auxQtd >= processo.getTamanho()) {
					if(auxQtd <= qtdMenor) {						
						indiceMenor = auxIndice;
						qtdMenor = auxQtd;
					}
				}
				auxQtd = 0;
			}			
		}
//		System.out.println(indiceMenor);
		
		for(int i=indiceMenor; i<(processo.getTamanho()+indiceMenor); i++) {
			memoria[i] = processo.getId();
		}
		
	}
	
}
