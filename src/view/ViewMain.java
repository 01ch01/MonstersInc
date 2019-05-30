package view;

/**
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 */
public final class ViewMain extends javax.swing.JFrame {

    /**
     * Creates new form ViewMain
     */
    public ViewMain() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoes = new javax.swing.ButtonGroup();
        btnCliente = new javax.swing.JToggleButton();
        btnEngenheiro = new javax.swing.JToggleButton();
        btnDiretor = new javax.swing.JToggleButton();
        btnSecretario = new javax.swing.JToggleButton();
        btnGerente = new javax.swing.JToggleButton();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        grupoBotoes.add(btnCliente);
        btnCliente.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        btnCliente.setText("Cliente");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        grupoBotoes.add(btnEngenheiro);
        btnEngenheiro.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        btnEngenheiro.setText("Engenheiro");
        btnEngenheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEngenheiroActionPerformed(evt);
            }
        });

        grupoBotoes.add(btnDiretor);
        btnDiretor.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        btnDiretor.setText("Diretor");
        btnDiretor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiretorActionPerformed(evt);
            }
        });

        grupoBotoes.add(btnSecretario);
        btnSecretario.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        btnSecretario.setText("Secretário");
        btnSecretario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecretarioActionPerformed(evt);
            }
        });

        grupoBotoes.add(btnGerente);
        btnGerente.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        btnGerente.setText("Gerente");
        btnGerente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenteActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("ESCOLHA UMA OPÇÃO PARA CADASTRO:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnEngenheiro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSecretario, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(btnGerente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(btnDiretor, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEngenheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSecretario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGerente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDiretor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        ViewCliente widget = new ViewCliente();
        widget.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClienteActionPerformed
    private void btnEngenheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEngenheiroActionPerformed
        ViewEngenheiro widget = new ViewEngenheiro();
        widget.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnEngenheiroActionPerformed
    private void btnDiretorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiretorActionPerformed
        ViewDiretor widget = new ViewDiretor();
        widget.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDiretorActionPerformed
    private void btnSecretarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecretarioActionPerformed
        ViewSecretario widget = new ViewSecretario();
        widget.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSecretarioActionPerformed
    private void btnGerenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenteActionPerformed
        ViewGerente widget = new ViewGerente();
        widget.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGerenteActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCliente;
    private javax.swing.JToggleButton btnDiretor;
    private javax.swing.JToggleButton btnEngenheiro;
    private javax.swing.JToggleButton btnGerente;
    private javax.swing.JToggleButton btnSecretario;
    private javax.swing.ButtonGroup grupoBotoes;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables

}
