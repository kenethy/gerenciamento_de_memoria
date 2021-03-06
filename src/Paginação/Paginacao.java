package Pagina��o;

import java.util.ArrayList;
import java.util.Random;

import util.Processo;

public abstract class Paginacao {
	protected int[] memoria;
	protected boolean[] segundaChance;
	protected ArrayList<Processo> processos;
	protected ArrayList<Integer> entradas;
	
	private final static int COUNT_MEMORIA = 8;

	public Paginacao() {
		this.memoria = new int[COUNT_MEMORIA];
		this.segundaChance = new boolean[COUNT_MEMORIA];
		this.processos = new ArrayList<>();
		this.entradas = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			this.processos.add(new Processo(i, i));
		}

		for (int i = 0; i < 200; i++) {
			Random r = new Random();
			entradas.add(r.nextInt(16));
		}

		for (int i = 0; i < this.memoria.length; i++) {
			this.memoria[i] = -1;
			this.segundaChance[i] = false;
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
			System.out.println("Executando P�gina "+pagina+" do Processo "+processo.getId());
			
			this.addMemoria(pagina, processo);
			
			printMemoria();
			
			sleep();
		}
	}

	public abstract void addMemoria(Integer i, Processo p);
	
	protected boolean hit(int pagina) {
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
	
	protected void reorganizarSegundaChance() {
		for(int i=0; i<this.segundaChance.length; i++) {
			if(i==0) {
				this.segundaChance[i] = this.segundaChance[i+1];
			} else if(i+1<this.segundaChance.length) {
				this.segundaChance[i] = this.segundaChance[i+1];
			} else if(i+1<this.segundaChance.length) {
				this.segundaChance[i+1] = false;
			}
		}
	}
	
	protected void printMemoria() {
		System.out.print("Mem�ria: ");
		for(int i=0; i<this.memoria.length; i++) {
			System.out.print(this.memoria[i]+" | ");
		}
		System.out.println();
		System.out.println();
	}
	
	private void printPaginas() {
		System.out.print("P�ginas: ");
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
