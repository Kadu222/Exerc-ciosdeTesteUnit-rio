package exercicio2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Testes do Exercicio 2: uma classe de equivalencia por estacao valida
 * (1 a 4) e valores invalidos fora do limite (0, 5, negativos, grandes).
 */
@DisplayName("Exercício 2 - Estações do ano")
class EstacoesDoAnoTest {

    private final EstacoesDoAno estacoes = new EstacoesDoAno();

    @Test
    @DisplayName("1 deve descrever o verão com o texto exato do enunciado")
    void numero1DeveDescreverVerao() {
        assertEquals("É verão\nE o tempo está quente.", estacoes.descreverEstacao(1));
    }

    @Test
    @DisplayName("2 deve descrever o outono")
    void numero2DeveDescreverOutono() {
        assertEquals("É outono\nE as folhas caem.", estacoes.descreverEstacao(2));
    }

    @Test
    @DisplayName("3 deve descrever o inverno com o texto exato do enunciado")
    void numero3DeveDescreverInverno() {
        assertEquals("É inverno\nE está frio.", estacoes.descreverEstacao(3));
    }

    @Test
    @DisplayName("4 deve descrever a primavera")
    void numero4DeveDescreverPrimavera() {
        assertEquals("É primavera\nE as flores desabrocham.", estacoes.descreverEstacao(4));
    }

    @ParameterizedTest(name = "descreverEstacao({0}) deve lançar IllegalArgumentException")
    @DisplayName("Valores fora do limite (0, 5, negativos e grandes) devem lançar exceção")
    @ValueSource(ints = {0, 5, -1, -100, 100, Integer.MAX_VALUE})
    void valoresForaDoLimiteDevemLancarExcecao(int numeroInvalido) {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class,
                () -> estacoes.descreverEstacao(numeroInvalido));
        assertEquals("Número inválido: " + numeroInvalido + ". Informe um valor de 1 a 4.",
                excecao.getMessage());
    }

    @Test
    @DisplayName("Todos os valores válidos (1 a 4) devem retornar uma descrição não vazia")
    void valoresValidosDevemRetornarDescricao() {
        assertAll(
                () -> assertEquals(EstacoesDoAno.VERAO, estacoes.descreverEstacao(1)),
                () -> assertEquals(EstacoesDoAno.OUTONO, estacoes.descreverEstacao(2)),
                () -> assertEquals(EstacoesDoAno.INVERNO, estacoes.descreverEstacao(3)),
                () -> assertEquals(EstacoesDoAno.PRIMAVERA, estacoes.descreverEstacao(4)));
    }
}
