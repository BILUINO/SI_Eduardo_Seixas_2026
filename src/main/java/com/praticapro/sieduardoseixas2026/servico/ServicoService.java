package com.praticapro.sieduardoseixas2026.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public List<Servico> listarAtivos() {
        return servicoRepository.findByAtivoTrue();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Optional<Servico> buscarPorCodigo(String codigo) {
        return servicoRepository.findByCodigo(codigo);
    }

    public List<Servico> buscarPorNome(String nome) {
        return servicoRepository.findByServicoContainingIgnoreCase(nome);
    }

    public List<Servico> buscarPorCategoria(Long categoriaId) {
        return servicoRepository.findByCategoriaId(categoriaId);
    }

    public Servico salvar(Servico servico) {
        validar(servico);
        if (servico.getCodigo() != null && !servico.getCodigo().isEmpty()
                && servicoRepository.existsByCodigo(servico.getCodigo())) {
            throw new RuntimeException("Já existe um serviço com este código");
        }
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Long id, Servico atualizado) {
        return servicoRepository.findById(id).map(s -> {
            validar(atualizado);

            if (atualizado.getCodigo() != null
                    && !atualizado.getCodigo().equals(s.getCodigo())
                    && servicoRepository.existsByCodigo(atualizado.getCodigo())) {
                throw new RuntimeException("Já existe outro serviço com este código");
            }

            s.setCodigo(atualizado.getCodigo());
            s.setServico(atualizado.getServico());
            s.setDescricao(atualizado.getDescricao());
            s.setPreco(atualizado.getPreco());
            s.setCusto(atualizado.getCusto());
            s.setTempoEstimadoMinutos(atualizado.getTempoEstimadoMinutos());
            s.setCategoria(atualizado.getCategoria());
            s.setUnidadeMedida(atualizado.getUnidadeMedida());
            s.setObservacao(atualizado.getObservacao());
            s.setAtivo(atualizado.getAtivo());
            s.setUsuarioAlteracao(atualizado.getUsuarioAlteracao());

            return servicoRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Serviço não encontrado: " + id));
    }

    public void deletar(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado: " + id);
        }
        servicoRepository.deleteById(id);
    }

    public Servico inativar(Long id) {
        return servicoRepository.findById(id).map(s -> {
            s.setAtivo(false);
            return servicoRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Serviço não encontrado: " + id));
    }

    private void validar(Servico s) {
        if (s.getServico() == null || s.getServico().trim().isEmpty()) {
            throw new RuntimeException("Nome do serviço é obrigatório");
        }
        if (s.getPreco() == null || s.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço deve ser maior que zero");
        }
        if (s.getCusto() != null && s.getCusto().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Custo não pode ser negativo");
        }
        if (s.getTempoEstimadoMinutos() != null && s.getTempoEstimadoMinutos() < 0) {
            throw new RuntimeException("Tempo estimado não pode ser negativo");
        }
    }
}
