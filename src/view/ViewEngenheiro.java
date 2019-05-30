package view;

import controller.TMEngenheiro;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Engenheiro;

/**
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 */
public class ViewEngenheiro extends javax.swing.JFrame implements View {

    private final TMEngenheiro tmEngenheiro;
    private boolean alteracao = false;

    /**
     * Creates new form ViewEngenheiro
     */
    public ViewEngenheiro() {
        initComponents();
        this.tmEngenheiro = new TMEngenheiro();
        this.alteracao = false;
        this.resetarCampos(false);
        this.tabela.setModel(tmEngenheiro);
        this.carregar("src/csv/lst_engenheiros.csv");

        this.tmEngenheiro.fireTableDataChanged();
    }

    @Override
    public final void resetarCampos(boolean flag) {
        this.edtNome.setEnabled(flag);
        this.rdFem.setEnabled(flag);
        this.rdMasc.setEnabled(flag);
        this.edtIdade.setEnabled(flag);
        this.edtEndereco.setEnabled(flag);
        this.edtTelefone.setEnabled(flag);
        this.edtEmail.setEnabled(flag);
        this.edtHoraExtra.setEnabled(flag);
        this.edtSalario.setEnabled(flag);
        this.edtHoras.setEnabled(flag);
        this.edtId.setEnabled(flag);

        if (!flag) {
            this.edtNome.setText(null);
            this.rdFem.setSelected(flag);
            this.rdMasc.setSelected(flag);
            this.edtIdade.setText(null);
            this.edtEndereco.setText(null);
            this.edtTelefone.setText(null);
            this.edtEmail.setText(null);
            this.edtHoraExtra.setText(null);
            this.edtSalario.setText(null);
            this.edtHoras.setText(null);
            this.edtId.setText(null);
        }
    }

    @Override
    public int buscar(String id) {
        for (int i = 0; i < this.tmEngenheiro.getRowCount(); i++) {
            if (id.equals(this.tmEngenheiro.getLstEngenheiros().get(i).getId())) {
                return i;
            }
        }
        JOptionPane.showMessageDialog(null, "ENGENHEIRO NÃO ENCONTRADO");
        return -1;
    }

