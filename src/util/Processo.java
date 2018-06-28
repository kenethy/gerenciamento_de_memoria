package util;

public class Processo {
	private int id;
	private int tamanho;

	public Processo(int id, int tamanho) {
		super();
		this.id = id;
		this.tamanho = tamanho;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
}
