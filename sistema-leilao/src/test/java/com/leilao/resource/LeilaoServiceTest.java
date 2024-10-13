package com.leilao.resource;

import com.leilao.entity.Leilao;
import com.leilao.repository.LeilaoRepository;
import com.leilao.service.LeilaoService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@QuarkusTest
public class LeilaoServiceTest {

    @InjectMocks
    LeilaoService leilaoService;

    @Mock
    LeilaoRepository leilaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarLeilao() {
        Leilao leilao = new Leilao();
        leilao.setEndereco("Rua ABC, 123");
        leilao.setCidade("São Paulo");
        leilao.setEstado("SP");
        leilao.setDataLeilao(LocalDateTime.now().plusDays(1));
        leilao.setDataVisita(LocalDateTime.now());
        
        doNothing().when(leilaoRepository).persist(leilao);  // Simulação de persistência

        Leilao criado = leilaoService.criarLeilao(leilao);
        
        assertNotNull(criado);
        assertEquals("São Paulo", criado.getCidade());
        verify(leilaoRepository, times(1)).persist(leilao);
    }

    @Test
    public void testListarLeiloes() {
        Leilao leilao = new Leilao();
        when(leilaoRepository.listAll()).thenReturn(Collections.singletonList(leilao));

        List<Leilao> leiloes = leilaoService.listarLeiloes();

        assertEquals(1, leiloes.size());
        verify(leilaoRepository, times(1)).listAll();
    }
}
