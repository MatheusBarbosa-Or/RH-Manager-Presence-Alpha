package Telas;

import Classes.Funcionarios;
import DbConnect.DbConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLoginPresenca {
    private JFrame FrameLoginPresenca;
    private JLabel UsernameTitleLogin;
    private JTextField UsernameFieldLogin;
    private JPasswordField PasswordFieldLogin;
    private JButton ButtonLoginLogin;
    private JLabel IconLogin;
    private JLabel PasswordTitle;
    private JPanel PanelLoginPresenca;

    public TelaLoginPresenca(){
        FrameLoginPresenca = new JFrame("RH Manager - Alpha");
        FrameLoginPresenca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameLoginPresenca.setSize(600, 440);
        FrameLoginPresenca.setLocationRelativeTo(null);
        FrameLoginPresenca.add(PanelLoginPresenca);
        FrameLoginPresenca.pack();
        FrameLoginPresenca.setVisible(true);

        ButtonLoginLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Funcionarios funcLogado = new Funcionarios();
                funcLogado = realizarLogin();
                if (funcLogado != null) {
                    JOptionPane.showMessageDialog(FrameLoginPresenca, "Login feito com sucesso!\n");
                    TelaHomepagePresenca telaHomepage = new TelaHomepagePresenca(FrameLoginPresenca, funcLogado);
                    FrameLoginPresenca.setVisible(false);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(FrameLoginPresenca, "Nome de usuario ou senha inválida!");
                }
            }
        });
    }

    private Funcionarios realizarLogin(){
        Integer username = Integer.parseInt(UsernameFieldLogin.getText());
        String password = new String(PasswordFieldLogin.getPassword());
        Funcionarios funcionario = DbConnection.loginFuncionario(username,password);

        return funcionario;
    }

    public void limparCampos() {
        UsernameFieldLogin.setText("");
        PasswordFieldLogin.setText("");
    }
}