/**
 * Título da classe.
 * Insira aqui uma pequena descrição sobre a mesma.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since May 19, 2019 at 10:39:58 AM
 * @version 0.1
 */
package controller;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.AbstractTableModel;
import model.Gerente;

public class TMGerente extends AbstractTableModel {

    Scanner input = new Scanner(System.in);

    // NOME, GÊNERO, IDADE, ENDEREÇO, TELEFONE, EMAIL, HORA EXTRA, SALARIO, HORAS DE TRABALHO, ID, SUBORDINADO, SENHA
    private final ArrayList<Gerente> lstGerentes;
    private final ArrayList<String> lstColunas;

    public TMGerente() {
        this.lstGerentes = new ArrayList<>();
        this.lstColunas = new ArrayList<>();
        this.lstColunas.add("Nome");
        this.lstColunas.add("Gênero");
        this.lstColunas.add("Idade");
        this.lstColunas.add("Endereço");
        this.lstColunas.add("Telefone");
        this.lstColunas.add("E-mail");
        this.lstColunas.add("Hora Extra");
        this.lstColunas.add("Salário");
        this.lstColunas.add("Horas Trabalhadas");
        this.lstColunas.add("ID");
        this.lstColunas.add("Subordinados");
        this.lstColunas.add("Bonificação");
    }

    public ArrayList<Gerente> getLstGerentes() {
        return lstGerentes;
    }

    public ArrayList<String> getLstColunas() {
        return lstColunas;
    }

    @Override
    public int getRowCount() {
        return this.getLstGerentes().size();
    }

    @Override
    public int getColumnCount() {
        return this.getLstColunas().size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna == this.getLstColunas().indexOf("Nome")) {
            return this.getLstGerentes().get(linha).getNome();
        } else if (coluna == this.getLstColunas().indexOf("Gênero")) {
            return this.getLstGerentes().get(linha).getSexo();
        } else if (coluna == this.getLstColunas().indexOf("Idade")) {
            return this.getLstGerentes().get(linha).getIdade();
        } else if (coluna == this.getLstColunas().indexOf("Endereço")) {
            return this.getLstGerentes().get(linha).getEndereco();
        } else if (coluna == this.getLstColunas().indexOf("Telefone")) {
            return this.getLstGerentes().get(linha).getTelefone();
        } else if (coluna == this.getLstColunas().indexOf("E-mail")) {
            return this.getLstGerentes().get(linha).getEmail();
        } else if (coluna == this.getLstColunas().indexOf("Hora Extra")) {
            return this.getLstGerentes().get(linha).getHoraExtra();
        } else if (coluna == this.getLstColunas().indexOf("Salário")) {
            return this.getLstGerentes().get(linha).getSalario();
        } else if (coluna == this.getLstColunas().indexOf("Horas Trabalhadas")) {
            return this.getLstGerentes().get(linha).getHorasTrabalho();
        } else if (coluna == this.getLstColunas().indexOf("ID")) {
            return this.getLstGerentes().get(linha).getId();
        } else if (coluna == this.getLstColunas().indexOf("Subordinados")) {
            return this.getLstGerentes().get(linha).getQtdSubordinados();
        } else if (coluna == this.lstColunas.indexOf("Bonificação")) {
            return this.lstGerentes.get(linha).getBonificacao();
        }
        System.out.println("Objeto não encontrado");
        return null;
    }

    public void addLinha(Gerente g) {
        this.getLstGerentes().add(g);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.lstGerentes.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        this.fireTableDataChanged();
    }

    public Gerente getLinha(int indice) {
        return this.lstGerentes.get(indice);
    }

    @Override
    public String getColumnName(int index) {
        return this.getLstColunas().get(index);
    }
}
