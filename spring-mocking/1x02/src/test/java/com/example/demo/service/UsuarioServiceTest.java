package com.example.demo.service;


import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    private AutoCloseable closeable;
    @InjectMocks
    private UsuarioService usuarioService;
    @BeforeEach
    public void setUp(){
         closeable = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    public void tearDown() throws Exception {
         closeable.close();
    }
    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        Long id = 1L;
        Usuario usuarioMock = new Usuario(id, "usuario teste", "l@t.com");
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        Usuario usuario = usuarioService.buscarUsuarioPorId(1L);
        assertNotNull(usuario);
        assertEquals(id,usuario.getId());
    }


    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        Long id = 1L;

        assertThrows(RuntimeException.class, () -> {
            usuarioService.buscarUsuarioPorId(1L);
        });
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Long id = 1L;
        Usuario usuarioMock = new Usuario(id, "usuario teste", "l@t.com");
        Mockito.when(usuarioRepository.save(any())).thenReturn(usuarioMock);
        Usuario usuario = usuarioService.salvarUsuario(usuarioMock);
        assertNotNull(usuario);
        assertEquals(id,usuario.getId());
    }
}