package util;

import java.util.ArrayList;
import java.util.Random;

public abstract class Memoria {
	protected int[] memoria = new int[512]; // Mem�ria de 512 MB
	private ArrayList<Processo> processos = new ArrayList<>();

	public abstract void runTipo(Processo processo);
	
	// Cria��o dos processos
	public Memoria() {
		for(int i = 0; i < this.memoria.length; i++) this.memoria[i] = 0;

		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			this.processos.add(new Processo(i + 1, r.nextInt(20) + 50));
		}
	}

	// Aloca��o dos processos na mem�ria
	public void run() {
		while(true) {
			for (Processo processo : processos) {
				System.out.println(processo);
				if (existememoria(processo.getTamanho())) {
					// Executar algoritmo de aloca��o nessa parte
					runTipo(processo);
					this.printMemoria();
				} else {
					System.out.println("Sem Mem�ria, n�o � poss�vel compactar.");
					this.removeRandom();
				}
				this.sleep();
			}
		}
	}

	// Verifica��o de espa�o de mem�ria livre
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

	// Verifica��o da mem�ria para saber se existe espa�o para inserir processo
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
		// Caso chegue no final da mem�ria e o processo n�o for alocado, realizar
		// compacta��o
		return true;
	}

	// Compacta��o
	protected void compactar() {
		ArrayList<Integer> aux = new ArrayList<>();

		// Copia toda a mem�ria para um auxiliar
		for (int i = 0; i < this.memoria.length; i++) {
			if (this.memoria[i] != 0)
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
	
	protected void printMemoria() {
		System.out.print("Mem�ria: ");
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
