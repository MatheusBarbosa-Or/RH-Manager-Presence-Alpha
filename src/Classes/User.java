package Classes;

public class User {
    private String username;
    private String password;
    private String nome;
    private String cpf;
    private String cargo;
    private Integer Usuarioid;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getUsuarioid() {
        return Usuarioid;
    }

    public void setUsuarioid(Integer usuarioid) {
        this.Usuarioid = usuarioid;
    }

    public void identify(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Cargo: " + this.cargo);
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nCPF: " + this.cpf + "\nCargo: " + this.cargo;
    }

}