package conta.controller;

import conta.model.Conta;
import conta.repository.ContaRepository;
import java.util.List;

public class ContaController {
    private ContaRepository contaRepository;

    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void sacar(int numero, double valor) {
        Conta conta = contaRepository.procurarPorNumero(numero);
        if (conta != null) {
            conta.sacar(valor);
            contaRepository.atualizar(conta);
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public void depositar(int numero, double valor) {
        Conta conta = contaRepository.procurarPorNumero(numero);
        if (conta != null) {
            conta.depositar(valor);
            contaRepository.atualizar(conta);
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public void transferir(int numeroOrigem, int numeroDestino, double valor) {
        Conta contaOrigem = contaRepository.procurarPorNumero(numeroOrigem);
        Conta contaDestino = contaRepository.procurarPorNumero(numeroDestino);

        if (contaOrigem != null && contaDestino != null) {
            if (contaOrigem.getSaldo() >= valor) {
                contaOrigem.sacar(valor);
                contaDestino.depositar(valor);
                contaRepository.atualizar(contaOrigem);
                contaRepository.atualizar(contaDestino);
                System.out.println("Transferência realizada com sucesso.");
            } else {
                System.out.println("Saldo insuficiente na conta de origem.");
            }
        } else {
            System.out.println("Conta de origem ou conta de destino não encontrada.");
        }
    }

    public Conta procurarPorNumero(int numero) throws Exception {
        try {
            return contaRepository.procurarPorNumero(numero);
        } catch (Exception e) {
            throw new Exception("Erro ao procurar a conta: " + e.getMessage());
        }
    }


    public List<Conta> listarTodas() throws Exception {
        try {
            return contaRepository.listarTodas();
        } catch (Exception e) {
            throw new Exception("Erro ao listar as contas: " + e.getMessage());
        }
    }


    public void cadastrar(Conta conta) throws Exception {
        try {
            contaRepository.adicionar(conta);
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar a conta: " + e.getMessage());
        }
    }

    public void atualizar(Conta conta) throws Exception {
        try {
            contaRepository.atualizar(conta);
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar a conta: " + e.getMessage());
        }
    }

    public void deletar(int numero) {
        try {
            contaRepository.remover(numero);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a conta: " + e.getMessage());
        }
    }

}