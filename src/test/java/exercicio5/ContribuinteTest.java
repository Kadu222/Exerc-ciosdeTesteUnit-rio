package exercicio5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Testes do Exercicio 5 usando analise de valor-limite na tabela de
 * aliquotas: cada faixa e testada na primeira e na ultima renda dela.
 */
@DisplayName("Exercício 5 - Imposto de Renda")
class ContribuinteTest {

    @ParameterizedTest(name = "renda anual R$ {0} -> alíquota {1}")
    @DisplayName("Alíquotas nas bordas de cada faixa da tabela")
    @CsvSource({
            "0.0,    0.0",
            "4000.0, 0.0",
            "4001.0, 0.058",
            "9000.0, 0.058",
            "9001.0, 0.15",
            "25000.0, 0.15",
            "25001.0, 0.275",
            "35000.0, 0.275",
            "35001.0, 0.30",
            "100000.0, 0.30"
    })
    void deveObterAliquotaNasBordas(double rendaAnual, double aliquotaEsperada) {
        assertEquals(aliquotaEsperada, Contribuinte.obterAliquota(rendaAnual), 0.000001);
    }

    @Test
    @DisplayName("Exemplo do enunciado: renda de R$ 40.000 a 30% deve pagar R$ 12.000")
    void exemploDoEnunciadoJoaoDaSilva() {
        Contribuinte joao = new Contribuinte("João da Silva", "123.456.789-00", "PR", 40000.0);
        assertEquals(0.30, joao.obterAliquota(), 0.000001);
        assertEquals(12000.0, joao.calcularImpostoAPagar(), 0.000001);
    }

    @ParameterizedTest(name = "renda anual R$ {0} -> imposto R$ {1}")
    @DisplayName("Imposto a pagar em cada faixa")
    @CsvSource({
            "2000.0,  0.0",
            "4000.0,  0.0",
            "6000.0,  348.0",
            "10000.0, 1500.0",
            "30000.0, 8250.0",
            "40000.0, 12000.0"
    })
    void deveCalcularImpostoAPagarEmCadaFaixa(double rendaAnual, double impostoEsperado) {
        Contribuinte contribuinte = new Contribuinte("Maria", "987.654.321-00", "SC", rendaAnual);
        assertEquals(impostoEsperado, contribuinte.calcularImpostoAPagar(), 0.000001);
    }

    @Test
    @DisplayName("Renda anual negativa deve lançar exceção")
    void rendaNegativaDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contribuinte("Maria", "987.654.321-00", "SC", -1.0));
        assertThrows(IllegalArgumentException.class, () -> Contribuinte.obterAliquota(-100));
    }

    @Test
    @DisplayName("UF fora de RS, PR e SC deve lançar exceção")
    void ufInvalidaDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contribuinte("Maria", "987.654.321-00", "SP", 10000.0));
        assertThrows(IllegalArgumentException.class,
                () -> new Contribuinte("Maria", "987.654.321-00", "RJ", 10000.0));
        assertThrows(IllegalArgumentException.class,
                () -> new Contribuinte("Maria", "987.654.321-00", null, 10000.0));
    }

    @Test
    @DisplayName("UF deve ser aceita em qualquer forma e normalizada para maiúsculas")
    void ufDeveSerNormalizada() {
        Contribuinte contribuinte = new Contribuinte("Maria", "987.654.321-00", "rs", 10000.0);
        assertEquals("RS", contribuinte.getUf());
    }

    @Test
    @DisplayName("Getters devem devolver os dados do contribuinte")
    void gettersDevemRetornarDados() {
        Contribuinte contribuinte = new Contribuinte("João da Silva", "123.456.789-00", "PR", 40000.0);
        assertEquals("João da Silva", contribuinte.getNome());
        assertEquals("123.456.789-00", contribuinte.getCpf());
        assertEquals("PR", contribuinte.getUf());
        assertEquals(40000.0, contribuinte.getRendaAnual(), 0.000001);
    }

    @Test
    @DisplayName("Nome, CPF nulos/vazios devem lançar exceção")
    void nomeECpfInvalidosDevemLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contribuinte(null, "123.456.789-00", "PR", 10000.0));
        assertThrows(IllegalArgumentException.class,
                () -> new Contribuinte("Maria", null, "PR", 10000.0));
    }
}
