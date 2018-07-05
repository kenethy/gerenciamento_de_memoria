package Paginação;

import java.util.ArrayList;
import java.util.Random;

import util.Processo;

public abstract class Paginacao {
	protected ArrayList<Integer> memoria;
	protected ArrayList<Processo> processos;
	protected ArrayList<Integer> entradas;

	public Paginacao() {
		this.memoria = new ArrayList<>();
		this.processos = new ArrayList<>();
		this.entradas = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			this.processos.add(new Processo(i, i));
		}

		for (int i = 0; i < 200; i++) {
			Random r = new Random();
			entradas.add(r.nextInt(16));
		}

		for (int i = 0; i < 4; i++) {
			this.memoria.add(-1);
		}
	}

	public void run() {
		for (int i = 0; i < this.entradas.size(); i++) {
			Processo processo = null;
			int pagina = this.entradas.get(i);

			for (Processo p : processos) {
				for (int j = 0; j < 4; j++) {
					if (p.getPaginas()[j] == pagina) {
						processo = p;
						break;
					}
				}
			}

			this.addMemoria(pagina, processo);
		}
	}

	public abstract void addMemoria(Integer i, Processo p);
}
