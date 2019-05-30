package model;

/**
 *
 * @author ch
 */
public class Secretario extends Funcionario {

    public Secretario() {
        super();
        super.taxa = 0.05;
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
