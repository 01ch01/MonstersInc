package model;

/**
 *
 * @author ch
 */
public class Engenheiro extends Funcionario {

    public Engenheiro() {
        super();
        super.taxa = 0.08;
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
