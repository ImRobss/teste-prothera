# Teste Prático - Java

Projeto Java simples (sem Maven/Gradle), usando apenas a biblioteca padrão do JDK, desenvolvido para um teste prático de processo seletivo.

## Descrição

O programa modela `Pessoa` e `Funcionario` (que estende `Pessoa`) e, na classe `Principal`, executa em sequência todas as operações pedidas: cadastro, remoção, listagem formatada, aumento salarial, agrupamento por função, filtro de aniversariantes, funcionário mais velho, ordenação alfabética, soma de salários e conversão em salários mínimos.

## Requisitos atendidos

- **3.1** Inserção dos 10 funcionários na ordem da tabela do enunciado.
- **3.2** Remoção do funcionário "João" da lista.
- **3.3** Impressão de todos os funcionários com data no formato `dd/MM/yyyy` e valores no formato brasileiro (`19.119,88`).
- **3.4** Aumento de 10% no salário de todos os funcionários, atualizando a lista.
- **3.5 / 3.6** Agrupamento dos funcionários por função (`Map<String, List<Funcionario>>`) e impressão do agrupamento.
- **3.8** Impressão dos aniversariantes dos meses 10 e 12.
- **3.9** Impressão do funcionário com maior idade (nome e idade).
- **3.10** Impressão da lista em ordem alfabética por nome.
- **3.11** Impressão do total dos salários.
- **3.12** Impressão de quantos salários mínimos (R$ 1212,00) cada funcionário recebe.

Os itens 3.8 a 3.12 já utilizam os salários reajustados pelo item 3.4.

## Estrutura

```
src/br/com/teste/
├── Pessoa.java
├── Funcionario.java
└── Principal.java
```

## Como importar no Eclipse

1. `File > Import... > General > Existing Projects into Workspace`.
2. Selecione a pasta raiz deste repositório.
3. Confirme a importação (o projeto já contém `.project` e `.classpath`).

## Como executar

Pelo Eclipse: clique com o botão direito em `Principal.java` e escolha `Run As > Java Application`.

Pelo terminal:

```bash
javac -d bin src/br/com/teste/*.java
java -cp bin br.com.teste.Principal
```

Requer JDK 11 ou superior.
