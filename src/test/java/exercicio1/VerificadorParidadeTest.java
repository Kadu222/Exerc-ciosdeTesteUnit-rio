package exercicio1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Testes do Exercicio 1 usando particao de equivalencia:
 * numeros pares (true) x numeros impares (false), incluindo
 * zero, negativos e valores grandes.
 */
@DisplayName("Exercício 1 - Verificador de paridade")
class VerificadorParidadeTest {

    private final VerificadorParidade verificador = new VerificadorParidade();

    @Test
    @DisplayName("Deve retornar true para um número par positivo")
    void deveRetornarTrueParaNumeroParPositivo() {
        assertTrue(verificador.ehPar(4),
                "4 é par, então o método deveria retornar true");
    }

    @Test
    @DisplayName("Deve retornar false para um número ímpar positivo")
    void deveRetornarFalseParaNumeroImparPositivo() {
        assertFalse(verificador.ehPar(7),
                "7 é ímpar, então o método deveria retornar false");
    }

    @ParameterizedTest(name = "ehPar({0}) deve retornar true")
    @DisplayName("Classe de equivalência dos pares: zero, positivos e negativos")
    @ValueSource(ints = {0, 2, 10, 100, 1_000_000, -2, -48})
    void deveReconhecerNumerosPares(int numero) {
        assertTrue(verificador.ehPar(numero));
    }

    @ParameterizedTest(name = "ehPar({0}) deve retornar false")
    @DisplayName("Classe de equivalência dos ímpares: positivos e negativos")
    @ValueSource(ints = {1, 3, 99, 999_999, -1, -7})
    void deveReconhecerNumerosImpares(int numero) {
        assertFalse(verificador.ehPar(numero));
    }

    @Test
    @DisplayName("Zero deve ser considerado par")
    void zeroDeveSerPar() {
        assertTrue(verificador.ehPar(0));
    }
}
