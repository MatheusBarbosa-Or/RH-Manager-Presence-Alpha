package Telas;

import Classes.Funcionario;
import DbConnect.DbConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelaHomepagePresenca {
    private JFrame FrameHomepagePresenca;
    private JPanel PanelHomepagePresenca;
    private JButton ButtonEntrada;
    private JButton ButtonSaida;
    private JButton ButtonSaidaPresenca;
    private JLabel HomepageTitle;
    private JLabel InfoTitle;
    private JLabel InfoFunc;
    private JLabel FuncIcon;

    private JFrame FrameLoginPresenca;
    private Integer novaEntrada_Saida = 0;

    public TelaHomepagePresenca(JFrame frameLoginPresenca, Funcionario funcLogado){
        FrameHomepagePresenca = new JFrame("RH Manager - Alpha");
        FrameHomepagePresenca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameHomepagePresenca.setSize(450, 250);
        FrameHomepagePresenca.setLocationRelativeTo(null);
        FrameHomepagePresenca.add(PanelHomepagePresenca);
        FrameHomepagePresenca.pack();
        FrameHomepagePresenca.setVisible(true);

        this.FrameLoginPresenca = frameLoginPresenca;

        if (funcLogado.getGenero().equals("Feminino")){
            FuncIcon.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile F.png")));
        } else if (funcLogado.getGenero().equals("Masculino")) {
            FuncIcon.setIcon(new ImageIcon(getClass().getResource("/IMG/Profile.png")));
        } else if (funcLogado.getGenero().equals("Outros")) {
            FuncIcon.setIcon(new ImageIcon(getClass().getResource("/IMG/Outros2.png")));
        }

        InfoFunc.setText("<html>"
                + "Nome: " + funcLogado.getNome() + "<br>"
                + "Id: " + funcLogado.getFuncionarioId() + "<br>"
                + "Genero: " + funcLogado.getGenero() + "<br>"
                + "Cargo: " + funcLogado.getCargo() + "<br>"
                + "Horario: " + funcLogado.getHorario() + "</html>");
        /////////////////////////////////////////////////////////////////

        ButtonSaidaPresenca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameHomepagePresenca.setVisible(false);
                FrameLoginPresenca.setVisible(true);
            }
        });

        ButtonEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaEntrada_Saida = 1;
                String data = gerarData(novaEntrada_Saida);
                String entrada = gerarHora(novaEntrada_Saida);
                DbConnection.inserirPresenca(data, entrada, funcLogado);
                JOptionPane.showMessageDialog(FrameLoginPresenca, "Presença Confirmada!\n");
                FrameHomepagePresenca.setVisible(false);
                FrameLoginPresenca.setVisible(true);
            }
        });

        ButtonSaida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaEntrada_Saida = 2;
                String data = gerarData(novaEntrada_Saida);
                String saida = gerarHora(novaEntrada_Saida);
                DbConnection.updatePresenca(data, saida, funcLogado);
                JOptionPane.showMessageDialog(FrameLoginPresenca, "Saida Confirmada!\n");
                FrameHomepagePresenca.setVisible(false);
                FrameLoginPresenca.setVisible(true);
            }
        });
    }

    private static String gerarData(Integer NovaEntrada_Saida) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = now.format(formatterDate);
        String data = formattedDate;

        return (data);
    }

    private static String gerarHora(Integer NovaEntrada_Saida) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = now.format(formatterTime);
        String hora = "";

        if (NovaEntrada_Saida == 1) {
            hora = ("Horario da Entrada: " + formattedTime);
        } else if (NovaEntrada_Saida == 2) {
            hora = (" - Horario da Saida: " + formattedTime);
        }
        return (hora);
    }
}