package view;

import controller.TMGerente;
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
import model.Gerente;

/**
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 */
public class ViewGerente extends javax.swing.JFrame implements View {

    private final TMGerente tmGerente;
    private boolean alteracao = false;

    /**
     * Creates new form ViewEngenheiro
     */
    public ViewGerente() {
        initComponents();
        this.tmGerente = new TMGerente();
        this.alteracao = false;
        this.resetarCampos(false);
        this.tabela.setModel(tmGerente);
        this.carregar("src/csv/lst_gerentes.csv");

        this.tmGerente.fireTableDataChanged();
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
        this.edtSenha.setEnabled(flag);
        this.edtSubordinado.setEnabled(flag);

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
            this.edtSenha.setText(null);
            this.edtSubordinado.setText(null);
        }
    }

    @Override
    public int buscar(String id) {
        for (int i = 0; i < this.tmGerente.getRowCount(); i++) {
            if (id.equals(this.tmGerente.getLstGerentes().get(i).getId())) {
                return i;
            }
        }
        JOptionPane.showMessageDialog(null, "DIRETOR NÃO ENCONTRADO");
        return -1;
    }

    public boolean existe(Gerente gerente) {
        for (Gerente g : this.tmGerente.getLstGerentes()) {
            if (g.equals(gerente)) {
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
                || this.edtSalario.getText().isEmpty()
                || this.edtSenha.getPassword().length == 0;
    }

    @Override
    public void mostrarTabela() {
        for (Gerente g : this.tmGerente.getLstGerentes()) {
            this.tmGerente.addLinha(g);
        }
    }

    public void copiarClasseParaCampos(Gerente g) {
        this.edtNome.setText(g.getNome());
        if (g.getSexo() == 'F' || g.getSexo() == 'f') {
            this.rdFem.setSelected(true);
        } else if (g.getSexo() == 'M' || g.getSexo() == 'm') {
            this.rdMasc.setSelected(true);
        }
        this.edtIdade.setText(g.getIdade() + "");
        this.edtEndereco.setText(g.getEndereco());
        this.edtTelefone.setText(g.getTelefone());
        this.edtEmail.setText(g.getEmail());
        this.edtHoraExtra.setText(g.getHoraExtra() + "");
        this.edtSalario.setText(g.getSalario() + "");
        this.edtHoras.setText(g.getHorasTrabalho() + "");
        this.edtId.setText(g.getId());
        this.edtSenha.setText(g.getSenha());
        this.edtSubordinado.setText(g.getQtdSubordinados() + "");
    }

    public void copiarCamposParaClasse(Gerente g) {
        g.setNome(this.edtNome.getText());

        if (this.rdFem.isSelected()) {
            g.setSexo('F');
        } else if (this.rdMasc.isSelected()) {
            g.setSexo('M');
        }
        g.setIdade(Integer.parseInt(this.edtIdade.getText()));
        g.setEndereco(this.edtEndereco.getText());
        g.setTelefone(this.edtTelefone.getText());
        g.setEmail(this.edtEmail.getText());
        g.setHoraExtra(Double.parseDouble(this.edtHoraExtra.getText()));
        g.setSalario(Double.parseDouble(this.edtSalario.getText()));
        g.setHorasTrabalho(Double.parseDouble(this.edtHoraExtra.getText()));
        g.setId(this.edtId.getText());
        g.setSenha(Arrays.toString(this.edtSenha.getPassword()));
        g.setQtdSubordinados(Integer.parseInt(this.edtSubordinado.getText()));
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
                Gerente g = new Gerente();
                g.setCSVInfo(linhaCsv);
                this.tmGerente.addLinha(g);
            }

        } catch (FileNotFoundException e) {
            Logger.getLogger(ViewGerente.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "ERRO. O arquivo não foi "
                    + "carregado com sucesso.");
        }
    }

    @Override
    public void salvar(String caminho) {
        try {
            FileWriter arquivo = new FileWriter(caminho);
            try (PrintWriter writeOnFile = new PrintWriter(arquivo)) {
                Gerente aux = new Gerente();
                String info = aux.getCabecalhoCSV();
                for (Gerente g : this.tmGerente.getLstGerentes()) {
                    info += g.getCSVInfo();
                }
                writeOnFile.print(info);
            }
        } catch (IOException e) {
            Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "ERRO. Impossível salvar. \n"
                    + "Provavelmente o arquivo está corrompido. ");
        }
    }

    @Override
    public void salvar() {
        Gerente g = new Gerente();
        this.copiarCamposParaClasse(g);

        if (this.alteracao) {
            this.tmGerente.getLstGerentes().set(this.buscar(g.getId()), g);
        } else {
            if (this.existe(g)) {
                JOptionPane.showMessageDialog(null, "ID já existe");
            } else {
                this.tmGerente.addLinha(g);
            }

        }
        this.salvar("src/csv/lst_gerentes.csv");
        this.tmGerente.fireTableDataChanged();
        this.resetarCampos(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoes = new javax.swing.ButtonGroup();
        lblNome = new javax.swing.JLabel();
        btnMenu = new javax.swing.JLabel();
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
        lblSenha = new javax.swing.JLabel();
        edtSenha = new javax.swing.JPasswordField();
        lblSubordinado = new javax.swing.JLabel();
        edtSubordinado = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblNome.setText("Nome:");

        btnMenu.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMenu.setText("INFORMAÇÕES DOS GERENTES");

        edtNome.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabela);

        rdMasc.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        rdMasc.setText("Masculino");

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

        lblSenha.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblSenha.setText("Senha:");

        edtSenha.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        edtSenha.setToolTipText("Símbolos não são permitidos!");

        lblSubordinado.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        lblSubordinado.setText("Subordinados:");

        edtSubordinado.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgBack.png"))); // NOI18N
        jButton1.setToolTipText("Retorna ao Menu Principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHoraExtra)
                                    .addComponent(lblSalario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtHoraExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSenha)
                                .addGap(41, 41, 41)
                                .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSubordinado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtSubordinado, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtId, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHoras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton1))
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
                        .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSubordinado)
                        .addComponent(edtSubordinado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSenha)
                        .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
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
                    .addComponent(btnMenu)
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

        Gerente g = new Gerente();
        g = this.tmGerente.getLstGerentes().get(this.buscar(termo));

        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente"
                + " editar os dados de " + g.getNome() + "?");

        if (confirm == JOptionPane.YES_OPTION) {
            this.alteracao = true;
            this.copiarClasseParaCampos(g);
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

        Gerente g = new Gerente();
        g = this.tmGerente.getLstGerentes().get(this.buscar(termo));

        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que "
                + "deseja excluir " + g.getNome() + "?");

        if (confirmacao == JOptionPane.YES_OPTION) {
            this.tmGerente.getLstGerentes().remove(g);
            this.salvar("src/csv/lst_gerentes.csv");
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            //            this.showTable();
            this.tmGerente.fireTableDataChanged();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja "
                + "realmente salvar?");

        if (confirmacao == JOptionPane.YES_OPTION) {
            this.salvar();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ViewMain widget = new ViewMain();
        widget.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel btnMenu;
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
    private javax.swing.JPasswordField edtSenha;
    private javax.swing.JTextField edtSubordinado;
    private javax.swing.JTextField edtTelefone;
    private javax.swing.ButtonGroup grupoBotoes;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSubordinado;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JRadioButton rdFem;
    private javax.swing.JRadioButton rdMasc;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
