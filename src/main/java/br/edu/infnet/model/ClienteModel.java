package br.edu.infnet.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClienteModel extends Model<ClienteModel> {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ClienteModel() {
        super("data/clientes.csv");
    }

    public ClienteModel(String nome, String email, String senha) {
        this();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public String toCsv() {
        return String.join(";",
                id != null ? id.toString() : "",
                createdAt != null ? createdAt.format(DATE_FORMATTER) : "",
                updatedAt != null ? updatedAt.format(DATE_FORMATTER) : "",
                nome != null ? nome : "",
                email != null ? email : "",
                senha != null ? senha : "",
                telefone != null ? telefone : "",
                endereco != null ? endereco : ""
        );
    }

    @Override
    public ClienteModel fromCsv(String csvLine) {
        String[] fields = csvLine.split(";");
        ClienteModel cliente = new ClienteModel();

        if (fields.length >= 8) {
            cliente.id = fields[0].isEmpty() ? null : Long.parseLong(fields[0]);
            cliente.createdAt = fields[1].isEmpty() ? null : LocalDateTime.parse(fields[1], DATE_FORMATTER);
            cliente.updatedAt = fields[2].isEmpty() ? null : LocalDateTime.parse(fields[2], DATE_FORMATTER);
            cliente.nome = fields[3].isEmpty() ? null : fields[3];
            cliente.email = fields[4].isEmpty() ? null : fields[4];
            cliente.senha = fields[5].isEmpty() ? null : fields[5];
            cliente.telefone = fields[6].isEmpty() ? null : fields[6];
            cliente.endereco = fields[7].isEmpty() ? null : fields[7];
        }

        return cliente;
    }

    @Override
    protected Long getObjectId(ClienteModel cliente) {
        return cliente.getId();
    }

    public boolean autenticar(String email, String senha) {
        return this.email != null && this.senha != null &&
                this.email.equals(email) && this.senha.equals(senha);
    }

    public ClienteModel findByEmail(String email) {
        List<ClienteModel> clientes = findAll();
        return clientes.stream()
                .filter(c -> c.getEmail() != null && c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
