package exercicio2;

/**
 * Exercicio 2: metodo que recebe um numero de 1 a 4 e descreve a estacao do ano.
 *
 * Mapeamento (conforme o enunciado):
 *   1 - verao | 2 - outono | 3 - inverno | 4 - primavera
 *
 * Observacao: o enunciado fornece os textos exatos apenas para verao e inverno.
 * Para outono e primavera foram adotados textos no mesmo padrao.
 */
public class EstacoesDoAno {

    public static final String VERAO = "É verão\nE o tempo está quente.";
    public static final String OUTONO = "É outono\nE as folhas caem.";
    public static final String INVERNO = "É inverno\nE está frio.";
    public static final String PRIMAVERA = "É primavera\nE as flores desabrocham.";

    /**
     * Recebe um numero de 1 a 4 e retorna a descricao da estacao correspondente.
     * Lanca IllegalArgumentException caso o parametro esteja fora do limite imposto.
     */
    public String descreverEstacao(int numero) {
        return switch (numero) {
            case 1 -> VERAO;
            case 2 -> OUTONO;
            case 3 -> INVERNO;
            case 4 -> PRIMAVERA;
            default -> throw new IllegalArgumentException(
                    "Número inválido: " + numero + ". Informe um valor de 1 a 4.");
        };
    }
}
