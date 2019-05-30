package model;

/**
 *
 * @author ch
 */
public class Gerente extends FuncionarioAutenticavel {

    private int qtdSubordinados;

    public Gerente() {
        super();
        this.qtdSubordinados = 0;
        this.taxa = 0.1;
    }

    public int getQtdSubordinados() {
        return qtdSubordinados;
    }

    public void setQtdSubordinados(int qtdSubordinados) {
        this.qtdSubordinados = qtdSubordinados;
    }

    @Override
    public void imprime() {
        super.imprime();
        System.out.println("Subordinados: " + this.qtdSubordinados);
    }

    @Override
    public void preencher() {
        super.preencher();
        System.out.print("Subordinados: ");
        this.qtdSubordinados = input.nextInt();
        input.nextLine();
    }

    @Override
    public String getCabecalhoCSV() {
        String info = super.getCabecalhoCSV();
        info += ";subordinados";
        return info;
    }

    @Override
    public String getCSVInfo() {
        String info = super.getCSVInfo();
        info += ";" + this.qtdSubordinados + ";" + this.getSenha();
        return info;
    }

    @Override
    public void setCSVInfo(String linhaCsv) {
        super.setCSVInfo(linhaCsv);
        String[] info = linhaCsv.split(";");
        this.qtdSubordinados = Integer.parseInt(info[11]);
    }

    @Override
    public double getBonificacao() {
        double horasTrabalhadas = super.getSalario() / this.getHorasTrabalho();
        double result = horasTrabalhadas * this.taxa;
        result += horasTrabalhadas;
        result *= this.getHoraExtra();
        return result - this.salario;
    }

}
