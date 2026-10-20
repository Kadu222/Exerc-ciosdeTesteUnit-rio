package exercicio4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Testes do Exercicio 4 usando analise de valor-limite:
 * cada faixa de idade e de IMC e testada nas duas bordas.
 */
@DisplayName("Exercício 4 - Atleta: categoria, IMC e classificação do IMC")
class AtletaTest {

    @ParameterizedTest(name = "idade {0} -> {1}")
    @DisplayName("Categorias por idade nas bordas de cada faixa")
    @CsvSource({
            "5,  pré-mirim",
            "7,  pré-mirim",
            "8,  mirim",
            "10, mirim",
            "11, infantil",
            "13, infantil",
            "14, infanto-juvenil",
            "17, infanto-juvenil",
            "18, juvenil",
            "20, juvenil",
            "21, adulto",
            "45, adulto"
    })
    void deveClassificarCategoriaPorIdade(int idade, String categoriaEsperada) {
        Atleta atleta = new Atleta("Teste", idade, 1.70, 65.0);
        assertEquals(categoriaEsperada, atleta.classificarCategoriaPorIdade());
    }

    @ParameterizedTest(name = "idade {0} deve lançar exceção")
    @DisplayName("Idades abaixo de 5 anos não pertencem a nenhuma categoria")
    @ValueSource(ints = {0, 1, 4, -3})
    void idadeAbaixoDoMinimoDeveLancarExcecao(int idadeInvalida) {
        Atleta atleta = new Atleta("Teste", idadeInvalida, 1.70, 65.0);
        assertThrows(IllegalArgumentException.class, atleta::classificarCategoriaPorIdade);
    }

    @Test
    @DisplayName("Deve calcular o IMC = massa / altura²")
    void deveCalcularImc() {
        Atleta atleta = new Atleta("Marina", 16, 1.68, 62.0);
        assertEquals(62.0 / (1.68 * 1.68), atleta.calcularImc(), 0.0001);
    }

    @Test
    @DisplayName("IMC de 70 kg e 1,75 m deve ser aproximadamente 22,86")
    void imcExemploConhecido() {
        Atleta atleta = new Atleta("Lucas", 21, 1.75, 70.0);
        assertEquals(22.86, atleta.calcularImc(), 0.01);
    }

    @Test
    @DisplayName("Altura ou peso inválidos devem lançar exceção")
    void medidasInvalidasDevemLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Atleta("Teste", 20, 0, 65.0).calcularImc());
        assertThrows(IllegalArgumentException.class,
                () -> new Atleta("Teste", 20, -1.70, 65.0).calcularImc());
        assertThrows(IllegalArgumentException.class,
                () -> new Atleta("Teste", 20, 1.70, 0).calcularImc());
        assertThrows(IllegalArgumentException.class,
                () -> new Atleta("Teste", 20, 1.70, -65).calcularImc());
    }

    @ParameterizedTest(name = "IMC {0} -> {1}")
    @DisplayName("Classificação do IMC nas bordas de cada faixa da tabela")
    @CsvSource({
            "18.4, Magreza",
            "18.5, Saudável",
            "24.9, Saudável",
            "25.0, Sobrepeso",
            "29.9, Sobrepeso",
            "30.0, Obesidade Grau I",
            "34.9, Obesidade Grau I",
            "35.0, Obesidade Grau II (severa)",
            "39.9, Obesidade Grau II (severa)",
            "40.0, Obesidade Grau III (mórbida)",
            "45.5, Obesidade Grau III (mórbida)"
    })
    void deveClassificarImcNasBordas(double imc, String classificacaoEsperada) {
        Atleta atleta = new Atleta("Teste", 20, 1.70, 65.0);
        assertEquals(classificacaoEsperada, atleta.classificarImc(imc));
    }

    @Test
    @DisplayName("classificarImc() deve usar o IMC calculado do próprio atleta")
    void classificarImcDeveUsarImcDoProprioAtleta() {
        // IMC = 110 / (1.80 * 1.80) = 33.95 -> Obesidade Grau I
        Atleta atleta = new Atleta("Roberto", 32, 1.80, 110.0);
        assertEquals("Obesidade Grau I", atleta.classificarImc());
    }

    @Test
    @DisplayName("Nome nulo ou vazio deve lançar exceção")
    void nomeInvalidoDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Atleta(null, 20, 1.70, 65.0));
        assertThrows(IllegalArgumentException.class, () -> new Atleta("   ", 20, 1.70, 65.0));
    }
}
