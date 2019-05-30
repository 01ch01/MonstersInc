/**
 * Título da classe.
 * Insira aqui uma pequena descrição sobre a mesma.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since May 18, 2019 at 5:20:46 PM
 * @version 0.1
 */
package model;

abstract public class FuncionarioAutenticavel extends Funcionario {

    private String senha;

    public FuncionarioAutenticavel() {
        super();
        this.senha = "";
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean autenticar(String senha) {
        return senha.equals(this.senha);
    }

    @Override
    public void preencher() {
        super.preencher();
        System.out.print("Senha: ");
        this.senha = input.nextLine();
    }

    @Override
    public void imprime() {
        super.imprime();
    }

}
