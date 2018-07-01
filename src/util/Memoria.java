package util;

import java.util.ArrayList;
import java.util.Random;

public class Memoria {
	private int[] memoria = new int[512]; // Memória de 512 MB
	private ArrayList<Processo> processos = new ArrayList<>();;

	// Criação dos processos
	public Memoria() {
		for (int i = 0; i < 15; i++) {
			Random r = new Random();
			this.processos.add(new Processo(i + 1, r.nextInt(50) + 100));
		}
	}

	// Alocação dos processos na memória
	public void run() {
		for (Processo processo : processos) {
			if (existememoria(processo.getTamanho())) {
				// Executar algoritmo de alocação nessa parte
				
				if (isCompactar(processo.getTamanho())) {
					compactar();
				}
			} else {
				System.out.println("Sem Memória");
			}
		}
	}

	// Verificação de espaço de memória livre
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

	// Verificação da memória para saber se existe espaço para inserir processo
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
		// Caso chegue no final da memória e o processo não for alocado, realizar
		// compactação
		return true;
	}

	// Compactação
	private void compactar() {
		ArrayList<Integer> aux = new ArrayList<>();

		// Copia toda a memória para um auxiliar
		for (int i = 0; i < this.memoria.length; i++) {
			if (this.memoria[i] == 0)
				aux.add(memoria[i]);
		}

		// Cria uma memória nova
		int[] memoria = new int[512];
		int count = 0;

		// Insere os dados
		for (Integer i : aux) {
			memoria[count] = i;
			count++;
		}

		// Substitui a memória
		this.memoria = memoria;
	}
}
