package conta.repository;

import java.util.List;
import conta.model.Conta;

public interface ContaRepository {
    void cadastrar(Conta conta);

    Conta procurarPorNumero(int numero);
    List<Conta> listarTodas();

    void deletar(int numero);

    void adicionar(Conta conta);
    void atualizar(Conta conta);
    void remover(int numero);
}
