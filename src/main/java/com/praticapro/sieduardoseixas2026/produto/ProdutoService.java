package com.praticapro.sieduardoseixas2026.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<Produto> listarAtivos() {
        return produtoRepository.findByAtivoTrue();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> buscarPorCodigoBarras(String codigoBarras) {
        return produtoRepository.findByCodigoBarras(codigoBarras);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> listarAbaixoDoMinimo() {
        return produtoRepository.findAll().stream()
                .filter(p -> p.getQuantidade() != null
                        && p.getQuantidadeMinima() != null
                        && p.getQuantidade().compareTo(p.getQuantidadeMinima()) < 0)
                .collect(Collectors.toList());
    }

    public Produto salvar(Produto produto) {
        calcularPercentualLucro(produto);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setCodigoBarras(produtoAtualizado.getCodigoBarras());
            produto.setReferencia(produtoAtualizado.getReferencia());
            produto.setMarca(produtoAtualizado.getMarca());
            produto.setUnidadeMedida(produtoAtualizado.getUnidadeMedida());
            produto.setCategoria(produtoAtualizado.getCategoria());
            produto.setValorCompra(produtoAtualizado.getValorCompra());
            produto.setValorVenda(produtoAtualizado.getValorVenda());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            produto.setQuantidadeMinima(produtoAtualizado.getQuantidadeMinima());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setObservacoes(produtoAtualizado.getObservacoes());
            produto.setAtivo(produtoAtualizado.getAtivo());
            calcularPercentualLucro(produto);
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    private void calcularPercentualLucro(Produto produto) {
        BigDecimal compra = produto.getValorCompra();
        BigDecimal venda = produto.getValorVenda();
        if (compra != null && venda != null && compra.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal lucro = venda.subtract(compra)
                    .divide(compra, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    .setScale(2, RoundingMode.HALF_UP);
            produto.setPercentualLucro(lucro);
        } else if (produto.getPercentualLucro() == null) {
            produto.setPercentualLucro(BigDecimal.ZERO);
        }
    }
}
