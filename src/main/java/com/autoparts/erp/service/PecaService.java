package com.autoparts.erp.service;

import com.autoparts.erp.dto.PecaRequestDTO;
import com.autoparts.erp.dto.PecaResponseDTO;
import com.autoparts.erp.entity.Peca;
import com.autoparts.erp.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    @Transactional
    public PecaResponseDTO criarPeca(PecaRequestDTO dto){
        if (pecaRepository.existsByCodigoBarra(dto.getCodigoBarra())){
            throw new RuntimeException("Já existe uma peça cadastrada com o código de barras: " + dto.getCodigoBarra());
        }

        // Converter o DTO recebido para a Entidade que será gravada no MySQL
        Peca peca = new Peca();
        peca.setNome(dto.getNome());
        peca.setCodigoBarra(dto.getCodigoBarra());
        peca.setMarca(dto.getMarca());
        peca.setModeloCompativel(dto.getModeloCompativel());
        peca.setPrecoCusto(dto.getPrecoCusto());
        peca.setPrecoVenda(dto.getPrecoVenda());
        peca.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        Peca pecaSalva = pecaRepository.save(peca);

        return new PecaResponseDTO(pecaSalva);
    }

    @Transactional(readOnly = true)
    public List<PecaResponseDTO> listarTodas(){
        return pecaRepository.findAll()
                .stream()
                .map(PecaResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PecaResponseDTO buscarPorId(Long id){
        Peca peca = pecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada com o ID: " + id));
        return new PecaResponseDTO(peca);
    }

    @Transactional
    public PecaResponseDTO atualizarPeca(Long id, PecaRequestDTO dto){
        Peca pecaExistente = pecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada para atualização. ID: " + id));

        pecaExistente.setNome(dto.getNome());
        pecaExistente.setCodigoBarra(dto.getCodigoBarra());
        pecaExistente.setMarca(dto.getMarca());
        pecaExistente.setModeloCompativel(dto.getModeloCompativel());
        pecaExistente.setPrecoCusto(dto.getPrecoCusto());
        pecaExistente.setPrecoVenda(dto.getPrecoVenda());
        pecaExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        Peca pecaAtualizada = pecaRepository.save(pecaExistente);
        return new PecaResponseDTO(pecaAtualizada);
    }

    @Transactional
    public void deletarPeca(Long id){
        if(!pecaRepository.existsById(id)){
            throw new RuntimeException("Não foi possível deletar. Peça não encontrada com o ID: " + id);
        }
        pecaRepository.deleteById(id);
    }
}
