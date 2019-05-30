package model;

/**
 *
 * @author ch
 */
public class Diretor extends FuncionarioAutenticavel {

    public Diretor() {
        super();
        super.taxa = 0.15;
    }

    @Override
    public String getCabecalhoCSV() {
        String info = super.getCabecalhoCSV();
        info += ";senha";
        return info;
    }

    @Override
    public String getCSVInfo() {
        String info = super.getCSVInfo();
        info += ";" + this.getSenha();
        return info;
    }

    @Override
    public void setCSVInfo(String linhaCsv) {
        super.setCSVInfo(linhaCsv);
        String[] info = linhaCsv.split(";");
        this.setSenha(info[11]);
    }

    @Override
    public double getBonificacao() {
        double horasTrabalhadas = this.salario / this.horasTrabalho;
        double result = horasTrabalhadas * this.taxa;
        result += horasTrabalhadas;
        result *= this.horaExtra;
        return result - this.salario;
    }

}
