package conta.model;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(int numero, double saldo) {
        super(numero, saldo);
    }

    @Override
    public void sacar(double valor) {
        if (saldoSuficiente(valor)) {
            double saldoAtual = getSaldo();
            double novoSaldo = saldoAtual - valor;
            setSaldo(novoSaldo);
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    @Override
    public void depositar(double valor) {
        double saldoAtual = getSaldo();
        double novoSaldo = saldoAtual + valor;
        setSaldo(novoSaldo);
    }

    @Override
    public String toString() {
        return "Conta Poupança [Número: " + this.getNumero() + ", Saldo: " + this.getSaldo() + "]";
    }
}
