package conta.model;

public abstract class Conta {
    private int numero;
    private double saldo;

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

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

    public void depositar(double valor) {
        double saldoAtual = getSaldo();
        double novoSaldo = saldoAtual + valor;
        setSaldo(novoSaldo);
    }

    protected boolean saldoSuficiente(double valor) {
        return saldo >= valor;
    }
}
