package Paginação;

import util.Processo;

public class FIFO extends Paginacao{

	@Override
	public void addMemoria(Integer pagina, Processo processo) {
		if(miss(pagina)) {
			System.out.println("Miss na página "+pagina);
		} else {
			System.out.println("Hit na página "+pagina);
			if(livre()) {
				for(int i=0;i<this.memoria.length;i++) {
					if(this.memoria[i]==-1) {
						this.memoria[i] = pagina;
						break;
					}
				}
			} else {
				System.out.println("Removeu página "+this.memoria[0]);
				this.memoria[0] = -1;
				reorganizar();
				this.memoria[this.memoria.length-1] = pagina;
			}
			System.out.println("Inseriu página "+pagina);
		}
	}
}
