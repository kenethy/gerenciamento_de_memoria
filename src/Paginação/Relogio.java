package Pagina��o;

import util.Processo;

public class Relogio extends Paginacao {
	
	private int ponteiro = 0;
	
	@Override
	public void addMemoria(Integer pagina, Processo processo){
		if(hit(pagina)) {
			System.out.println("Hit na p�gina "+pagina);
			
			for(int i=0; i<this.memoria.length; i++) {
				if(pagina==this.memoria[i]) {
					this.segundaChance[i] = true;
					break;
				}
			}
		} else {
			System.out.println("Miss na p�gina "+pagina);
			if(livre()) {
				for(int i=0;i<this.memoria.length;i++) {
					if(this.memoria[i]==-1) {
						this.memoria[i] = pagina;
						break;
					}
				}
			} else {				
				while(this.segundaChance[this.ponteiro]){
					this.segundaChance[this.ponteiro] = false;
					ponteiro++;					
					if(this.ponteiro == this.memoria.length) ponteiro = 0;
				}
				
				System.out.println("Removeu p�gina "+this.memoria[ponteiro]);
				this.memoria[ponteiro] = pagina;	
				ponteiro++;		
				if(this.ponteiro == this.memoria.length) ponteiro = 0;
			}
			System.out.println("Inseriu p�gina "+pagina);
		}
	}	
	
	public void printMemoria() {
		System.out.print("Mem�ria: ");
		for(int i=0; i<this.memoria.length; i++) {
			if(i==ponteiro)	System.out.print("'"+this.memoria[i]+"'"+" | ");
			else System.out.print(this.memoria[i]+" | ");
		}
		System.out.println();
		System.out.println();
	}


}
