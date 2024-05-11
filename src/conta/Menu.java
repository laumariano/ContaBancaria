package conta;

import java.util.Scanner;
import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.repository.ContaRepository;
import conta.repository.ContaRepositoryImpl;
import java.util.List;

public class Menu {
    private static ContaController contaController;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ContaRepositoryImpl contaRepository = new ContaRepositoryImpl();
        contaController = new ContaController(contaRepository);

        int opcao = -1;
        do {
            try {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Procurar conta por número");
                System.out.println("2 - Listar todas as contas");
                System.out.println("3 - Cadastrar nova conta");
                System.out.println("4 - Atualizar conta");
                System.out.println("5 - Deletar conta");
                System.out.println("6 - Sacar");
                System.out.println("7 - Depositar");
                System.out.println("8 - Transferir");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o número da conta: ");
                        int numeroConta = scanner.nextInt();
                        Conta contaProcurada = contaController.procurarPorNumero(numeroConta);
                        if (contaProcurada != null) {
                            System.out.println("Conta encontrada: " + contaProcurada);
                        } else {
                            System.out.println("Conta não encontrada.");
                        }
                        break;
                    case 2:
                        System.out.println("Listando todas as contas:");
                        List<Conta> todasAsContas = contaController.listarTodas();
                        for (Conta conta : todasAsContas) {
                            System.out.println(conta);
                        }
                        break;
                    case 3:
                        System.out.print("Digite o número da nova conta: ");
                        int novoNumeroConta = scanner.nextInt();
                        System.out.println("Escolha o tipo de conta:");
                        System.out.println("1 - Conta Corrente");
                        System.out.println("2 - Conta Poupança");
                        int tipoConta = scanner.nextInt();
                        Conta novaConta;
                        if (tipoConta == 1) {
                            novaConta = new ContaCorrente(novoNumeroConta, 0);
                        } else if (tipoConta == 2) {
                            novaConta = new ContaPoupanca(novoNumeroConta, 0);
                        } else {
                            System.out.println("Opção inválida.");
                            break;
                        }
                        contaController.cadastrar(novaConta);
                        System.out.println("Nova conta cadastrada com sucesso.");
                        break;

                    case 4:
                        System.out.print("Digite o número da conta a ser atualizada: ");
                        int numeroContaAtualizar = scanner.nextInt();
                        Conta contaAtualizar = contaController.procurarPorNumero(numeroContaAtualizar);
                        if (contaAtualizar != null) {
                            System.out.println("Conta encontrada: " + contaAtualizar);
                            System.out.print("Digite o novo saldo da conta: ");
                            double novoSaldo = scanner.nextDouble();
                            contaAtualizar.setSaldo(novoSaldo);
                            contaController.atualizar(contaAtualizar);
                            System.out.println("Conta atualizada com sucesso.");
                        } else {
                            System.out.println("Conta não encontrada.");
                        }
                        break;

                    case 5:
                        System.out.print("Digite o número da conta a ser deletada: ");
                        int numeroContaDeletar = scanner.nextInt();
                        contaController.deletar(numeroContaDeletar);
                        System.out.println("Conta deletada com sucesso.");
                        break;
                    case 6:
                        System.out.print("Digite o número da conta: ");
                        int numeroContaSacar = scanner.nextInt();
                        System.out.print("Digite o valor a ser sacado: ");
                        double valorSacar = scanner.nextDouble();
                        contaController.sacar(numeroContaSacar, valorSacar);
                        break;
                    case 7:
                        System.out.print("Digite o número da conta: ");
                        int numeroContaDepositar = scanner.nextInt();
                        System.out.print("Digite o valor a ser depositado: ");
                        double valorDepositar = scanner.nextDouble();
                        contaController.depositar(numeroContaDepositar, valorDepositar);
                        break;
                    case 8:
                        System.out.print("Digite o número da conta de origem: ");
                        int numeroContaOrigem = scanner.nextInt();
                        System.out.print("Digite o número da conta de destino: ");
                        int numeroContaDestino = scanner.nextInt();
                        System.out.print("Digite o valor a ser transferido: ");
                        double valorTransferir = scanner.nextDouble();
                        contaController.transferir(numeroContaOrigem, numeroContaDestino, valorTransferir);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine();
            }
        } while (opcao != 0);
    }
}
