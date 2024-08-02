package DbConnect;

import Classes.Funcionarios;

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

    public static Funcionarios loginFuncionario(Integer username, String password){
        String sql = "SELECT * FROM Funcionarios WHERE Id = ? AND PasswordPresenca = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Funcionarios funcionario = new Funcionarios();

                funcionario.setNome(rs.getString("Nome"));
                funcionario.setCpf(rs.getString("CPF"));
                funcionario.setEmail(rs.getString("Email"));
                funcionario.setDataNascimento(rs.getString("DataNascimento"));
                funcionario.setGenero(rs.getString("Genero"));
                funcionario.setCargo(rs.getString("Cargo"));
                funcionario.setHorario(rs.getString("Horario"));
                funcionario.setFuncionarioId(rs.getInt("Id"));
                funcionario.setPasswordPresenca(rs.getString("PasswordPresenca"));

                return funcionario;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String inserirPresenca(String data, String entrada, Funcionarios funcLogado){
        String sql = "INSERT INTO Presenca (Data, Entrada_Saida, IdFuncionario) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, data);
            pstmt.setString(2, entrada);
            pstmt.setInt(3, funcLogado.getFuncionarioId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String updatePresenca(String data, String saida, Funcionarios funcLogado){
        String sql = "UPDATE Presenca SET Entrada_Saida = Entrada_Saida || ? WHERE Data = ? AND IdFuncionario = ?";


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
}