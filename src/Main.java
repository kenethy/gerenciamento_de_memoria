import particoes_variaveis.FirstFit;
import particoes_variaveis.NextFit;
import util.Memoria;

/**
 * @author Andr� Lins
 * @author C�sar Muniz
 * @author Kenedy Silva
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Memoria m = new FirstFit();;
		Memoria m = new NextFit();
		m.run();
	}

}
