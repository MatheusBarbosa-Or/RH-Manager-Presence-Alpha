import Classes.Funcionarios;
import Classes.User;
import DbConnect.DbConnection;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPor favor insira um nome de usuario:");
        String username = scanner.nextLine();

        System.out.println("\nPor favor insira uma senha:");
        String password = scanner.nextLine();

        User usuarioLogado = realizarLogin(username, password);
        if (usuarioLogado != null){
            System.out.println("\nLogin feito com sucesso!");
            int optionPresenca = 0;
            do{
                System.out.println("\nDeseja lançar uma presença? digite 1 para sim, 2 para não");
                optionPresenca = scanner.nextInt();
                scanner.nextLine();
                if (optionPresenca == 1){
                    System.out.println("Informe o Id do funcionario:");
                    Integer IdFuncionario = scanner.nextInt();
                    Funcionarios funcionario = DbConnection.infoFuncionario(IdFuncionario);
                    scanner.nextLine();
                    System.out.println("Informe se você esta entrando ou saindo, digite 1 para entra e 2 para sair:");
                    Integer NovaEntrada_Saida = scanner.nextInt();
                    scanner.nextLine();
                    String Data_Hora = gerarData_Hora(NovaEntrada_Saida);
                    String caminho = gerarRelatorio(funcionario, Data_Hora);
                }
            }while (optionPresenca != 2);
        }else {
            System.out.println("\nUsuario ou senha invalido");
        }
    }

    private static Connection connect() {
        String url = "jdbc:sqlite:DataBase/RH_Manager_DB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static User realizarLogin(String username, String password){
        String sql = "SELECT * FROM Usuarios WHERE username = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set
            if (rs.next()) {
                User usuario = new User();
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCargo(rs.getString("cargo"));
                usuario.setUsuarioid(rs.getInt("Id"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static String gerarData_Hora(Integer NovaEntrada_Saida){
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
    }

}