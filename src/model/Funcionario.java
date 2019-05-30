package model;

/**
 *
 * @author ch
 */
abstract public class Funcionario extends Pessoa implements Bonificacao{

    protected double horaExtra;
    protected double taxa;
    protected double salario;
    protected double horasTrabalho;
    protected String id;

    public Funcionario() {
        super();
        this.horasTrabalho = 0;
        this.salario = 0;
        this.horaExtra = 0;
        this.taxa = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getHoraExtra() {
        return horaExtra;
    }

    public void setHoraExtra(double horaExtra) {
        this.horaExtra = horaExtra;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getHorasTrabalho() {
        return horasTrabalho;
    }

    public void setHorasTrabalho(double horasTrabalho) {
        this.horasTrabalho = horasTrabalho;
    }

    @Override
    public void imprime() {
        super.imprime();
        System.out.println("ID: " + this.id);
        System.out.println("Salário: R$ " + this.salario);
        System.out.println("Horas Trabalhadas: " + this.horasTrabalho + " h");
    }

    @Override
    public void preencher() {
        super.preencher();
        System.out.print("ID: ");
        this.id = input.nextLine();
        System.out.print("Salário: ");
        this.salario = input.nextFloat();
        input.nextLine();
        System.out.print("Horas trabalhadas: ");
        this.horasTrabalho = input.nextFloat();
        input.nextLine();
        this.horaExtra = this.horasTrabalho - 44;
    }

    @Override
    public String getCabecalhoCSV() {
        String info = super.getCabecalhoCSV();
        info += "horas extras;salário;horas trabalhadas;id;bonificacao";
        return info;
    }

    @Override
    public String getCSVInfo() {
        String info = super.getCSVInfo();
        info += ";" + this.horaExtra + ";" + this.salario + ";"
                + this.horasTrabalho + ";" + this.id + ";" + this.getBonificacao();
        return info;
    }

    @Override
    public void setCSVInfo(String linhaCsv) {
        super.setCSVInfo(linhaCsv);
        String[] info = linhaCsv.split(";");
        this.horaExtra = Double.parseDouble(info[6]);
        this.salario = Double.parseDouble(info[7]);
        this.horasTrabalho = Double.parseDouble(info[8]);
        this.id = info[9];
        String aux = Double.toString(this.getBonificacao());
        aux = info[10];
    }

    @Override
    public abstract double getBonificacao();
    
}
