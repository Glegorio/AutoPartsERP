package com.autoparts.erp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class PecaRequestDTO {

    @NotBlank(message = "O nome da peça é obrigatório")
    @Size(max = 100, message = "O nome deve ter o máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O código de barras é obrigatório")
    private String codigoBarra;

    @NotBlank(message = "A marca da peça é obrigatória")
    private String marca;

    private String modeloCompativel;

    // @NotNull valida que o campo não venha nulo no JSON
    @NotNull(message = "O preço de custo é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço de custo deve ser maior que zero")
    private BigDecimal precoCusto;

    @NotNull(message = "O preço de venda é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço de venda deve ser maior que zero")
    private BigDecimal precoVenda;

    @NotNull(message = "A quantidade inicial em estoque é obrigatória")
    @DecimalMin(value = "0", message = "A quantidade em estoque não pode ser negativa")
    private Integer quantidadeEstoque;

    public PecaRequestDTO(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModeloCompativel() {
        return modeloCompativel;
    }

    public void setModeloCompativel(String modeloCompativel) {
        this.modeloCompativel = modeloCompativel;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
