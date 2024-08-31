package Telas;

import Classes.Funcionario;
import DbConnect.DbConnection;
import at.favre.lib.crypto.bcrypt.BCrypt;

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
        FrameLoginPresenca.setSize(500, 600);
        FrameLoginPresenca.setLocationRelativeTo(null);
        FrameLoginPresenca.add(PanelLoginPresenca);
        FrameLoginPresenca.pack();
        FrameLoginPresenca.setVisible(true);

        ButtonLoginLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Funcionario funcLogado = new Funcionario();
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

    private Funcionario realizarLogin(){
        Integer username = Integer.parseInt(UsernameFieldLogin.getText());
        String password = new String(PasswordFieldLogin.getPassword());
        Funcionario funcionario = DbConnection.loginFuncionario(username,password);

        return funcionario;
    }

    public void limparCampos() {
        UsernameFieldLogin.setText("");
        PasswordFieldLogin.setText("");
    }
}