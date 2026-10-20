package exercicio4;

/**
 * Exercicio 4: classe Atleta com classificacao por idade, calculo do IMC
 * e classificacao do IMC conforme a tabela do enunciado.
 */
public class Atleta {

    public static final String PRE_MIRIM = "pré-mirim";
    public static final String MIRIM = "mirim";
    public static final String INFANTIL = "infantil";
    public static final String INFANTO_JUVENIL = "infanto-juvenil";
    public static final String JUVENIL = "juvenil";
    public static final String ADULTO = "adulto";

    public static final String MAGREZA = "Magreza";
    public static final String SAUDAVEL = "Saudável";
    public static final String SOBREPESO = "Sobrepeso";
    public static final String OBESIDADE_GRAU_I = "Obesidade Grau I";
    public static final String OBESIDADE_GRAU_II = "Obesidade Grau II (severa)";
    public static final String OBESIDADE_GRAU_III = "Obesidade Grau III (mórbida)";

    private final String nome;
    private final int idade;
    private final double altura;
    private final double peso;

    public Atleta(String nome, int idade, double altura, double peso) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    /**
     * Classifica o atleta em uma categoria de acordo com a idade:
     *   pre-mirim 5 a 7 | mirim 8 a 10 | infantil 11 a 13 |
     *   infanto-juvenil 14 a 17 | juvenil 18 a 20 | adulto maiores de 21.
     *
     * O enunciado nao define categoria para menores de 5 anos;
     * nesses casos lanca IllegalArgumentException.
     */
    public String classificarCategoriaPorIdade() {
        if (idade < 5) {
            throw new IllegalArgumentException(
                    "Idade " + idade + " fora das categorias (a partir de 5 anos).");
        }
        if (idade <= 7) {
            return PRE_MIRIM;
        }
        if (idade <= 10) {
            return MIRIM;
        }
        if (idade <= 13) {
            return INFANTIL;
        }
        if (idade <= 17) {
            return INFANTO_JUVENIL;
        }
        if (idade <= 20) {
            return JUVENIL;
        }
        return ADULTO;
    }

    /**
     * Calcula o IMC do atleta: IMC = massa / altura^2.
     * Lanca IllegalArgumentException se altura ou peso forem invalidos.
     */
    public double calcularImc() {
        if (altura <= 0) {
            throw new IllegalArgumentException("Altura deve ser maior que zero.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior que zero.");
        }
        return peso / (altura * altura);
    }

    /**
     * Classifica o IMC do proprio atleta conforme a tabela do enunciado.
     */
    public String classificarImc() {
        return classificarImc(calcularImc());
    }

    /**
     * Classifica um valor de IMC conforme a tabela do enunciado:
     *   < 18,5 Magreza | 18,5-24,9 Saudavel | 25,0-29,9 Sobrepeso |
     *   30,0-34,9 Obesidade Grau I | 35,0-39,9 Obesidade Grau II (severa) |
     *   >= 40,0 Obesidade Grau III (morbida).
     */
    public String classificarImc(double imc) {
        if (imc < 18.5) {
            return MAGREZA;
        }
        if (imc < 25.0) {
            return SAUDAVEL;
        }
        if (imc < 30.0) {
            return SOBREPESO;
        }
        if (imc < 35.0) {
            return OBESIDADE_GRAU_I;
        }
        if (imc < 40.0) {
            return OBESIDADE_GRAU_II;
        }
        return OBESIDADE_GRAU_III;
    }
}
