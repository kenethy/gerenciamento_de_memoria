package particoes_variaveis;

import util.Memoria;
import util.Processo;

/**
 * @author Kenedy Silva
 *
 */

public class NextFit extends Memoria {
	private static int posicao = 0;

	@Override
	public void runTipo(Processo processo) {
		int countlivre = 0;
		int posicaoAnterior = posicao;

		if (isCompactar(processo.getTamanho())) {
			System.out.println("Sem Memória, Compactando...");
			compactar();

			for (int i = 0; i < memoria.length; i++) {
				if (memoria[i] == 0) {
					posicao = i;
					break;
				}
			}
		}

		for (int i = posicao; i < memoria.length; i++) {
			if (memoria[i] == 0)
				countlivre++;
			else {
				countlivre = 0;
				posicao = i + 1;
			}

			if (countlivre == processo.getTamanho())
				break;

			if (i + 1 == memoria.length)
				posicao = 0;
		}

		System.err.println("Posicão Anterior: " + posicaoAnterior + "/ Posicão: " + posicao);

		for (int i = posicao; i < (processo.getTamanho() + posicao); i++) {
			memoria[i] = processo.getId();
		}
	}

	@Override
	protected void printMemoria() {
		System.out.print("Memória: ");
		for (int i = 0; i < this.memoria.length; i++) {
			System.out.print(i + "/" + this.memoria[i] + " | ");
		}
		System.out.println();
	}

}
