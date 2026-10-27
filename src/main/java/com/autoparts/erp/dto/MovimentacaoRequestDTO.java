package com.autoparts.erp.dto;

import com.autoparts.erp.entity.TipoMovimentacao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovimentacaoRequestDTO {

    @NotNull(message = "O ID da peça é obrigatório")
    private Long pecaId;

    @NotNull(message = "O tipo de movimentação é obrigatório (ENTRADA ou SAIDA)")
    private TipoMovimentacao tipo;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade movimentada deve ser de no mínimo 1 unidade")
    private Integer quantidade;

    @NotBlank(message = "O motivo da movimentação é obrigatório")
    private String motivo;

    public MovimentacaoRequestDTO() {}

    public Long getPecaId() {
        return pecaId;
    }

    public void setPecaId(Long pecaId) {
        this.pecaId = pecaId;
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
}
