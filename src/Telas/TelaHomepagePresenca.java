package Telas;

import Classes.Funcionarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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

    public TelaHomepagePresenca(JFrame frameLoginPresenca, Funcionarios funcLogado){
        FrameHomepagePresenca = new JFrame("RH Manager - Alpha");
        FrameHomepagePresenca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameHomepagePresenca.setSize(430, 250);
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

            }
        });


        ButtonSaida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
