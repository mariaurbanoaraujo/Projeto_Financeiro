package business.view;

import business.Aplicacao;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class TelaInvestimento extends JFrame {

    private JTextField txtValorInicial;
    private JTextField txtPrazo;
    private JComboBox<String> cbTipoAplicacao;
    private JButton btnCalcular;
    private JLabel lblResultado;

    public TelaInvestimento() {
        setTitle("Simulador de Investimento");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(null);

        JLabel lblValorInicial = new JLabel("Valor Inicial");
        lblValorInicial.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblValorInicial.setBounds(40, 30, 120, 25);
        add(lblValorInicial);

        txtValorInicial = new JTextField();
        txtValorInicial.setBounds(180, 25, 200, 30);
        txtValorInicial.setHorizontalAlignment(JTextField.CENTER);
        add(txtValorInicial);

        txtValorInicial.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != ',' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        JLabel lblPrazo = new JLabel("Prazo");
        lblPrazo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblPrazo.setBounds(40, 75, 120, 25);
        add(lblPrazo);

        txtPrazo = new JTextField();
        txtPrazo.setBounds(180, 70, 200, 30);
        txtPrazo.setHorizontalAlignment(JTextField.CENTER);
        add(txtPrazo);

        txtPrazo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        JLabel lblTipo = new JLabel("Tipo de Aplicação");
        lblTipo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblTipo.setBounds(40, 120, 130, 25);
        add(lblTipo);

        String[] opcoes = { "Poupança", "CDI", "Tesouro Direto" };
        cbTipoAplicacao = new JComboBox<>(opcoes);
        cbTipoAplicacao.setBounds(180, 115, 200, 30);
        add(cbTipoAplicacao);

        btnCalcular = new JButton("Calcular Rendimento");
        btnCalcular.setBounds(120, 165, 200, 35);
        add(btnCalcular);

        lblResultado = new JLabel("Valor Final da Aplicação: R$ ", SwingConstants.CENTER);
        lblResultado.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblResultado.setBounds(20, 220, 400, 30);
        add(lblResultado);

        btnCalcular.addActionListener(e -> calcular());
    }

    private void calcular() {
        try {
            String strValor = txtValorInicial.getText().replace(",", ".");
            if (strValor.isEmpty() || txtPrazo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            float valorInicial = Float.parseFloat(strValor);
            int prazo = Integer.parseInt(txtPrazo.getText());

            String opcaoSelecionada = (String) cbTipoAplicacao.getSelectedItem();
            float taxa = 0.0f;

            if ("Poupança".equals(opcaoSelecionada)) {
                taxa = 0.38f;
            } else if ("CDI".equals(opcaoSelecionada)) {
                taxa = 0.53f;
            } else if ("Tesouro Direto".equals(opcaoSelecionada)) {
                taxa = 0.65f;
            }

            Aplicacao app = new Aplicacao();
            app.calcularRendimento(valorInicial, prazo, taxa);

            lblResultado.setText(String.format("Valor Final da Aplicação: R$ %.2f", app.getMontanteFinal()));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite valores numéricos válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}