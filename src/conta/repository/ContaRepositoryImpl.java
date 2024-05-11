package conta.repository;

import java.util.ArrayList;
import java.util.List;

import conta.model.Conta;

public class ContaRepositoryImpl implements ContaRepository {
    private List<Conta> contas;

    public ContaRepositoryImpl() {
        this.contas = new ArrayList<>();
    }

    @Override
    public void cadastrar(Conta conta) {
        contas.add(conta);
    }

    @Override
    public Conta procurarPorNumero(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }

    @Override
    public List<Conta> listarTodas() {
        return contas;
    }

    @Override
    public void atualizar(Conta conta) {
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumero() == conta.getNumero()) {
                contas.set(i, conta);
                return;
            }
        }
    }

    @Override
    public void remover(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                contas.remove(conta);
                return;
            }
        }
    }

    @Override
    public void deletar(int numero) {
        contas.removeIf(conta -> conta.getNumero() == numero);
    }


    @Override
    public void adicionar(Conta conta) {
        cadastrar(conta);
    }
}
