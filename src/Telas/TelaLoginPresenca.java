package Telas;

import Classes.UserPresence;
import DbConnect.DbConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TelaLoginPresenca {
    private JFrame FrameLoginPresenca;
    private JLabel UsernameTitleLogin;
    private JTextField UsernameFieldLogin;
    private JPasswordField PasswordFieldLogin;
    private JButton ButtonLoginLogin;
    private JLabel IconLogin;
    private JLabel MenuTitleLogin;
    private JLabel PasswordTitle;
    private JPanel PanelLoginPresenca;


    public TelaLoginPresenca(){
        FrameLoginPresenca = new JFrame("RH Manager - Alpha");
        FrameLoginPresenca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameLoginPresenca.setSize(600, 360);
        FrameLoginPresenca.setLocationRelativeTo(null);
        FrameLoginPresenca.add(PanelLoginPresenca);
        FrameLoginPresenca.pack();
        FrameLoginPresenca.setVisible(true);

        ButtonLoginLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserPresence funcLogado = new UserPresence();
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

    private UserPresence realizarLogin(){
        Integer username = Integer.parseInt(UsernameFieldLogin.getText());
        String password = new String(PasswordFieldLogin.getPassword());

        String sql = "SELECT * FROM Usuarios_Presenca WHERE username = ? AND password = ?";

        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UserPresence funcionario = new UserPresence();
                funcionario.setUsername(rs.getInt("username"));
                funcionario.setPassword(rs.getString("password"));
                funcionario.setUserPresenceId(rs.getInt("Id"));
                return funcionario;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void limparCampos() {
        UsernameFieldLogin.setText("");
        PasswordFieldLogin.setText("");
    }
}