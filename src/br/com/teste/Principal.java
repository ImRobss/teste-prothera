package br.com.teste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Classe principal: executa, em ordem, todos os itens do enunciado.
 */
public class Principal {

	// Formatação centralizada, usada em todo o programa.
	private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DecimalFormat FORMATO_NUMERICO = new DecimalFormat("#,##0.00",
			new DecimalFormatSymbols(new Locale("pt", "BR")));
	private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

	public static void main(String[] args) {
		List<Funcionario> funcionarios = new ArrayList<>();

		inserirFuncionarios(funcionarios); // 3.1
		removerJoao(funcionarios); // 3.2
		imprimirFuncionarios(funcionarios); // 3.3
		aumentarSalarios(funcionarios); // 3.4

		Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao(funcionarios); // 3.5
		imprimirFuncionariosPorFuncao(funcionariosPorFuncao); // 3.6

		imprimirAniversariantesOutubroDezembro(funcionarios); // 3.8
		imprimirFuncionarioMaisVelho(funcionarios); // 3.9
		imprimirFuncionariosEmOrdemAlfabetica(funcionarios); // 3.10
		imprimirTotalSalarios(funcionarios); // 3.11
		imprimirSalariosMinimos(funcionarios); // 3.12
	}

	// 3.1 - Inserir todos os funcionários, na mesma ordem e com as informações da tabela.
	private static void inserirFuncionarios(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.1 - Inserção dos funcionários");

		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

		System.out.println(funcionarios.size() + " funcionários inseridos.");
	}

	// 3.2 - Remover o funcionário "João" da lista.
	private static void removerJoao(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.2 - Remoção do funcionário João");
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
		System.out.println("Funcionário João removido. Total atual: " + funcionarios.size());
	}

	// 3.3 - Imprimir todos os funcionários com todas as suas informações.
	private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.3 - Listagem de todos os funcionários");
		funcionarios.forEach(Principal::imprimirFuncionario);
	}

	// 3.4 - Aplicar 10% de aumento de salário e atualizar a lista.
	private static void aumentarSalarios(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.4 - Aumento de 10% nos salários");
		BigDecimal fatorAumento = new BigDecimal("1.10");
		for (Funcionario funcionario : funcionarios) {
			BigDecimal novoSalario = funcionario.getSalario().multiply(fatorAumento).setScale(2, RoundingMode.HALF_UP);
			funcionario.setSalario(novoSalario);
		}
		funcionarios.forEach(Principal::imprimirFuncionario);
	}

	// 3.5 - Agrupar os funcionários por função em um Map.
	private static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
		return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
	}

	// 3.6 - Imprimir os funcionários agrupados por função.
	private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
		imprimirCabecalho("3.6 - Funcionários agrupados por função");
		// TreeMap para manter a impressão em ordem alfabética de função.
		Map<String, List<Funcionario>> ordenadoPorFuncao = new TreeMap<>(funcionariosPorFuncao);
		for (Map.Entry<String, List<Funcionario>> entrada : ordenadoPorFuncao.entrySet()) {
			System.out.println("Função: " + entrada.getKey());
			entrada.getValue().forEach(Principal::imprimirFuncionario);
		}
	}

	// 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12.
	private static void imprimirAniversariantesOutubroDezembro(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.8 - Aniversariantes de outubro e dezembro");
		funcionarios.stream()
				.filter(funcionario -> {
					int mes = funcionario.getDataNascimento().getMonthValue();
					return mes == 10 || mes == 12;
				})
				.forEach(Principal::imprimirFuncionario);
	}

	// 3.9 - Imprimir o funcionário com a maior idade (nome e idade).
	private static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.9 - Funcionário com maior idade");
		funcionarios.stream()
				.min(Comparator.comparing(Funcionario::getDataNascimento))
				.ifPresent(funcionario -> {
					int idade = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
					System.out.println("Nome: " + funcionario.getNome() + " | Idade: " + idade + " anos");
				});
	}

	// 3.10 - Imprimir a lista de funcionários em ordem alfabética.
	private static void imprimirFuncionariosEmOrdemAlfabetica(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.10 - Funcionários em ordem alfabética");
		funcionarios.stream()
				.sorted(Comparator.comparing(Funcionario::getNome))
				.forEach(Principal::imprimirFuncionario);
	}

	// 3.11 - Imprimir o total dos salários dos funcionários.
	private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.11 - Total dos salários");
		BigDecimal total = funcionarios.stream()
				.map(Funcionario::getSalario)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println("Total: " + FORMATO_NUMERICO.format(total));
	}

	// 3.12 - Imprimir quantos salários mínimos ganha cada funcionário.
	private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
		imprimirCabecalho("3.12 - Quantidade de salários mínimos por funcionário");
		for (Funcionario funcionario : funcionarios) {
			BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
			System.out.println(funcionario.getNome() + ": " + FORMATO_NUMERICO.format(quantidadeSalariosMinimos)
					+ " salários mínimos");
		}
	}

	// Impressão reutilizável de um funcionário, com todas as informações formatadas.
	private static void imprimirFuncionario(Funcionario funcionario) {
		System.out.println("Nome: " + funcionario.getNome()
				+ " | Data Nascimento: " + funcionario.getDataNascimento().format(FORMATO_DATA)
				+ " | Salário: " + FORMATO_NUMERICO.format(funcionario.getSalario())
				+ " | Função: " + funcionario.getFuncao());
	}

	private static void imprimirCabecalho(String titulo) {
		System.out.println();
		System.out.println("=== " + titulo + " ===");
	}
}
