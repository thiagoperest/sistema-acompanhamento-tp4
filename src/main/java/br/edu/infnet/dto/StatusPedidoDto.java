package br.edu.infnet.dto;

import java.time.LocalDateTime;

public class StatusPedidoDto {
    private Long id;
    private String status;
    private String descricao;
    private LocalDateTime dataHoraAtualizacao;
    private String justificativa;
    private Long pedidoId;

    public StatusPedidoDto() {
    }

    public StatusPedidoDto(String status, String descricao, Long pedidoId) {
        this.status = status;
        this.descricao = descricao;
        this.pedidoId = pedidoId;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(LocalDateTime dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    @Override
    public String toString() {
        return "StatusPedidoDto{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHoraAtualizacao=" + dataHoraAtualizacao +
                '}';
    }
}
