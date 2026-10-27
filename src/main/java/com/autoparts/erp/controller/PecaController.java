package com.autoparts.erp.controller;

import com.autoparts.erp.dto.PecaRequestDTO;
import com.autoparts.erp.dto.PecaResponseDTO;
import com.autoparts.erp.service.PecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pecas")
@Tag(name = "Peças", description = "Endpoints para gerenciamento do catálogo de peças")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    // POST '/api/pecas' -> Criar um novo recurso
    @PostMapping
    @Operation(summary = "Cadastrar nova peça", description = "Cria um novo registro de peça no catálogo da loja")
    public ResponseEntity<PecaResponseDTO> criarPeca(@Valid @RequestBody PecaRequestDTO dto) {
        PecaResponseDTO novaPeca = pecaService.criarPeca(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPeca);
    }

    // GET '/api/pecas' -> Consultar todos os recursos
    @GetMapping
    @Operation(summary = "Listar todas as peças", description = "Retorna uma lista completa com todas as peças cadastradas")
    public ResponseEntity<List<PecaResponseDTO>> listarTodas() {
        List<PecaResponseDTO> pecas = pecaService.listarTodas();
        return ResponseEntity.ok(pecas);
    }

    // GET '/api/pecas/{id}' -> Consultar recurso por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar peça por ID", description = "Recupera os detalhes de uma peça específica pelo o ID")
    public ResponseEntity<PecaResponseDTO> buscarPorID(@PathVariable Long id) {
        PecaResponseDTO peca = pecaService.buscarPorId(id);
        return ResponseEntity.ok(peca);
    }

    // PUT '/api/pecas/{id}' -> Atualizar recurso existente por completo
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar peça", description = "Atualiza os dados de uma peça existente pelo seu ID.")
    public ResponseEntity<PecaResponseDTO> atualizarPeca(@PathVariable Long id, @Valid @RequestBody PecaRequestDTO dto) {
        PecaResponseDTO pecaAtualizada = pecaService.atualizarPeca(id, dto);
        return ResponseEntity.ok(pecaAtualizada);
    }

    // DELETE '/api/pecas/{id}' -> Remover recurso
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar peça", description = "Remove um registro de peça do banco de dados pelo seu ID.")
    public ResponseEntity<Void> deletarPeca(@PathVariable Long id) {
        pecaService.deletarPeca(id);
        return ResponseEntity.noContent().build();
    }

}
