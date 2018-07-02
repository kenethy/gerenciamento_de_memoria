package util;

import java.util.ArrayList;
import java.util.Random;

public abstract class Memoria {
	protected int[] memoria = new int[512]; // Memória de 512 MB
	private ArrayList<Processo> processos = new ArrayList<>();

	public abstract void runTipo(Processo processo);
	
	// Criação dos processos
	public Memoria() {
		for(int i = 0; i < this.memoria.length; i++) this.memoria[i] = 0;

		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			this.processos.add(new Processo(i + 1, r.nextInt(20) + 50));
		}
	}

	// Alocação dos processos na memória
	public void run() {
		while(true) {
			for (Processo processo : processos) {
				System.out.println(processo);
				if (existememoria(processo.getTamanho())) {
					// Executar algoritmo de alocação nessa parte
					runTipo(processo);
					this.printMemoria();
				} else {
					System.out.println("Sem Memória, não é possível compactar.");
					this.removeRandom();
				}
				this.sleep();
			}
		}
	}

	// Verificação de espaço de memória livre
	protected boolean existememoria(int espaco) {
		int countlivre = 0;
		for (int i = 0; i < memoria.length; i++) {
			if (memoria[i] == 0)
				countlivre++;
		}

		if (espaco <= countlivre)
			return true;
		else
			return false;
	}

	// Verificação da memória para saber se existe espaço para inserir processo
	protected boolean isCompactar(int tamanho) {
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
	protected void compactar() {
		ArrayList<Integer> aux = new ArrayList<>();

		// Copia toda a memória para um auxiliar
		for (int i = 0; i < this.memoria.length; i++) {
			if (this.memoria[i] != 0)
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
	
	protected void printMemoria() {
		System.out.print("Memória: ");
		for(int i = 0; i < this.memoria.length; i++) {
			System.out.print(this.memoria[i]+" | ");
		}
		System.out.println();
	}
	
	private void removeRandom() {
		Random r = new Random();
		int remove = r.nextInt(this.processos.size()) + 1;
		for(int i = 0; i < this.memoria.length; i++) {
			if(this.memoria[i]==remove) this.memoria[i] = 0;
		}
	}
	
	public void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
