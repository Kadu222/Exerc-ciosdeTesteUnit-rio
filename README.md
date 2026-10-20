# Exercícios de Teste Unitário — Java

Solução dos 5 exercícios da lista **"Exercícios de Teste Unitário"** (enunciado completo em [`docs/enunciado.pdf`](docs/enunciado.pdf)), implementados em **Java** com testes unitários em **JUnit 5**, gerenciados pelo **Gradle**.

Cada exercício está em um pacote próprio (`exercicio1` a `exercicio5`), com as classes de solução em `src/main/java` e as classes de teste em `src/test/java`.

---

## Requisitos

- **JDK 17 ou superior** (desenvolvido e testado com o JDK 23).
- **Gradle 8+** (desenvolvido e testado com o Gradle 9.0.0).
  - O arquivo `gradle.properties` fixa o JDK usado pelo Gradle (`org.gradle.java.home`). Ajuste esse caminho se o seu JDK estiver em outro local.
- VS Code com as extensões **Extension Pack for Java** e **Gradle for Java** (opcional, mas recomendado — dá suporte a execução e ao explorador de testes).

## Como compilar e executar

Abra um terminal na pasta do projeto (`exercicios-java/`) e use:

| O que fazer | Comando |
|---|---|
| Compilar tudo | `gradle build` |
| Rodar **todos os testes unitários** | `gradle test` |
| Rodar o **programa de demonstração** | `gradle run` |
| Relatório HTML dos testes | abrir `build/reports/tests/test/index.html` |

> No Windows, se o `gradle` não estiver no PATH, use o caminho completo do executável.

A classe principal (demonstração) é `app.ExemplosDeUso`. Também é possível rodá-la direto após compilar:

```
java -cp build/classes/java/main app.ExemplosDeUso
```

---

## Descrição dos exercícios e das soluções

### Exercício 1 — Paridade (`exercicio1.VerificadorParidade`)

Método booleano `ehPar(int numero)`: retorna `true` se o número for par e `false` caso seja ímpar, usando o resto da divisão por 2 (`numero % 2 == 0`). Funciona também para zero e negativos.

**Testes** (`VerificadorParidadeTest`, 16 casos): partição de equivalência entre pares e ímpares, incluindo `0`, negativos e valores grandes.

### Exercício 2 — Estações do ano (`exercicio2.EstacoesDoAno`)

Método `descreverEstacao(int numero)`: recebe um número de **1 a 4** (1 = verão, 2 = outono, 3 = inverno, 4 = primavera) e retorna a descrição da estação. Valores fora desse intervalo lançam `IllegalArgumentException`.

**Testes** (`EstacoesDoAnoTest`, 11 casos): uma classe de equivalência por estação e valores inválidos nas bordas e fora delas (`0`, `5`, negativos, `Integer.MAX_VALUE`).

### Exercício 3 — Nome Star Wars (`exercicio3.Pessoa`)

Classe `Pessoa` com atributos **nome**, **sobrenome**, **sobrenome de solteira da mãe** e **cidade natal** (encapsulados, com construtor e getters). O método `gerarNomeStarWars()` aplica a fórmula:

- **Primeiro nome** = 3 primeiras letras do sobrenome + 2 primeiras letras do nome;
- **Sobrenome** = 2 primeiras letras do sobrenome de solteira da mãe + 3 primeiras letras da cidade natal.

**Testes** (`PessoaTest`, 6 casos): fórmula completa, nomes mais curtos que o pedido (o método usa o que existir), espaços nas extremidades, getters e validação de dados nulos/vazios.

### Exercício 4 — Atleta (`exercicio4.Atleta`)

Classe `Atleta` com **nome**, **idade**, **altura** e **peso**, e três métodos:

- `classificarCategoriaPorIdade()`: pré-mirim (5–7), mirim (8–10), infantil (11–13), infanto-juvenil (14–17), juvenil (18–20) e adulto (21+);
- `calcularImc()`: IMC = massa / altura²;
- `classificarImc()` (e sobrecarga `classificarImc(double)`): < 18,5 Magreza; 18,5–24,9 Saudável; 25,0–29,9 Sobrepeso; 30,0–34,9 Obesidade Grau I; 35,0–39,9 Obesidade Grau II (severa); ≥ 40,0 Obesidade Grau III (mórbida).

