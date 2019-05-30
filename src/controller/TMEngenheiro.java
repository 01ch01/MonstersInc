/**
 * Título da classe.
 * Insira aqui uma pequena descrição sobre a mesma.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since May 19, 2019 at 10:38:05 AM
 * @version 0.1
 */
package controller;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.AbstractTableModel;
import model.Engenheiro;

public class TMEngenheiro extends AbstractTableModel {

    Scanner input = new Scanner(System.in);

    // NOME, GÊNERO, IDADE, ENDEREÇO, TELEFONE, EMAIL, HORA EXTRA, SALARIO, HORAS DE TRABALHO, ID
    private final ArrayList<Engenheiro> lstEngenheiros;
    private final ArrayList<String> lstColunas;

    public TMEngenheiro() {
        this.lstEngenheiros = new ArrayList<>();
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

    public ArrayList<Engenheiro> getLstEngenheiros() {
        return lstEngenheiros;
    }

    public ArrayList<String> getLstColunas() {
        return lstColunas;
    }

    @Override
    public int getRowCount() {
        return this.lstEngenheiros.size();
    }

    @Override
    public int getColumnCount() {
        return this.lstColunas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna == this.getLstColunas().indexOf("Nome")) {
            return this.getLstEngenheiros().get(linha).getNome();
        } else if (coluna == this.getLstColunas().indexOf("Gênero")) {
            return this.getLstEngenheiros().get(linha).getSexo();
        } else if (coluna == this.getLstColunas().indexOf("Idade")) {
            return this.getLstEngenheiros().get(linha).getIdade();
        } else if (coluna == this.getLstColunas().indexOf("Endereço")) {
            return this.getLstEngenheiros().get(linha).getEndereco();
        } else if (coluna == this.getLstColunas().indexOf("Telefone")) {
            return this.getLstEngenheiros().get(linha).getTelefone();
        } else if (coluna == this.getLstColunas().indexOf("E-mail")) {
            return this.getLstEngenheiros().get(linha).getEmail();
        } else if (coluna == this.getLstColunas().indexOf("Hora Extra")) {
            return this.getLstEngenheiros().get(linha).getHoraExtra();
        } else if (coluna == this.getLstColunas().indexOf("Salário")) {
            return this.getLstEngenheiros().get(linha).getSalario();
        } else if (coluna == this.getLstColunas().indexOf("Horas Trabalhadas")) {
            return this.getLstEngenheiros().get(linha).getHorasTrabalho();
        } else if (coluna == this.getLstColunas().indexOf("ID")) {
            return this.getLstEngenheiros().get(linha).getId();
        } else if (coluna == this.getLstColunas().indexOf("Bonificação")) {
            return this.getLstEngenheiros().get(linha).getBonificacao();
        }
        System.out.println("Objeto não encontrado");
        return null;
    }

    public void addLinha(Engenheiro e) {
        this.getLstEngenheiros().add(e);
        this.fireTableDataChanged();
    }

    public void removeLinha(int linha) {
        this.lstEngenheiros.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        this.fireTableDataChanged();
    }

    public Engenheiro getLinha(int indice) {
        return this.lstEngenheiros.get(indice);
    }

    @Override
    public String getColumnName(int index) {
        return this.getLstColunas().get(index);
    }
}
