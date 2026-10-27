package com.autoparts.erp.service;

import com.autoparts.erp.dto.MovimentacaoRequestDTO;
import com.autoparts.erp.dto.MovimentacaoResponseDTO;
import com.autoparts.erp.dto.PecaResponseDTO;
import com.autoparts.erp.entity.MovimentacaoEstoque;
import com.autoparts.erp.entity.Peca;
import com.autoparts.erp.entity.TipoMovimentacao;
import com.autoparts.erp.repository.MovimentacaoEstoqueRepository;
import com.autoparts.erp.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoRepository;

    @Autowired
    private PecaRepository pecaRepository;

    @Transactional
    public MovimentacaoResponseDTO registrarMovimentacao(MovimentacaoRequestDTO dto){

        Peca peca = pecaRepository.findById(dto.getPecaId())
                .orElseThrow(() -> new RuntimeException("Peça não encontrada com o ID: " + dto.getPecaId()));

        if (dto.getTipo() == TipoMovimentacao.ENTRADA){
            // Aumenta o saldo em estoque
            int novoEstoque = peca.getQuantidadeEstoque() + dto.getQuantidade();
            peca.setQuantidadeEstoque(novoEstoque);
        } else if (dto.getTipo() == TipoMovimentacao.SAIDA){
            // Verifica se há peças suficientes para retirar
            if (peca.getQuantidadeEstoque() < dto.getQuantidade()){
                throw new RuntimeException("Estoque insuficiente! Saldo atual: "
                + peca.getQuantidadeEstoque() + " unidades. Tentaiva de saída: "
                + dto.getQuantidade() + " unidades");
            }

            // Diminui o saldo em estoque
            int novoEstoque = peca.getQuantidadeEstoque() - dto.getQuantidade();
            peca.setQuantidadeEstoque(novoEstoque);
        }

        pecaRepository.save(peca);

        // Registro histórica de Movimentação
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setPeca(peca);
        movimentacao.setTipo(dto.getTipo());
        movimentacao.setQuantidade(dto.getQuantidade());
        movimentacao.setMotivo(dto.getMotivo());
        movimentacao.setDataHora(LocalDateTime.now());

        MovimentacaoEstoque movimentacaoSalva = movimentacaoRepository.save(movimentacao);

        return new MovimentacaoResponseDTO(movimentacaoSalva);
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoResponseDTO> listarTodas(){
        return movimentacaoRepository.findAll()
                .stream()
                .map(MovimentacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoResponseDTO> buscarHistoricoPorPeca(long pecaId){
        // Verificar se existe antes de listar
        if(!pecaRepository.existsById(pecaId)){
            throw new RuntimeException("Peça não encontrada para buscar histórico");
        }
            return movimentacaoRepository.findByPecaIdOrderByDataHoraDesc(pecaId)
                    .stream()
                    .map(MovimentacaoResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PecaResponseDTO> buscarPecasComEstoqueBaixo(Integer limiteMinimo) {
        // Retorna peças onde a quantidade em estoque é menor ou igual ao limite configurado
        return pecaRepository.findAll()
                .stream()
                .filter(peca -> peca.getQuantidadeEstoque() <= limiteMinimo)
                .map(PecaResponseDTO::new)
                .collect(Collectors.toList());
    }
}