**Testes** (`AtletaTest`, 32 casos): análise de valor-limite em todas as faixas de idade e de IMC, cálculo do IMC com tolerância e exceções para dados inválidos.

### Exercício 5 — Imposto de Renda (`exercicio5.Contribuinte`)

Classe `Contribuinte` com **nome**, **CPF**, **UF** (RS, PR e SC) e **renda anual**. O método `calcularImpostoAPagar()` aplica `Imposto = Renda Anual × Alíquota`, com a tabela do enunciado (0 a 4.000 → 0%; 4.001 a 9.000 → 5,8%; 9.001 a 25.000 → 15%; 25.001 a 35.000 → 27,5%; acima de 35.000 → 30%). O método estático `obterAliquota(double)` facilita testar as bordas da tabela.

**Testes** (`ContribuinteTest`, 22 casos): bordas de cada faixa, o exemplo do enunciado (R$ 40.000 a 30% = R$ 12.000), renda negativa e UF fora do permitido.

---

## Técnicas de teste aplicadas

- **Partição de equivalência**: classes válidas e inválidas para cada entrada (ex.: pares × ímpares; estações 1–4 × fora do intervalo).
- **Análise de valor-limite**: as duas bordas de cada faixa de idade, IMC e renda anual (ex.: 7→pré-mirim, 8→mirim; 4000→0%, 4001→5,8%).
- **Testes de exceção**: `assertThrows` para entradas fora do domínio (estação inválida, idade < 5, altura/peso não positivos, renda negativa, UF fora de RS/PR/SC, campos nulos/vazios).
- **Testes parametrizados** (`@ParameterizedTest` + `@ValueSource`/`@CsvSource`) para cobrir muitos valores sem repetição.
- **Teste do exemplo do enunciado** como caso de uso real (R$ 40.000 → R$ 12.000 de imposto).

**Total: 87 testes, todos passando** (`gradle test` → BUILD SUCCESSFUL).

---

## Decisões e premissas (pontos em que o enunciado era aberto)

1. **Exercício 2**: o enunciado dá os textos exatos apenas para verão e inverno; para **outono** e **primavera** foram usados textos no mesmo padrão ("É outono / E as folhas caem." e "É primavera / E as flores desabrocham."). Os textos dos exercícios 1 e 3 seguem exatamente o enunciado.
2. **Exercício 2**: a exceção lançada é `IllegalArgumentException`, padrão do Java para argumentos fora do domínio.
3. **Exercício 3**: nomes mais curtos do que a fórmula pede retornam o que existir (sem estourar `StringIndexOutOfBoundsException`).
4. **Exercício 4**: o enunciado não define categoria para menores de 5 anos — nesses casos o método lança `IllegalArgumentException`.
5. **Exercício 4**: na tabela de IMC, o valor **40,0** (e acima) é classificado como Obesidade Grau III (mórbida), cobrindo a lacuna entre "35,0–39,9" e "> 40,0".
6. **Exercício 5**: a UF é validada contra RS, PR e SC (com normalização para maiúsculas); renda anual negativa é rejeitada.
7. **Exercício 5**: o imposto é calculado sobre a renda anual inteira com a alíquota da faixa (fórmula simples do enunciado), e não de forma progressiva.

## Estrutura do projeto

```
exercicios-java/
├── build.gradle                 # configuração do Gradle (JUnit 5 + plugin application)
├── settings.gradle
├── gradle.properties            # JDK usado pelo Gradle
├── docs/
│   └── enunciado.pdf            # lista original de exercícios
└── src/
    ├── main/java/
    │   ├── app/ExemplosDeUso.java          # programa de demonstração (main)
    │   ├── exercicio1/VerificadorParidade.java
    │   ├── exercicio2/EstacoesDoAno.java
    │   ├── exercicio3/Pessoa.java
    │   ├── exercicio4/Atleta.java
    │   └── exercicio5/Contribuinte.java
    └── test/java/
        ├── exercicio1/VerificadorParidadeTest.java
        ├── exercicio2/EstacoesDoAnoTest.java
        ├── exercicio3/PessoaTest.java
        ├── exercicio4/AtletaTest.java
        └── exercicio5/ContribuinteTest.java
```
