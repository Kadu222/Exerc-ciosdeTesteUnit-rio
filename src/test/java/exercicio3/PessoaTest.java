package exercicio3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes do Exercicio 3: geracao do nome Star Wars a partir de
 * nome, sobrenome, sobrenome de solteira da mae e cidade natal.
 */
@DisplayName("Exercício 3 - Pessoa e nome Star Wars")
class PessoaTest {

    @Test
    @DisplayName("Deve gerar o nome Star Wars conforme a fórmula do enunciado")
    void deveGerarNomeStarWarsConformeFormula() {
        // Sil + An -> "SilAn" | So + Cur -> "SoCur"
        Pessoa pessoa = new Pessoa("Ana", "Silva", "Souza", "Curitiba");
        assertEquals("SilAn SoCur", pessoa.gerarNomeStarWars());
    }

    @Test
    @DisplayName("Deve gerar o nome Star Wars para outro exemplo")
    void deveGerarNomeStarWarsParaOutroExemplo() {
        // Lan + Ca -> "LanCa" | Fe + Joi -> "FeJoi"
        Pessoa pessoa = new Pessoa("Carla", "Lanzoni", "Ferreira", "Joinville");
        assertEquals("LanCa FeJoi", pessoa.gerarNomeStarWars());
    }

    @Test
    @DisplayName("Deve aceitar nomes mais curtos do que a fórmula pede")
    void deveAceitarNomesCurtos() {
        // sobrenome "Li" tem menos de 3 letras e nome "Bo" tem 2
        // mae "Su" tem 2 letras e cidade "Rec" tem 3
        Pessoa pessoa = new Pessoa("Bo", "Li", "Su", "Rec");
        assertEquals("LiBo SuRec", pessoa.gerarNomeStarWars());
    }

    @Test
    @DisplayName("Deve ignorar espaços digitados no início/fim dos dados")
    void deveIgnorarEspacosNasExtremidades() {
        Pessoa pessoa = new Pessoa("  Ana ", "  Silva ", " Souza ", " Curitiba ");
        assertEquals("SilAn SoCur", pessoa.gerarNomeStarWars());
    }

    @Test
    @DisplayName("Getters devem devolver exatamente os dados informados")
    void gettersDevemRetornarDadosInformados() {
        Pessoa pessoa = new Pessoa("Ana", "Silva", "Souza", "Curitiba");
        assertEquals("Ana", pessoa.getNome());
        assertEquals("Silva", pessoa.getSobrenome());
        assertEquals("Souza", pessoa.getSobrenomeDeSolteiraDaMae());
        assertEquals("Curitiba", pessoa.getCidadeNatal());
    }

    @Test
    @DisplayName("Dados nulos ou vazios devem lançar IllegalArgumentException")
    void dadosInvalidosDevemLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Pessoa(null, "Silva", "Souza", "Curitiba"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Ana", null, "Souza", "Curitiba"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Ana", "Silva", null, "Curitiba"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("Ana", "Silva", "Souza", null));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("", "Silva", "Souza", "Curitiba"));
        assertThrows(IllegalArgumentException.class, () -> new Pessoa("   ", "Silva", "Souza", "Curitiba"));
    }
}