    public boolean existe(Engenheiro engenheiro) {
        for (Engenheiro e : this.tmEngenheiro.getLstEngenheiros()) {
            if (e.equals(engenheiro)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verificarCamposVazios() {
        return this.edtNome.getText().isEmpty()
                || this.edtIdade.getText().isEmpty()
                || this.edtEndereco.getText().isEmpty()
                || this.edtTelefone.getText().isEmpty()
                || this.edtEmail.getText().isEmpty()
                || (this.rdFem.isSelected()
                || this.rdMasc.isSelected())
                || this.edtHoraExtra.getText().isEmpty()
                || this.edtHoras.getText().isEmpty()
                || this.edtId.getText().isEmpty()
                || this.edtSalario.getText().isEmpty();
    }

    @Override
    public void mostrarTabela() {
        for (Engenheiro c : this.tmEngenheiro.getLstEngenheiros()) {
            this.tmEngenheiro.addLinha(c);
        }
    }

    public void copiarClasseParaCampos(Engenheiro e) {
        this.edtNome.setText(e.getNome());
        if (e.getSexo() == 'F' || e.getSexo() == 'f') {
            this.rdFem.setSelected(true);
        } else if (e.getSexo() == 'M' || e.getSexo() == 'm') {
            this.rdMasc.setSelected(true);
        }
        this.edtIdade.setText(e.getIdade() + "");
        this.edtEndereco.setText(e.getEndereco());
        this.edtTelefone.setText(e.getTelefone());
        this.edtEmail.setText(e.getEmail());
        this.edtHoraExtra.setText(e.getHoraExtra() + "");
        this.edtSalario.setText(e.getSalario() + "");
        this.edtHoras.setText(e.getHorasTrabalho() + "");
        this.edtId.setText(e.getId());

    }

    public void copiarCamposParaClasse(Engenheiro e) {
        e.setNome(this.edtNome.getText());

        if (this.rdFem.isSelected()) {
            e.setSexo('F');
        } else if (this.rdMasc.isSelected()) {
            e.setSexo('M');
        }

        e.setIdade(Integer.parseInt(this.edtIdade.getText()));
        e.setEndereco(this.edtEndereco.getText());
        e.setTelefone(this.edtTelefone.getText());
        e.setEmail(this.edtEmail.getText());
        e.setHoraExtra(Double.parseDouble(this.edtHoraExtra.getText()));
        e.setSalario(Double.parseDouble(this.edtSalario.getText()));
        e.setHorasTrabalho(Double.parseDouble(this.edtHoraExtra.getText()));
        e.setId(this.edtId.getText());
    }

    @Override
    public final void carregar(String caminho) {
        FileReader arquivo;
        try {
            arquivo = new FileReader(caminho);
            Scanner ler = new Scanner(arquivo);
            ler.useDelimiter("\n");

            //pulando linha do cabeçalho
            ler.next();

            while (ler.hasNext()) {
                String linhaCsv = ler.next();
                Engenheiro c = new Engenheiro();
                c.setCSVInfo(linhaCsv);
                this.tmEngenheiro.addLinha(c);
            }

        } catch (FileNotFoundException e) {
            Logger.getLogger(ViewMain.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "ERRO. O arquivo não foi "
                    + "carregado com sucesso.");
        }
    }

    @Override
    public void salvar(String caminho) {
        try {
            FileWriter arquivo = new FileWriter(caminho);
            try (PrintWriter writeOnFile = new PrintWriter(arquivo)) {
                Engenheiro aux = new Engenheiro();
                String info = aux.getCabecalhoCSV();
                for (Engenheiro c : this.tmEngenheiro.getLstEngenheiros()) {
                    info += c.getCSVInfo();
                }
                writeOnFile.print(info);
            }
        } catch (IOException e) {
            Logger.getLogger(Engenheiro.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "ERRO. Impossível salvar. \n"
                    + "Provavelmente o arquivo está corrompido. ");
        }
    }

    @Override
    public void salvar() {
        Engenheiro e = new Engenheiro();
        this.copiarCamposParaClasse(e);

        if (this.alteracao) {
            this.tmEngenheiro.getLstEngenheiros().set(this.buscar(e.getId()), e);
        } else {
            if (this.existe(e)) {
                JOptionPane.showMessageDialog(null, "ID já existe");
            } else {
                this.tmEngenheiro.addLinha(e);
            }
        }
        this.salvar("src/csv/lst_engenheiros.csv");
        this.tmEngenheiro.fireTableDataChanged();
        this.resetarCampos(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoes = new javax.swing.ButtonGroup();
        lblNome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        rdMasc = new javax.swing.JRadioButton();
        rdFem = new javax.swing.JRadioButton();
        lblGenero = new javax.swing.JLabel();
        edtIdade = new javax.swing.JTextField();
        lblIdade = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        edtEndereco = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        edtTelefone = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        edtEmail = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        lblHoraExtra = new javax.swing.JLabel();
        edtHoraExtra = new javax.swing.JTextField();
        lblHoras = new javax.swing.JLabel();
        edtHoras = new javax.swing.JTextField();
        lblSalario = new javax.swing.JLabel();
        edtSalario = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        edtId = new javax.swing.JTextField();
        btnMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblNome.setText("Nome:");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INFORMAÇÕES DOS ENGENHEIROS");

        edtNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabela);

        grupoBotoes.add(rdMasc);
        rdMasc.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        rdMasc.setText("Masculino");

        grupoBotoes.add(rdFem);
        rdFem.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        rdFem.setText("Feminino");

        lblGenero.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblGenero.setText("Gênero:");

        edtIdade.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        edtIdade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtIdadeActionPerformed(evt);
            }
        });

        lblIdade.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblIdade.setText("Idade:");

        lblEndereco.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblEndereco.setText("Endereço:");

        edtEndereco.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        lblTelefone.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblTelefone.setText("Telefone:");

        edtTelefone.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        lblEmail.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblEmail.setText("E-mail:");

        edtEmail.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        btnNovo.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgNew.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgEdit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgCancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgDelete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgSave.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        lblHoraExtra.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblHoraExtra.setText("Hora Extra:");

        edtHoraExtra.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        lblHoras.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblHoras.setText("Regime de Horas:");

        edtHoras.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        lblSalario.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblSalario.setText("Salário:");

        edtSalario.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        lblId.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblId.setText("ID:");

        edtId.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgBack.png"))); // NOI18N
        btnMenu.setToolTipText("Retorna ao Menu Principal");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIdade)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdFem)
                                .addGap(18, 18, 18)
                                .addComponent(rdMasc))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHoraExtra)
                            .addComponent(lblSalario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtHoraExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtId, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHoras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnMenu))
                .addContainerGap(723, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblGenero)
                                        .addComponent(lblNome))
                                    .addGap(26, 26, 26)
                                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEmail)
                                        .addComponent(lblTelefone)
                                        .addComponent(lblEndereco))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(edtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(edtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(edtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(48, 48, 48)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                            .addGap(18, 18, 18))
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdMasc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdFem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdade)
                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoraExtra)
                    .addComponent(edtHoraExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoras)
                    .addComponent(edtHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtSalario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblSalario)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId)
                            .addComponent(edtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSalvar))
                .addGap(183, 183, 183))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNome)
                                .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(lblGenero)
                            .addGap(64, 64, 64)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEndereco)
                                .addComponent(edtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTelefone)
                                .addComponent(edtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEmail)
                                .addComponent(edtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtIdadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtIdadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtIdadeActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.resetarCampos(true);
        this.alteracao = false;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String termo = JOptionPane.showInputDialog("Insira aqui o ID:");

        Engenheiro e = new Engenheiro();
        e = this.tmEngenheiro.getLstEngenheiros().get(this.buscar(termo));

        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente"
                + " editar os dados de " + e.getNome() + "?");

        if (confirm == JOptionPane.YES_OPTION) {
            this.alteracao = true;
            this.copiarClasseParaCampos(e);
            this.resetarCampos(true);

        } else if (confirm == JOptionPane.NO_OPTION) {
            this.btnCancelarActionPerformed(evt);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.resetarCampos(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        String termo = JOptionPane.showInputDialog("Insira aqui o ID:");

        Engenheiro e = new Engenheiro();
        e = this.tmEngenheiro.getLstEngenheiros().get(this.buscar(termo));

        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que "
                + "deseja excluir " + e.getNome() + "?");

        if (confirmacao == JOptionPane.YES_OPTION) {
            this.tmEngenheiro.getLstEngenheiros().remove(e);
            this.salvar("src/csv/lst_engenheiros.csv");
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            //            this.showTable();
            this.tmEngenheiro.fireTableDataChanged();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja "
                + "realmente salvar?");

        if (confirmacao == JOptionPane.YES_OPTION) {
            this.salvar();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        ViewMain widget = new ViewMain();
        widget.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtEndereco;
    private javax.swing.JTextField edtHoraExtra;
    private javax.swing.JTextField edtHoras;
    private javax.swing.JTextField edtId;
    private javax.swing.JTextField edtIdade;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtSalario;
    private javax.swing.JTextField edtTelefone;
    private javax.swing.ButtonGroup grupoBotoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblHoraExtra;
    private javax.swing.JLabel lblHoras;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdade;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JRadioButton rdFem;
    private javax.swing.JRadioButton rdMasc;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
