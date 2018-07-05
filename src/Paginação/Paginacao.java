package Paginação;

import java.util.ArrayList;
import java.util.Random;

import util.Processo;

public abstract class Paginacao {
	protected int[] memoria;
	protected ArrayList<Processo> processos;
	protected ArrayList<Integer> entradas;

	public Paginacao() {
		this.memoria = new int[4];
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
			this.memoria[i] = -1;
		}
	}

	public void run() {
		printPaginas();
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
			System.out.println("Executando Página "+pagina+" do Processo "+processo.getId());
			
			this.addMemoria(pagina, processo);
			
			printMemoria();
			
			sleep();
		}
	}

	public abstract void addMemoria(Integer i, Processo p);
	
	protected boolean miss(int pagina) {
		for(int i = 0; i < this.memoria.length;i++) {
			if(this.memoria[i]==pagina) return true;
		}
		return false;
	}
	
	protected boolean livre() {
		boolean livre = false;
		for(int i = 0; i < this.memoria.length;i++) {
			if(this.memoria[i]==-1) return true;
		}
		return livre;
	}
	
	protected void reorganizar() {
		for(int i=0; i<this.memoria.length; i++) {
			if(this.memoria[i]==-1 && i+1<this.memoria.length) {
				this.memoria[i] = this.memoria[i+1];
				this.memoria[i+1] = -1;
			}
		}
	}
	
	private void printMemoria() {
		System.out.print("Memória: ");
		for(int i=0; i<this.memoria.length; i++) {
			System.out.print(this.memoria[i]+" | ");
		}
		System.out.println();
		System.out.println();
	}
	
	private void printPaginas() {
		System.out.print("Páginas: ");
		for(int i=0; i<this.entradas.size(); i++) {
			System.out.print(this.entradas.get(i)+" | ");
		}
		System.out.println();
		System.out.println();
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
