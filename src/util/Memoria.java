package util;

import java.util.ArrayList;
import java.util.Random;

public class Memoria {
	private int[] memoria = new int[512];
	private ArrayList<Processo> processos = new ArrayList<>();;

	public Memoria() {
		for (int i = 0; i < 15; i++) {
			Random r = new Random();
			this.processos.add(new Processo(i + 1, r.nextInt(50) + 100));
		}
	}

	public void run() {
		for (Processo processo : processos) {
			if (existememoria(processo.getTamanho())) {
				if (isCompactar(processo.getTamanho())) {
					compactar();
				}

			} else {
				System.out.println("Sem Memória");
			}
		}
	}

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
		return true;
	}

	private void compactar() {
		ArrayList<Integer> aux = new ArrayList<>();

		for (int i = 0; i < this.memoria.length; i++) {
			if (this.memoria[i] == 0)
				aux.add(memoria[i]);
		}

		int[] memoria = new int[512];
		int count = 0;

		for (Integer i : aux) {
			memoria[count] = i;
			count++;
		}

		this.memoria = memoria;
	}
}
