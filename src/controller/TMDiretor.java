/**
 * Título da classe.
 * Insira aqui uma pequena descrição sobre a mesma.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since May 19, 2019 at 10:39:30 AM
 * @version 0.1
 */
package controller;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.AbstractTableModel;
import model.Diretor;

public class TMDiretor extends AbstractTableModel {

    Scanner input = new Scanner(System.in);

    // NOME, GÊNERO, IDADE, ENDEREÇO, TELEFONE, EMAIL, HORA EXTRA, SALARIO, HORAS DE TRABALHO, ID
    private final ArrayList<Diretor> lstDiretores;
    private final ArrayList<String> lstColunas;

    public TMDiretor() {
        this.lstDiretores = new ArrayList<>();
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
        this.lstColunas.add("Bonificação");
    }

    public ArrayList<Diretor> getLstDiretores() {
        return lstDiretores;
    }

    public ArrayList<String> getLstColunas() {
        return lstColunas;
    }

    @Override
    public int getRowCount() {
        return this.getLstDiretores().size();
    }

    @Override
    public int getColumnCount() {
        return this.getLstColunas().size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna == this.getLstColunas().indexOf("Nome")) {
            return this.getLstDiretores().get(linha).getNome();
        } else if (coluna == this.getLstColunas().indexOf("Gênero")) {
            return this.getLstDiretores().get(linha).getSexo();
        } else if (coluna == this.getLstColunas().indexOf("Idade")) {
            return this.getLstDiretores().get(linha).getIdade();
        } else if (coluna == this.getLstColunas().indexOf("Endereço")) {
            return this.getLstDiretores().get(linha).getEndereco();
        } else if (coluna == this.getLstColunas().indexOf("Telefone")) {
            return this.getLstDiretores().get(linha).getTelefone();
        } else if (coluna == this.getLstColunas().indexOf("E-mail")) {
            return this.getLstDiretores().get(linha).getEmail();
        } else if (coluna == this.getLstColunas().indexOf("Hora Extra")) {
            return this.getLstDiretores().get(linha).getHoraExtra();
        } else if (coluna == this.getLstColunas().indexOf("Salário")) {
            return this.getLstDiretores().get(linha).getSalario();
        } else if (coluna == this.getLstColunas().indexOf("Horas Trabalhadas")) {
            return this.getLstDiretores().get(linha).getHorasTrabalho();
        } else if (coluna == this.getLstColunas().indexOf("ID")) {
            return this.getLstDiretores().get(linha).getId();
        } else if (coluna == this.lstColunas.indexOf("Bonificação")) {
            return this.getLstDiretores().get(linha).getBonificacao();
        }
        System.out.println("Objeto não encontrado");
        return null;
    }

    public void addLinha(Diretor d) {
        this.getLstDiretores().add(d);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.lstDiretores.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        this.fireTableDataChanged();
    }

    public Diretor getLinha(int indice) {
        return this.lstDiretores.get(indice);
    }

    @Override
    public String getColumnName(int index) {
        return this.getLstColunas().get(index);
    }
}
