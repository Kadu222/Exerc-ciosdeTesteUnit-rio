package app;

import exercicio1.VerificadorParidade;
import exercicio2.EstacoesDoAno;
import exercicio3.Pessoa;
import exercicio4.Atleta;
import exercicio5.Contribuinte;

/**
 * Programa de demonstracao: executa um exemplo de cada exercicio e imprime
 * os resultados no console. Os testes de verdade ficam no JUnit (gradle test).
 */
public class ExemplosDeUso {

    public static void main(String[] args) {
        System.out.println("========== EXERCICIO 1: Paridade ==========");
        VerificadorParidade paridade = new VerificadorParidade();
        System.out.println("ehPar(10) = " + paridade.ehPar(10));
        System.out.println("ehPar(7)  = " + paridade.ehPar(7));
        System.out.println("ehPar(0)  = " + paridade.ehPar(0));

        System.out.println();
        System.out.println("========== EXERCICIO 2: Estacoes do ano ==========");
        EstacoesDoAno estacoes = new EstacoesDoAno();
        System.out.println(estacoes.descreverEstacao(1));
        System.out.println("---");
        System.out.println(estacoes.descreverEstacao(3));
        try {
            estacoes.descreverEstacao(9);
        } catch (IllegalArgumentException e) {
            System.out.println("descreverEstacao(9) -> excecao: " + e.getMessage());
        }

        System.out.println();
        System.out.println("========== EXERCICIO 3: Nome Star Wars ==========");
        Pessoa pessoa = new Pessoa("Carlos", "Landim", "Ferreira", "Joinville");
        System.out.println("Pessoa: " + pessoa.getNome() + " " + pessoa.getSobrenome()
                + " | mae: " + pessoa.getSobrenomeDeSolteiraDaMae()
                + " | cidade: " + pessoa.getCidadeNatal());
        System.out.println("Nome Star Wars: " + pessoa.gerarNomeStarWars());

        System.out.println();
        System.out.println("========== EXERCICIO 4: Atleta ==========");
        Atleta atleta = new Atleta("Marina", 16, 1.68, 62.0);
        System.out.println("Atleta: " + atleta.getNome()
                + " | categoria: " + atleta.classificarCategoriaPorIdade()
                + " | IMC: " + String.format("%.2f", atleta.calcularImc())
                + " (" + atleta.classificarImc() + ")");
        Atleta atletaAdulto = new Atleta("Roberto", 32, 1.80, 110.0);
        System.out.println("Atleta: " + atletaAdulto.getNome()
                + " | categoria: " + atletaAdulto.classificarCategoriaPorIdade()
                + " | IMC: " + String.format("%.2f", atletaAdulto.calcularImc())
                + " (" + atletaAdulto.classificarImc() + ")");

        System.out.println();
        System.out.println("========== EXERCICIO 5: Imposto de Renda ==========");
        Contribuinte contribuinte = new Contribuinte("João da Silva", "123.456.789-00", "PR", 40000.0);
        System.out.println("Contribuinte: " + contribuinte.getNome()
                + " | CPF: " + contribuinte.getCpf()
                + " | UF: " + contribuinte.getUf()
                + " | Renda anual: R$" + String.format("%,.2f", contribuinte.getRendaAnual()));
        System.out.println("Aliquota: " + (contribuinte.obterAliquota() * 100) + "%");
        System.out.println("Imposto a pagar: R$" + String.format("%,.2f", contribuinte.calcularImpostoAPagar()));
    }
}
