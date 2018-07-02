package particoes_variaveis;

import util.Memoria;
import util.Processo;

public class FirstFit extends Memoria {

	@Override
	public void runTipo(Processo processo) {
		int countlivre=0;
		int posicao = 0;
		
		if (isCompactar(processo.getTamanho())) {
			System.out.println("Sem Memória, Compactando...");
			compactar();
		}
		
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] == 0)
				countlivre++;
			else {
				countlivre = 0;
				posicao = i+1;
			}
			
			if(countlivre==processo.getTamanho())
				break;
		}

		for(int i=posicao; i<(processo.getTamanho()+posicao); i++) {
			memoria[i] = processo.getId();
		}
	}
	
}
