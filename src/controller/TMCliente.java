/**
 * Título da classe.
 * Insira aqui uma pequena descrição sobre a mesma.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since May 19, 2019 at 10:37:55 AM
 * @version 0.1
 */
package controller;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.AbstractTableModel;
import model.Cliente;

public class TMCliente extends AbstractTableModel {

    Scanner input = new Scanner(System.in);

    // NOME, GÊNERO, IDADE, ENDEREÇO, TELEFONE, EMAIL, CPF, BONIFICAÇÃO
    private final ArrayList<Cliente> lstClientes = new ArrayList<>();
    private final ArrayList<String> lstColunas = new ArrayList<>();

    public TMCliente() {
        this.lstColunas.add("Nome");
        this.lstColunas.add("Gênero");
        this.lstColunas.add("Idade");
        this.lstColunas.add("Endereço");
        this.lstColunas.add("Telefone");
        this.lstColunas.add("E-mail");
        this.lstColunas.add("CPF");
        this.lstColunas.add("Bonificação");
    }

    public ArrayList<Cliente> getLstClientes() {
        return lstClientes;
    }

    public ArrayList<String> getLstColunas() {
        return lstColunas;
    }

    @Override
    public String getColumnName(int index) {
        return this.getLstColunas().get(index);
    }

    @Override
    public int getRowCount() {
        return this.lstClientes.size();
    }

    @Override
    public int getColumnCount() {
        return this.lstColunas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna == this.lstColunas.indexOf("Nome")) {
            return this.lstClientes.get(linha).getNome();
        } else if (coluna == this.lstColunas.indexOf("Gênero")) {
            return this.lstClientes.get(linha).getSexo();
        } else if (coluna == this.lstColunas.indexOf("Idade")) {
            return this.lstClientes.get(linha).getIdade();
        } else if (coluna == this.lstColunas.indexOf("Endereço")) {
            return this.lstClientes.get(linha).getEndereco();
        } else if (coluna == this.lstColunas.indexOf("Telefone")) {
            return this.lstClientes.get(linha).getTelefone();
        } else if (coluna == this.lstColunas.indexOf("E-mail")) {
            return this.lstClientes.get(linha).getEmail();
        } else if (coluna == this.lstColunas.indexOf("CPF")) {
            return this.lstClientes.get(linha).getCpf();
        } else if (coluna == this.lstColunas.indexOf("Bonificação")) {
            return this.lstClientes.get(linha).getBonificacao();
        }
        System.out.println("Objeto não encontrado");
        return null;
    }

    public void addLinha(Cliente c) {
        this.getLstClientes().add(c);
        this.fireTableDataChanged();
    }

    public void removerLinha(int linha) {
        this.lstClientes.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        this.fireTableDataChanged();
    }

    public Cliente getLinha(int indice) {
        return this.lstClientes.get(indice);
    }
}
