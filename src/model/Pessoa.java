package model;

import java.util.Scanner;

/**
 *
 * @author ch
 */
public abstract class Pessoa {

    Scanner input = new Scanner(System.in);

    private String nome;
    private char sexo;
    private int idade;
    private String endereco;
    private String telefone;
    private String email;

    public Pessoa() {
        this.nome = "";
        this.sexo = 'F';
        this.idade = 0;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void imprime() {
        System.out.println("\n-----------------------");
        System.out.println("Nome: " + this.nome);
        System.out.println("Sexo: " + this.sexo);
        System.out.println("Idade: " + this.idade + " anos");
    }

    public void preencher() {
        System.out.println("\n-----------------------");
        System.out.print("\nNome: ");
        this.nome = input.nextLine();
        System.out.print("Sexo: ");
        this.sexo = input.nextLine().charAt(0);
        System.out.print("Idade: ");
        this.idade = input.nextInt();
        input.nextLine();
    }

    public String getCabecalhoCSV() {
        String info = "nome;genero;idade;endereco;telefone;email;";
        return info;
    }

    public String getCSVInfo() {
        String info = "\n" + this.nome + ";" + this.sexo + ";" + this.idade
                + ";" + this.endereco + ";" + this.telefone + ";" + this.email;
        return info;
    }

    /**
     * Preenche a linha 'csvRow' com todas as informações sobre o objeto
     * instanciado, para que possa ser inserido em um arquivo CSV. Os atributos
     * são separados (delimitados) por ponto-e-vírgula (;).
     *
     * @param linhaCsv : a linha em que as informações serão armazenadas.
     */
    public void setCSVInfo(String linhaCsv) {
        String[] info = linhaCsv.split(";");
        // nome, genero, idade, endereço, telefone, email
        this.nome = info[0];
        this.sexo = info[1].charAt(0);
        this.idade = Integer.parseInt(info[2]);
        this.endereco = info[3];
        this.telefone = info[4];
        this.email = info[5];
    }
}
