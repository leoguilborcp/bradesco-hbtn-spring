package com.example.demo.service;


import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {


    @Mock
    private ProdutoRepository produtoRepository;


    @InjectMocks
    private ProdutoService produtoService;


    @Test
    void deveRetornarProdutoQuandoIdExistir() {
        Long id = 1L;
        Produto produtoMock = new Produto(id, "Produto Teste", 100.0);
        Mockito.when(produtoRepository.findById(any())).thenReturn(Optional.of(produtoMock));
        Produto produto = produtoService.buscarPorId(id);
        assertNotNull(produto);
    }


    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        Long id = 1L;
        Mockito.when(produtoRepository.findById(any())).thenReturn(Optional.empty());
    
        assertThrows(RuntimeException.class, () -> {
            produtoService.buscarPorId(1L);
        });
    }
}