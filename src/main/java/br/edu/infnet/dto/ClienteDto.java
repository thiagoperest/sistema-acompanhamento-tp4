package br.edu.infnet.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ClienteDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private LocalDateTime dataCadastro;
    private List<PedidoDto> pedidos;

    public ClienteDto() {
    }

    public ClienteDto(String nome, String email, String telefone, String endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<PedidoDto> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDto> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "ClienteDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
