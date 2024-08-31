package DbConnect;

import Classes.Funcionario;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.*;

public class DbConnection {
    private static final String URL = "jdbc:sqlite:C:\\Users\\J.Matheus\\IdeaProjects\\TesteTela\\Database\\RH_Manager_DB.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Funcionario loginFuncionario(Integer username, String password){
        String sql = "SELECT * FROM Funcionarios WHERE Id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String DbPasswordHash = rs.getString("PasswordPresenca");
                String DbSaltHash = rs.getString("PasswordSalt");

                String saltedPassword = password + DbSaltHash;
                BCrypt.Result result = BCrypt.verifyer().verify(saltedPassword.toCharArray(), DbPasswordHash);

                if (result.verified) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setNome(rs.getString("Nome"));
                    funcionario.setCpf(rs.getString("CPF"));
                    funcionario.setEmail(rs.getString("Email"));
                    funcionario.setDataNascimento(rs.getString("DataNascimento"));
                    funcionario.setGenero(rs.getString("Genero"));
                    funcionario.setCargo(rs.getString("Cargo"));
                    funcionario.setHorario(rs.getString("Horario"));
                    funcionario.setFuncionarioId(rs.getInt("Id"));
                    funcionario.setPasswordPresenca(DbPasswordHash);

                    return funcionario;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String inserirEntrada(String data, String entrada, Funcionario funcLogado){
        String sql = "INSERT INTO Presenca (Data, Entrada, Saida, IdFuncionario) VALUES (?, ?, ?, ?)";
        String saidaPadrão = "";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, data);
            pstmt.setString(2, entrada);
            pstmt.setString(3, saidaPadrão);
            pstmt.setInt(4, funcLogado.getFuncionarioId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String inserirSaida(String data, String saida, Funcionario funcLogado){
        String sql = "UPDATE Presenca SET Saida = ? WHERE Data = ? AND IdFuncionario = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, saida);
            pstmt.setString(2, data);
            pstmt.setInt(3, funcLogado.getFuncionarioId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean verifyEntrada(String data, Funcionario funcLogado){
        String sql = "SELECT Data FROM Presenca WHERE Data = ? AND IdFuncionario = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, data);
            pstmt.setInt(2, funcLogado.getFuncionarioId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                String checkData = rs.getString("Data");
                if (checkData.equals(data)){
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean verifySaida(String data, Funcionario funcLogado){
        String sql = "SELECT Saida FROM Presenca WHERE Data = ? AND IdFuncionario = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, data);
            pstmt.setInt(2, funcLogado.getFuncionarioId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()){
                String checkSaida = rs.getString("Saida");
                if (checkSaida.equals("")){
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}