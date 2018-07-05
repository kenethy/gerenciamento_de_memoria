package Paginação;

import util.Processo;

public class SC extends Paginacao{

	@Override
	public void addMemoria(Integer pagina, Processo processo) {
		if(hit(pagina)) {
			System.out.println("Hit na página "+pagina);
			
			for(int i=0; i<this.memoria.length; i++) {
				if(pagina==this.memoria[i]) {
					this.segundaChance[i] = true;
					break;
				}
			}
		} else {
			System.out.println("Miss na página "+pagina);
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
					//System.out.println("Removeu página "+this.memoria[i]);
					this.memoria[0] = -1;
					reorganizar();
					reorganizarSegundaChance();
					this.memoria[this.memoria.length-1] = paginaCurrent;
				}
				
				System.out.println("Removeu página "+this.memoria[0]);
				this.memoria[0] = -1;
				reorganizar();
				this.memoria[this.memoria.length-1] = pagina;
			}
			System.out.println("Inseriu página "+pagina);
		}
	}
}
