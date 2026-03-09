/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cookieclickercosmico;

/**
 *
 * @author sowbo
 */
public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());

    private GameState estado;
    private javax.swing.JPanel painelMenuInferior; 
    private cookieclickercosmico.Telas.TelaInicio telaInicio;
    private cookieclickercosmico.Telas.TelaConquistas telaConquistas;
    private cookieclickercosmico.Telas.TelaPerfil telaPerfil;
    private javax.swing.JLabel lblCookiesPersistente; // O novo contador de cookies
    private javax.swing.JPanel painelContadorPersistente; // Referência para mostrar/esconder
    /**
     * Creates new form MainFrame
     */
    public MainFrame(GameState estado) {
        this.estado = estado;
        initComponents(); // O NetBeans faz a bagunça dele aqui...
        
        // 1. A MARRETA: Forçamos a janela principal a usar o BorderLayout, ignorando o NetBeans!
        this.getContentPane().setLayout(new java.awt.BorderLayout());

        // 2. Transforma o painel central em um baralho de cartas
        painelCartas.setLayout(new java.awt.CardLayout());
        
        // 3. Adiciona as cartas
        telaInicio = new cookieclickercosmico.Telas.TelaInicio(this.estado); 
        cookieclickercosmico.Telas.TelaLoja telaLoja = new cookieclickercosmico.Telas.TelaLoja(this.estado, this);
        telaConquistas = new cookieclickercosmico.Telas.TelaConquistas(this.estado);
        telaPerfil = new cookieclickercosmico.Telas.TelaPerfil(this.estado);
        cookieclickercosmico.Telas.TelaMenu telaMenu = new cookieclickercosmico.Telas.TelaMenu(this.estado, this);
        cookieclickercosmico.Telas.TelaReviver telaReviver = new cookieclickercosmico.Telas.TelaReviver(this.estado);

        painelCartas.add(telaInicio, "cardInicio");
        painelCartas.add(telaLoja, "cardLoja");
        painelCartas.add(telaConquistas, "cardConquistas");
        painelCartas.add(telaPerfil, "cardPerfil");
        painelCartas.add(telaMenu, "cardMenu");
        painelCartas.add(telaReviver, "cardReviver");
        
        // 4. Cria e configura o menu inferior com GridLayout (1 linha, 4 colunas)
        painelMenuInferior = new javax.swing.JPanel();
        painelMenuInferior.setLayout(new java.awt.GridLayout(1, 4)); 
        
        // Criando os botões
        javax.swing.JButton btnInicio = new javax.swing.JButton("Início");
        javax.swing.JButton btnConquistas = new javax.swing.JButton("Conquistas");
        javax.swing.JButton btnLoja = new javax.swing.JButton("Loja");
        javax.swing.JButton btnPerfil = new javax.swing.JButton("Perfil");

        // --- APLICANDO O VISUAL (Tema Perfil: Botões pretos) ---
        javax.swing.JButton[] botoesMenu = {btnInicio, btnConquistas, btnLoja, btnPerfil};
        for (javax.swing.JButton btn : botoesMenu) {
            btn.setBackground(java.awt.Color.BLACK); // Fundo Preto
            btn.setForeground(java.awt.Color.WHITE); // Texto branco
            btn.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.WHITE, 1)); // Borda fina
            btn.setFocusPainted(false); // Tira aquele quadrado feio de seleção ao clicar
            btn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Deixa a fonte mais bonita
        }

        // --- ADICIONANDO AS AÇÕES DE CLIQUE ---
        btnInicio.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
            cl.show(painelCartas, "cardInicio"); 
            if (painelContadorPersistente != null) painelContadorPersistente.setVisible(false);
        });

        btnConquistas.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
            cl.show(painelCartas, "cardConquistas"); 
            telaConquistas.atualizarConquistas(); // Força a atualização visual
            if (painelContadorPersistente != null) painelContadorPersistente.setVisible(true);
        });

        btnLoja.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
            cl.show(painelCartas, "cardLoja"); 
            if (painelContadorPersistente != null) painelContadorPersistente.setVisible(true);
        });

        btnPerfil.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
            cl.show(painelCartas, "cardPerfil"); 
            if (painelContadorPersistente != null) painelContadorPersistente.setVisible(true);
        });

        // Adiciona os botões formatados no painel
        painelMenuInferior.add(btnInicio);
        painelMenuInferior.add(btnConquistas);
        painelMenuInferior.add(btnLoja);
        painelMenuInferior.add(btnPerfil);        
        // 5. Criação do Contador Persistente (fica entre as telas e o menu)
        painelContadorPersistente = new javax.swing.JPanel();
        painelContadorPersistente.setBackground(java.awt.Color.BLACK);
        painelContadorPersistente.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 10)); // Centralizado com padding

        lblCookiesPersistente = new javax.swing.JLabel("Biscoitos: 0");
        lblCookiesPersistente.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        lblCookiesPersistente.setForeground(java.awt.Color.WHITE);
        painelContadorPersistente.add(lblCookiesPersistente);

        // Agrupamos o Contador e o Menu Inferior no mesmo painel SUL para ficarem juntos
        javax.swing.JPanel painelSulAgrupado = new javax.swing.JPanel();
        painelSulAgrupado.setLayout(new java.awt.BorderLayout());
        painelSulAgrupado.add(painelContadorPersistente, java.awt.BorderLayout.NORTH);
        painelSulAgrupado.add(painelMenuInferior, java.awt.BorderLayout.SOUTH);
        
        // Mantém invisível até o jogador apertar o PLAY
        painelSulAgrupado.setVisible(false); 
        // Pra poder esconder tudo junto depois, guardamos a referencia do painel sul no lugar do painelMenuInferior no iniciarJogo
        painelMenuInferior = painelSulAgrupado; 
        
        // 6. O SEGUNDO GOLPE DA MARRETA: Encaixa as peças nos lugares certos do BorderLayout
        this.getContentPane().add(painelCartas, java.awt.BorderLayout.CENTER); // Painel das telas vai no MEIO
        this.getContentPane().add(painelSulAgrupado, java.awt.BorderLayout.SOUTH); // O agrupamento vai embaixo (SUL)
        
        // 7. Mostra o Menu primeiro (ou o Início, para testar a navegação)
        java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
        cl.show(painelCartas, "cardMenu");
        if (painelContadorPersistente != null) painelContadorPersistente.setVisible(false);
        
        // 7. Garante que a tela recarregue o visual com as novas peças
        this.revalidate();
        this.repaint();
    }

    public void iniciarJogo() {
        painelMenuInferior.setVisible(true);
        if (painelContadorPersistente != null) painelContadorPersistente.setVisible(false); // Início não tem persistente
        
        java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
        cl.show(painelCartas, "cardInicio");
    }
    
    public void atualizarTextos() {
        // Verifica se alcançou o nível 10 para o Prestígio (Novo Jogo+)
        if (estado.calcularNivelAtual() >= 10 && !estado.isPopupPrestigioMostrado()) {
            estado.setPopupPrestigioMostrado(true);
            
            int escolha = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "UAU! Você alcançou o Nível 10 das galáxias!\n" +
                "Deseja fazer um NEW GAME+ (Reviver) agora?\n" +
                "(Seus ganhos futuros serão multiplicados!)",
                "Hora de Renascer!",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE
            );
            
            if (escolha == javax.swing.JOptionPane.YES_OPTION) {
                // Vai direto pra tela de reviver
                java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
                cl.show(painelCartas, "cardReviver");
            }
        }
        
        System.out.println("atualizando");
        // Se a tela de início já foi criada, manda ela se atualizar
        if (this.telaInicio != null) {
            System.out.println("atualizando 2");

            this.telaInicio.atualizarTela(); 
        }
        
        if (this.telaConquistas != null) {
            this.telaConquistas.atualizarConquistas();
        }
        
        if (this.telaPerfil != null) {
            this.telaPerfil.atualizarPerfil();
        }
        
        // Atualiza a barrinha inferior com o os biscoitos atuais globalmente
        if (this.lblCookiesPersistente != null) {
            this.lblCookiesPersistente.setText(String.format("Biscoitos: %.0f", estado.getTotalCookies()));
        }
        
        // Mais pra frente, você vai adicionar a Loja aqui também!
        // if (this.telaLoja != null) {
        //     this.telaLoja.atualizarBotoesDeCompra();
        // }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        painelCartas = new javax.swing.JPanel();

        jButton2.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout painelCartasLayout = new javax.swing.GroupLayout(painelCartas);
        painelCartas.setLayout(painelCartasLayout);
        painelCartasLayout.setHorizontalGroup(
            painelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        painelCartasLayout.setVerticalGroup(
            painelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel painelCartas;
    // End of variables declaration//GEN-END:variables
}
