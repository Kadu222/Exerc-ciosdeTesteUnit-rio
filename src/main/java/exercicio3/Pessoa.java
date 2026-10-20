package exercicio3;

/**
 * Exercicio 3: gerador de nome Star Wars.
 *
 * Formula do enunciado:
 *   Primeiro nome na serie Star Wars:
 *     1. As tres primeiras letras do sobrenome;
 *     2. Mais as duas primeiras letras do proprio nome.
 *   Sobrenome na serie Star Wars:
 *     1. As duas primeiras letras do sobrenome de solteira da mae;
 *     2. Mais as tres primeiras letras do nome da cidade natal.
 */
public class Pessoa {

    private final String nome;
    private final String sobrenome;
    private final String sobrenomeDeSolteiraDaMae;
    private final String cidadeNatal;

    public Pessoa(String nome, String sobrenome, String sobrenomeDeSolteiraDaMae, String cidadeNatal) {
        validar("Nome", nome);
        validar("Sobrenome", sobrenome);
        validar("Sobrenome de solteira da mãe", sobrenomeDeSolteiraDaMae);
        validar("Cidade natal", cidadeNatal);

        this.nome = nome.trim();
        this.sobrenome = sobrenome.trim();
        this.sobrenomeDeSolteiraDaMae = sobrenomeDeSolteiraDaMae.trim();
        this.cidadeNatal = cidadeNatal.trim();
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getSobrenomeDeSolteiraDaMae() {
        return sobrenomeDeSolteiraDaMae;
    }

    public String getCidadeNatal() {
        return cidadeNatal;
    }

    /**
     * Gera o nome completo Star Wars conforme a formula do enunciado.
     */
    public String gerarNomeStarWars() {
        String primeiroNomeStarWars = primeirasLetras(sobrenome, 3) + primeirasLetras(nome, 2);
        String sobrenomeStarWars = primeirasLetras(sobrenomeDeSolteiraDaMae, 2) + primeirasLetras(cidadeNatal, 3);
        return primeiroNomeStarWars + " " + sobrenomeStarWars;
    }

    /**
     * Retorna as primeiras letras do texto. Se o texto for mais curto que a
     * quantidade pedida, retorna o texto inteiro (evita StringIndexOutOfBounds).
     */
    private String primeirasLetras(String texto, int quantidade) {
        if (texto.length() <= quantidade) {
            return texto;
        }
        return texto.substring(0, quantidade);
    }

    private static void validar(String nomeDoCampo, String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(nomeDoCampo + " não pode ser nulo ou vazio.");
        }
    }
}
