package view;

import controller.TMCliente;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;

/**
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 */
public final class ViewCliente extends javax.swing.JFrame implements View {

    private final TMCliente tmCliente;
    private boolean alteracao;

    /**
     * Creates new form ViewCliente
     */
    public ViewCliente() {
        initComponents();
        this.tmCliente = new TMCliente();
        this.alteracao = false;
        this.resetarCampos(false);
        this.tabela.setModel(tmCliente);
        this.carregar("src/csv/lst_clientes.csv");

        this.tmCliente.fireTableDataChanged();
    }

    @Override
    public int buscar(String cpf) {
        for (int i = 0; i < this.tmCliente.getRowCount(); i++) {
            if (cpf.equals(this.tmCliente.getLstClientes().get(i).getCpf())) {
                return i;
            }
        }
        JOptionPane.showMessageDialog(null, "CLIENTE NÃO ENCONTRADO");
        return -1;
    }

    public boolean existe(Cliente cliente) {
        for (Cliente c : this.tmCliente.getLstClientes()) {
            if (c.getCpf().equals(cliente.getCpf())) {
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
                || (this.rdFem.isSelected() || this.rdMasc.isSelected())
                || this.edtCpf.getText().isEmpty()
                || this.edtSenha.getPassword().length == 0;
    }

    @Override
    public void mostrarTabela() {
        for (Cliente c : this.tmCliente.getLstClientes()) {
            this.tmCliente.addLinha(c);
        }
    }

    public void copiarClasseParaCampos(Cliente c) {
        this.edtNome.setText(c.getNome());
        if (c.getSexo() == 'F' || c.getSexo() == 'f') {
            this.rdFem.setSelected(true);
        } else if (c.getSexo() == 'M' || c.getSexo() == 'm') {
            this.rdMasc.setSelected(true);
        }
        this.edtIdade.setText(c.getIdade() + "");
        this.edtEndereco.setText(c.getEndereco());
        this.edtTelefone.setText(c.getTelefone());
        this.edtEmail.setText(c.getEmail());
        this.edtCpf.setText(c.getCpf());
        this.edtSenha.setText(c.getSenha());
    }

    public void copiarCamposParaClasse(Cliente c) {
        c.setNome(this.edtNome.getText());

        if (this.rdFem.isSelected()) {
            c.setSexo('F');
        } else if (this.rdMasc.isSelected()) {
            c.setSexo('M');
        }

        c.setIdade(Integer.parseInt(this.edtIdade.getText()));
        c.setEndereco(this.edtEndereco.getText());
        c.setTelefone(this.edtTelefone.getText());
        c.setEmail(this.edtEmail.getText());
        c.setCpf(this.edtCpf.getText());
        c.setSenha(Arrays.toString(this.edtSenha.getPassword()));
    }

    @Override
    public void carregar(String caminho) {
        FileReader arquivo;
        try {
            arquivo = new FileReader(caminho);
            Scanner ler = new Scanner(arquivo);
            ler.useDelimiter("\n");

            //pulando linha do cabeçalho
            ler.next();

            while (ler.hasNext()) {
                String linhaCsv = ler.next();
                Cliente c = new Cliente();
                c.setCSVInfo(linhaCsv);
                this.tmCliente.addLinha(c);
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
                Cliente aux = new Cliente();
                String info = aux.getCabecalhoCSV();
                for (Cliente c : this.tmCliente.getLstClientes()) {
                    info += c.getCSVInfo();
                }
                writeOnFile.print(info);
            }
        } catch (IOException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "ERRO. Impossível salvar. \n"
                    + "Provavelmente o arquivo está corrompido. ");
        }
    }

    @Override
    public void salvar() {
        Cliente c = new Cliente();
        this.copiarCamposParaClasse(c);

        if (this.alteracao) {
            this.tmCliente.getLstClientes().set(this.buscar(c.getCpf()), c);
        } else {
            if (this.existe(c)) {
                JOptionPane.showMessageDialog(null, "CPF já existe");
            } else {
                this.tmCliente.addLinha(c);
            }
        }
        this.salvar("src/csv/lst_clientes.csv");
        this.tmCliente.fireTableDataChanged();
        this.resetarCampos(false);
    }

    @Override
    public void resetarCampos(boolean flag) {
        this.edtNome.setEnabled(flag);
        this.rdFem.setEnabled(flag);
        this.rdMasc.setEnabled(flag);
        this.edtIdade.setEnabled(flag);
        this.edtEndereco.setEnabled(flag);
        this.edtTelefone.setEnabled(flag);
        this.edtEmail.setEnabled(flag);
        this.edtCpf.setEnabled(flag);
        this.edtSenha.setEnabled(flag);

        if (!flag) {
            this.edtNome.setText(null);
            this.rdFem.setSelected(flag);
            this.rdMasc.setSelected(flag);
            this.edtIdade.setText(null);
            this.edtEndereco.setText(null);
            this.edtTelefone.setText(null);
            this.edtEmail.setText(null);
            this.edtCpf.setText(null);
            this.edtSenha.setText(null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoes = new javax.swing.ButtonGroup();
        lblNome = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        lblGenero = new javax.swing.JLabel();
        rdFem = new javax.swing.JRadioButton();
        rdMasc = new javax.swing.JRadioButton();
        lblCpf = new javax.swing.JLabel();
        edtCpf = new javax.swing.JTextField();
        edtIdade = new javax.swing.JTextField();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        lblIdade = new javax.swing.JLabel();
        edtSenha = new javax.swing.JPasswordField();
        lblSenha = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblNome.setText("Nome:");

        edtNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        lblGenero.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblGenero.setText("Gênero:");

        grupoBotoes.add(rdFem);
        rdFem.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        rdFem.setText("Feminino");

        grupoBotoes.add(rdMasc);
        rdMasc.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        rdMasc.setText("Masculino");

        lblCpf.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblCpf.setText("CPF:");

        edtCpf.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        edtIdade.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        edtIdade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtIdadeActionPerformed(evt);
            }
        });

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

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INFORMAÇÕES DOS CLIENTES");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabela);

        lblIdade.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblIdade.setText("Idade:");

        edtSenha.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        edtSenha.setToolTipText("Símbolos não são permitidos!");

        lblSenha.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblSenha.setText("Senha:");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(lblCpf)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGenero)
                                    .addComponent(lblNome))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(rdFem)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdMasc))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail)
                                    .addComponent(lblTelefone)
                                    .addComponent(lblEndereco)
                                    .addComponent(lblSenha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(edtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(lblIdade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMenu)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome)
                            .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGenero)
                            .addComponent(rdFem)
                            .addComponent(rdMasc))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCpf)
                            .addComponent(edtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIdade))
                        .addGap(18, 18, 18)
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
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSenha)
                            .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovo)
                            .addComponent(btnEditar)
                            .addComponent(btnCancelar)
                            .addComponent(btnExcluir)
                            .addComponent(btnSalvar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                        .addContainerGap())))
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
        String termo = JOptionPane.showInputDialog("Insira aqui o CPF do Cliente:");

        Cliente c = new Cliente();
        c = this.tmCliente.getLstClientes().get(this.buscar(termo));

        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente"
                + " editar os dados de " + c.getNome() + "?");

        if (confirm == JOptionPane.YES_OPTION) {
            this.alteracao = true;
            this.copiarClasseParaCampos(c);
            this.resetarCampos(true);

        } else if (confirm == JOptionPane.NO_OPTION) {
            this.btnCancelarActionPerformed(evt);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.resetarCampos(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja "
                + "realmente salvar?");

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (this.verificarCamposVazios()) {
                this.salvar();
            } else {
                JOptionPane.showMessageDialog(null, "Favor preencher todos os "
                        + "campos");
            }

        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        String termo = JOptionPane.showInputDialog("Insira aqui o CPF do Cliente:");

        Cliente c = new Cliente();
        c = this.tmCliente.getLstClientes().get(this.buscar(termo));

        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que "
                + "deseja excluir " + c.getNome() + "?");

        if (confirmacao == JOptionPane.YES_OPTION) {
            this.tmCliente.getLstClientes().remove(c);
            this.salvar("src/csv/lst_clientes.csv");
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
//            this.showTable();
            this.tmCliente.fireTableDataChanged();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

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
    private javax.swing.JTextField edtCpf;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtEndereco;
    private javax.swing.JTextField edtIdade;
    private javax.swing.JTextField edtNome;
    private javax.swing.JPasswordField edtSenha;
    private javax.swing.JTextField edtTelefone;
    private javax.swing.ButtonGroup grupoBotoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblIdade;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JRadioButton rdFem;
    private javax.swing.JRadioButton rdMasc;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
