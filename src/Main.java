import Telas.TelaLoginPresenca;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        TelaLoginPresenca telaLoginPresenca = new TelaLoginPresenca();


   /* private static String gerarData_Hora(Integer NovaEntrada_Saida){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = now.format(formatterDate);
        String formattedTime = now.format(formatterTime);
        String Data_Hora = formattedDate + " " + formattedTime;
        if (NovaEntrada_Saida == 1){
            System.out.println("Data: " + formattedDate);
            System.out.println("Horario da Entrada: " + formattedTime);
        } else if (NovaEntrada_Saida == 2) {
            System.out.println("Data: " + formattedDate);
            System.out.println("Horario da Saida: " + formattedTime);
        }
        return(Data_Hora);
    }

    public static String gerarRelatorio(Funcionarios funcionario, String Data_Hora) throws IOException {
        String relatorio = "Relatorio: \n" + "\nNome: " + funcionario.getNome() + "\nId: " + funcionario.getFuncionarioId() +
                "\nCargo: " + funcionario.getCargo() + "\nHorario: " + funcionario.getHorario() +  "\nPresença: " + Data_Hora ;

        String nomeArquivo = "src/Relatorios/Relatorio_" + funcionario.getFuncionarioId() + ".txt";
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            escritor.write(relatorio);
        }
        return nomeArquivo;
    }*/

    }
}