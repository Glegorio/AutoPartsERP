package com.autoparts.erp.controller;

import com.autoparts.erp.dto.MovimentacaoRequestDTO;
import com.autoparts.erp.dto.MovimentacaoResponseDTO;
import com.autoparts.erp.dto.PecaResponseDTO;
import com.autoparts.erp.service.MovimentacaoEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@Tag(name = "Controle de Estoque", description = "Endpoints para registro de entradas, saídas e extrato de movimentações de peças")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService movimentacaoService;

    @PostMapping
    @Operation(summary = "Registrar entrada ou saída de estoque", description = "Realiza uma movimentação de estoque (ENTRADA ou SAIDA), atualiza o saldo do produto e grava o histórico.")
    public ResponseEntity<MovimentacaoResponseDTO> registrarMovimentacao(@Valid @RequestBody MovimentacaoRequestDTO dto) {
        MovimentacaoResponseDTO resposta = movimentacaoService.registrarMovimentacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    @Operation(summary = "Listar todas as movimentações", description = "Retorna o histórico geral de movimentações registradas no sistema.")
    public ResponseEntity<List<MovimentacaoResponseDTO>> listarTodas() {
        List<MovimentacaoResponseDTO> movimentacoes = movimentacaoService.listarTodas();
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/peca/{pecaId}")
    @Operation(summary = "Extrato de movimentações por peça", description = "Retorna o histórico individual de entradas e saídas de uma peça específica pelo seu ID.")
    public ResponseEntity<List<MovimentacaoResponseDTO>> buscarHistoricoPorPeca(@PathVariable Long pecaId) {
        List<MovimentacaoResponseDTO> historico = movimentacaoService.buscarHistoricoPorPeca(pecaId);
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/alertas-estoque-baixo")
    @Operation(summary = "Alertas de estoque baixo", description = "Retorna todas as peças que possuem saldo de estoque igual ou inferior ao limite informado.")
    public ResponseEntity<List<PecaResponseDTO>> buscarPecasComEstoqueBaixo(@RequestParam(defaultValue = "5") Integer limiteMinimo) {
        List<PecaResponseDTO> pecasEmAlerta = movimentacaoService.buscarPecasComEstoqueBaixo(limiteMinimo);
        return ResponseEntity.ok(pecasEmAlerta);
    }
}