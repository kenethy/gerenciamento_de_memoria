package Pagina��o;

import util.Processo;

public class SC extends Paginacao{

	@Override
	public void addMemoria(Integer pagina, Processo processo) {
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
				while(this.segundaChance[0]) {
					int paginaCurrent = this.memoria[0];
					//System.out.println("Removeu p�gina "+this.memoria[i]);
					this.memoria[0] = -1;
					reorganizar();
					reorganizarSegundaChance();
					this.memoria[this.memoria.length-1] = paginaCurrent;
				}
				
				System.out.println("Removeu p�gina "+this.memoria[0]);
				this.memoria[0] = -1;
				reorganizar();
				this.memoria[this.memoria.length-1] = pagina;
			}
			System.out.println("Inseriu p�gina "+pagina);
		}
	}
}
