package exercicio5;

import java.util.Set;

/**
 * Exercicio 5: contribuinte do Imposto de Renda e calculo do imposto a pagar.
 *
 * Dados: NOME, CPF, UF (RS, PR e SC) e RENDA ANUAL.
 *
 * Tabela de aliquotas do enunciado:
 *   0 a 4.000            0%
 *   4.001 a 9.000        5,8%
 *   9.001 a 25.000       15%
 *   25.001 a 35.000      27,5%
 *   acima de 35.000      30%
 *
 * Imposto a pagar = Renda Anual * Aliquota
 */
public class Contribuinte {

    public static final Set<String> UFS_VALIDAS = Set.of("RS", "PR", "SC");

    private final String nome;
    private final String cpf;
    private final String uf;
    private final double rendaAnual;

    public Contribuinte(String nome, String cpf, String uf, double rendaAnual) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        if (uf == null || uf.isBlank()) {
            throw new IllegalArgumentException("UF não pode ser nula ou vazia.");
        }
        String ufNormalizada = uf.trim().toUpperCase();
        if (!UFS_VALIDAS.contains(ufNormalizada)) {
            throw new IllegalArgumentException("UF inválida: " + uf + ". Aceitos apenas RS, PR e SC.");
        }
        if (rendaAnual < 0) {
            throw new IllegalArgumentException("Renda anual não pode ser negativa.");
        }

        this.nome = nome.trim();
        this.cpf = cpf.trim();
        this.uf = ufNormalizada;
        this.rendaAnual = rendaAnual;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getUf() {
        return uf;
    }

    public double getRendaAnual() {
        return rendaAnual;
    }

    /**
     * Retorna a aliquota aplicavel a renda anual do contribuinte.
     */
    public double obterAliquota() {
        return obterAliquota(rendaAnual);
    }

    /**
     * Retorna a aliquota aplicavel a uma renda anual informada.
     */
    public static double obterAliquota(double rendaAnual) {
        if (rendaAnual < 0) {
            throw new IllegalArgumentException("Renda anual não pode ser negativa.");
        }
        if (rendaAnual <= 4000) {
            return 0.0;
        }
        if (rendaAnual <= 9000) {
            return 0.058;
        }
        if (rendaAnual <= 25000) {
            return 0.15;
        }
        if (rendaAnual <= 35000) {
            return 0.275;
        }
        return 0.30;
    }

    /**
     * Calcula o imposto a pagar: Imposto = Renda Anual * Aliquota.
     */
    public double calcularImpostoAPagar() {
        return rendaAnual * obterAliquota();
    }
}
