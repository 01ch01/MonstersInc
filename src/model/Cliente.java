package model;

/**
 *
 * @author ch
 */
public class Cliente extends Pessoa implements Bonificacao {

    private String cpf;
    private String senha;

    public Cliente() {
        super();
        this.cpf = "";
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void preencher() {
        super.preencher();
        System.out.print("CPF: ");
        this.cpf = input.nextLine();
    }

    @Override
    public void imprime() {
        super.imprime();
        System.out.println("CPF: " + this.cpf);
    }

    public boolean autenticar(String senha) {
        return senha.equals(this.senha);
    }

    @Override
    public String getCabecalhoCSV() {
        String info = super.getCabecalhoCSV();
        info += "cpf;bonificação";
        return info;
    }

    @Override
    public String getCSVInfo() {
        String info = super.getCSVInfo();
        info += ";" + this.cpf + ";" + this.getBonificacao();
        return info;
    }

    @Override
    public void setCSVInfo(String linhaCsv) {
        super.setCSVInfo(linhaCsv);
        String[] info = linhaCsv.split(";");
        this.cpf = info[6];
        String aux = Double.toString(this.getBonificacao());
        aux = info[7];
    }

    @Override
    public double getBonificacao() {
        return 0.0;
    }

}
