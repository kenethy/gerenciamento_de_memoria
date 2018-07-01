package util;

import java.util.ArrayList;
import java.util.Random;

public class Memoria {
	private int[] memoria = new int[512]; // Mem�ria de 512 MB
	private ArrayList<Processo> processos = new ArrayList<>();;

	// Cria��o dos processos
	public Memoria() {
		for (int i = 0; i < 15; i++) {
			Random r = new Random();
			this.processos.add(new Processo(i + 1, r.nextInt(50) + 100));
		}
	}

	// Aloca��o dos processos na mem�ria
	public void run() {
		for (Processo processo : processos) {
			if (existememoria(processo.getTamanho())) {
				// Executar algoritmo de aloca��o nessa parte
				
				if (isCompactar(processo.getTamanho())) {
					compactar();
				}
			} else {
				System.out.println("Sem Mem�ria");
			}
		}
	}

	// Verifica��o de espa�o de mem�ria livre
	private boolean existememoria(int espaco) {
		int countlivre = 0;
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] == 0)
				countlivre++;
		}

		if (espaco >= countlivre)
			return true;
		else
			return false;
	}

	// Verifica��o da mem�ria para saber se existe espa�o para inserir processo
	private boolean isCompactar(int tamanho) {
		int sequencia = 0;
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] == 0)
				sequencia++;
			else
				sequencia = 0;

			if (sequencia >= tamanho)
				return false;
		}
		// Caso chegue no final da mem�ria e o processo n�o for alocado, realizar
		// compacta��o
		return true;
	}

	// Compacta��o
	private void compactar() {
		ArrayList<Integer> aux = new ArrayList<>();

		// Copia toda a mem�ria para um auxiliar
		for (int i = 0; i < this.memoria.length; i++) {
			if (this.memoria[i] == 0)
				aux.add(memoria[i]);
		}

		// Cria uma mem�ria nova
		int[] memoria = new int[512];
		int count = 0;

		// Insere os dados
		for (Integer i : aux) {
			memoria[count] = i;
			count++;
		}

		// Substitui a mem�ria
		this.memoria = memoria;
	}
}
