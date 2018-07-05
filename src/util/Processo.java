package util;

public class Processo {
	private int id;
	private int tamanho;
	private int[] paginas;

	public Processo(int id, int tamanho) {
		super();
		this.id = id;
		this.tamanho = tamanho;
		this.paginas = new int[4];
		
		this.paginas[0] = 0 + (id * 4);
		this.paginas[1] = 1 + (id * 4);
		this.paginas[2] = 2 + (id * 4);
		this.paginas[3] = 3 + (id * 4);
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

	public int[] getPaginas() {
		return paginas;
	}

	public void setPaginas(int[] paginas) {
		this.paginas = paginas;
	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", tamanho=" + tamanho + "]";
	}
	
}
