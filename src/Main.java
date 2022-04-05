import espacoDeEstados.*;
import estrategiasDeBusca.cega.BuscaEmProfundidadeLimitada;
public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		//char[] cfgIni = {' ','2','3','1','4','6','7','5','8'};
		//char[] cfgIni = {'2','4','3','7','1','6','5',' ','8'};
		char[] cfgIni = {'2','3',' ','7','4','1','5','8','6'};
		//char[] cfgIni = {'7','2','3','4',' ','1','5','8','6'}; // OutOfMemory
                char[] cfgFim = {'1','2','3','4','5','6','7','8',' '};
                        

		Puzzle8 puzzleInicial = new Puzzle8();
		puzzleInicial.setEstado(cfgIni);
		puzzleInicial.setCusto(0);
		puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );
			
		Puzzle8 puzzleFinal = new Puzzle8();
		puzzleFinal.setEstado(cfgFim);
		puzzleFinal.setCusto(0);
		puzzleFinal.setAvaliacao(0);
					
                BuscaEmProfundidadeLimitada buscaPL = new BuscaEmProfundidadeLimitada(); //buscaPI = Busca de pronfundidade limitada

		buscaPL.setInicio(puzzleInicial);
		buscaPL.setObjetivo(puzzleFinal);
		buscaPL.setLimite(0);

		while (buscaPL.getCaminhoSolucao().isEmpty()) {
			try{
				buscaPL.buscar();
			}catch (Exception e){
				System.out.println("O limite nao é: " + buscaPL.getLimite());
			}
			buscaPL.setLimite(buscaPL.getLimite() + 1);
		}

		for(Estado e : buscaPL.getCaminhoSolucao()) {
			System.out.println(e);
			System.out.println(" ");
		}
                
                System.exit(0);
	}
}