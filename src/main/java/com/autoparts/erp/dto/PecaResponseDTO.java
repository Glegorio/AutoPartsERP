package com.autoparts.erp.dto;

import com.autoparts.erp.entity.Peca;

import java.math.BigDecimal;

public class PecaResponseDTO {

    private Long id;
    private String nome;
    private String codigoBarra;
    private String marca;
    private String modeloCompativel;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private Integer quantidadeEstoque;

    public PecaResponseDTO(){}

    // Construtor utilitário para converter de Entidade Peca diretamente para DTO
    public PecaResponseDTO(Peca peca) {
        this.id = peca.getId();
        this.nome = peca.getNome();
        this.codigoBarra = peca.getCodigoBarra();
        this.marca = peca.getMarca();
        this.modeloCompativel = peca.getModeloCompativel();
        this.precoCusto = peca.getPrecoCusto();
        this.precoVenda = peca.getPrecoVenda();
        this.quantidadeEstoque = peca.getQuantidadeEstoque();
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
