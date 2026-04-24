package com.praticapro.sieduardoseixas2026.transportadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    public List<Transportadora> listarTodos() {
        return transportadoraRepository.findAll();
    }

    public List<Transportadora> listarAtivos() {
        return transportadoraRepository.findByAtivoTrue();
    }

    public Optional<Transportadora> buscarPorId(Long id) {
        return transportadoraRepository.findById(id);
    }

    public Optional<Transportadora> buscarPorCpfCnpj(String cpfCnpj) {
        String limpo = cpfCnpj == null ? null : cpfCnpj.replaceAll("[^0-9]", "");
        return transportadoraRepository.findByCpfCnpj(limpo);
    }

    public List<Transportadora> buscarPorNome(String nome) {
        return transportadoraRepository.findByTransportadoraContainingIgnoreCase(nome);
    }

    public List<Transportadora> buscarPorTipo(Integer tipo) {
        return transportadoraRepository.findByTipo(tipo);
    }

    public Transportadora salvar(Transportadora t) {
        validar(t);
        if (t.getCpfCnpj() != null) {
            String limpo = t.getCpfCnpj().replaceAll("[^0-9]", "");
            if (transportadoraRepository.existsByCpfCnpj(limpo)) {
                throw new RuntimeException("Já existe uma transportadora cadastrada com este CPF/CNPJ");
            }
        }
        vincularFilhos(t);
        return transportadoraRepository.save(t);
    }

    public Transportadora atualizar(Long id, Transportadora atualizado) {
        return transportadoraRepository.findById(id).map(t -> {
            validar(atualizado);
            t.setTransportadora(atualizado.getTransportadora());
            t.setApelido(atualizado.getApelido());
            t.setTipo(atualizado.getTipo());
            t.setCpfCnpj(atualizado.getCpfCnpj());
            t.setRgIe(atualizado.getRgIe());
            t.setAntt(atualizado.getAntt());
            t.setCep(atualizado.getCep());
            t.setLogradouro(atualizado.getLogradouro());
            t.setNumero(atualizado.getNumero());
            t.setComplemento(atualizado.getComplemento());
            t.setBairro(atualizado.getBairro());
            t.setCidade(atualizado.getCidade());
            t.setNacionalidade(atualizado.getNacionalidade());
            t.setObservacao(atualizado.getObservacao());
            t.setAtivo(atualizado.getAtivo());
            t.setUsuarioAlteracao(atualizado.getUsuarioAlteracao());

            t.getEmails().clear();
            if (atualizado.getEmails() != null) {
                atualizado.getEmails().forEach(e -> {
                    e.setTransportadora(t);
                    t.getEmails().add(e);
                });
            }

            t.getTelefones().clear();
            if (atualizado.getTelefones() != null) {
                atualizado.getTelefones().forEach(tel -> {
                    tel.setTransportadora(t);
                    t.getTelefones().add(tel);
                });
            }

            return transportadoraRepository.save(t);
        }).orElseThrow(() -> new RuntimeException("Transportadora não encontrada: " + id));
    }

    public void deletar(Long id) {
        if (!transportadoraRepository.existsById(id)) {
            throw new RuntimeException("Transportadora não encontrada: " + id);
        }
        transportadoraRepository.deleteById(id);
    }

    public Transportadora inativar(Long id) {
        return transportadoraRepository.findById(id).map(t -> {
            t.setAtivo(false);
            return transportadoraRepository.save(t);
        }).orElseThrow(() -> new RuntimeException("Transportadora não encontrada: " + id));
    }

    private void validar(Transportadora t) {
        if (t.getTransportadora() == null || t.getTransportadora().trim().isEmpty()) {
            throw new RuntimeException("Nome/Razão Social da transportadora é obrigatório");
        }
        if (t.getTipo() == null || (t.getTipo() != 1 && t.getTipo() != 2)) {
            throw new RuntimeException("Tipo inválido. Use 1 (PF) ou 2 (PJ)");
        }
        if (t.getCpfCnpj() != null) {
            String limpo = t.getCpfCnpj().replaceAll("[^0-9]", "");
            if (t.getTipo() == 1 && limpo.length() != 11) {
                throw new RuntimeException("CPF deve ter 11 dígitos");
            }
            if (t.getTipo() == 2 && limpo.length() != 14) {
                throw new RuntimeException("CNPJ deve ter 14 dígitos");
            }
        }
    }

    private void vincularFilhos(Transportadora t) {
        if (t.getEmails() != null) {
            for (TransportadoraEmail e : t.getEmails()) {
                e.setTransportadora(t);
            }
        }
        if (t.getTelefones() != null) {
            for (TransportadoraTelefone tel : t.getTelefones()) {
                tel.setTransportadora(t);
            }
        }
    }
}
