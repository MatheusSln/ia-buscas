import java.util.Scanner;

import espacoDeEstados.*;
import estrategiasDeBusca.cega.*;
public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Escolha um tipo de estratégia: \n "
						+ " 1 - Busca não informada \n "
						+ " 2 - Busca informada \n "
						+ " 3 - Busca em largura");
		
		int estrategia = teclado.nextInt();
		
		switch (estrategia) {
			case 1:
				BuscaNaoInformada();
				break;
			case 2:
				System.out.println("Digite o limite:");
				int limite = teclado.nextInt();
				BuscaInformada(limite);
				break;
			case 3:
				BuscaEmLargura();
				break;
			default: 
				System.out.println("Opção não encontrada :/");
				break;
		}
	}
	
	static Puzzle8 puzzleInicial = new Puzzle8();
		
	static Puzzle8 puzzleFinal = new Puzzle8();
	
	private static void SetCustosEAvaliacoes(char[] cfgIni, char[] cfgFim) {
		
		puzzleInicial.setEstado(cfgIni);
		puzzleInicial.setCusto(0);
		puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );
		
		puzzleFinal.setEstado(cfgFim);
		puzzleFinal.setCusto(0);
		puzzleFinal.setAvaliacao(0);
	}
	
	private static void BuscaEmLargura() {
		char[] cfgIni = {'2',' ','3','7','4','1','5','8','6'};
		char[] cfgFim = {'1','2','3','4','5','6','7',' ','8'};
		
		SetCustosEAvaliacoes(cfgIni, cfgFim);
		
		BuscaEmLargura buscaL = new BuscaEmLargura();
		
		buscaL.setInicio(puzzleInicial);
		buscaL.setObjetivo(puzzleFinal);
		
		buscaL.buscar();
		
		for(Estado e : buscaL.getCaminhoSolucao()) {
			System.out.println(e);
			System.out.println(" ");
		}
		
		 System.exit(0);
	}
	
	private static void BuscaInformada(int limite) {
		Scanner teclado = new Scanner(System.in);
		
		char[] cfgIni = {'2','3',' ','7','4','1','5','8','6'};
		char[] cfgFim = {'1','2','3','4','5','6','7','8',' '};
		
		SetCustosEAvaliacoes(cfgIni, cfgFim);
		
		BuscaEmProfundidadeLimitada buscaI = new BuscaEmProfundidadeLimitada();
		
		buscaI.setInicio(puzzleInicial);
		buscaI.setObjetivo(puzzleFinal);
		buscaI.setLimite(limite);
		
		try {
			buscaI.buscar();
		}catch (Exception e){
			System.out.println("O limite nao é: " + buscaI.getLimite());
			System.out.println("Digite um novo limite:");
			int novoLimite = teclado.nextInt();
			BuscaInformada(novoLimite);
		}
		
		for(Estado e : buscaI.getCaminhoSolucao()) {
			System.out.println(e);
			System.out.println(" ");
		}
		
		 System.exit(0);
	}
	
	private static void BuscaNaoInformada() {
		char[] cfgIni = {' ','2','3','7','4','1','5','8','6'};
		char[] cfgFim = {'1','2','3','4','5','6',' ','7','8'};
		
		SetCustosEAvaliacoes(cfgIni, cfgFim);
		
		BuscaEmProfundidadeLimitada buscaPL = new BuscaEmProfundidadeLimitada();
		
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