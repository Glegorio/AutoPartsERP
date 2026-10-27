package com.autoparts.erp.dto;

import com.autoparts.erp.entity.MovimentacaoEstoque;
import com.autoparts.erp.entity.TipoMovimentacao;

import java.time.LocalDateTime;

public class MovimentacaoResponseDTO {

    private Long id;
    private Long pecaId;
    private String nomePeca;
    private TipoMovimentacao tipo;
    private Integer quantidade;
    private String motivo;
    private LocalDateTime dataHora;
    private Integer estoqueAtualizadoPeca;

    public MovimentacaoResponseDTO() {}

    public MovimentacaoResponseDTO(MovimentacaoEstoque movimentacao) {
        this.id = movimentacao.getId();
        this.pecaId = movimentacao.getPeca().getId();
        this.nomePeca = movimentacao.getPeca().getNome();
        this.tipo = movimentacao.getTipo();
        this.quantidade = movimentacao.getQuantidade();
        this.motivo = movimentacao.getMotivo();
        this.dataHora = movimentacao.getDataHora();
        this.estoqueAtualizadoPeca = movimentacao.getPeca().getQuantidadeEstoque();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPecaId() {
        return pecaId;
    }

    public void setPecaId(Long pecaId) {
        this.pecaId = pecaId;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getEstoqueAtualizadoPeca() {
        return estoqueAtualizadoPeca;
    }

    public void setEstoqueAtualizadoPeca(Integer estoqueAtualizadoPeca) {
        this.estoqueAtualizadoPeca = estoqueAtualizadoPeca;
    }
}
