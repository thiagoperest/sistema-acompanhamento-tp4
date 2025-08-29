package br.edu.infnet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDto {
    private Long id;
    private String numeroPedido;
    private LocalDateTime dataCompra;
    private LocalDateTime previsaoEntrega;
    private BigDecimal valorTotal;
    private String statusAtual;
    private String codigoRastreamento;
    private Long clienteId;
    private List<StatusPedidoDto> historicoStatus;

    public PedidoDto() {
    }

    public PedidoDto(String numeroPedido, BigDecimal valorTotal, Long clienteId) {
        this.numeroPedido = numeroPedido;
        this.valorTotal = valorTotal;
        this.clienteId = clienteId;
        this.dataCompra = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public LocalDateTime getPrevisaoEntrega() {
        return previsaoEntrega;
    }

    public void setPrevisaoEntrega(LocalDateTime previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }

    public String getCodigoRastreamento() {
        return codigoRastreamento;
    }

    public void setCodigoRastreamento(String codigoRastreamento) {
        this.codigoRastreamento = codigoRastreamento;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<StatusPedidoDto> getHistoricoStatus() {
        return historicoStatus;
    }

    public void setHistoricoStatus(List<StatusPedidoDto> historicoStatus) {
        this.historicoStatus = historicoStatus;
    }

    @Override
    public String toString() {
        return "PedidoDto{" +
                "id=" + id +
                ", numeroPedido='" + numeroPedido + '\'' +
                ", valorTotal=" + valorTotal +
                ", statusAtual='" + statusAtual + '\'' +
                '}';
    }
}
