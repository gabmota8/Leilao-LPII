package com.leilao.resource;

import com.leilao.entity.Lance;
import com.leilao.entity.Produto; // Usando a classe Produto genérica
import com.leilao.entity.ProdutoNotebook;
import com.leilao.repository.LanceRepository;
import com.leilao.repository.ProdutoRepository;
import com.leilao.service.LanceService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.mockito.Mockito.*;

@QuarkusTest
public class LanceResourceTest {

    @InjectMocks
    LanceService lanceService;

    @Mock
    LanceRepository lanceRepository;

    @Mock
    ProdutoRepository produtoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testRegistrarLanceValido() {
        Produto produto = new ProdutoNotebook();
        produto.setId(1L);

        Lance lance = new Lance();
        lance.setProduto(produto);
        lance.setValor(2000.00);
        lance.setDataHora(LocalDateTime.now());

        when(produtoRepository.findByIdOptional(1L)).thenReturn(Optional.of(produto));
        when(lanceRepository.list("produto", produto)).thenReturn(Collections.emptyList());
        doNothing().when(lanceRepository).persist(lance);

        given()
          .contentType(ContentType.JSON)
          .body(lance)
          .when().post("/lances")
          .then()
             .statusCode(201)  // Verifica se o lance foi criado
             .body("valor", equalTo(2000.00F));

        verify(produtoRepository, times(1)).findByIdOptional(1L);
        verify(lanceRepository, times(1)).persist(lance);
    }

    @Test
    public void testRegistrarLanceInvalido() {
        Produto produto = new ProdutoNotebook();
        produto.setId(1L);

        Lance lanceAnterior = new Lance();
        lanceAnterior.setProduto(produto);
        lanceAnterior.setValor(2000.00); // Lance anterior foi 2000.00

        Lance novoLance = new Lance();
        novoLance.setProduto(produto);
        novoLance.setValor(1500.00);  // Novo lance inválido, menor que o anterior
        novoLance.setDataHora(LocalDateTime.now());

        when(produtoRepository.findByIdOptional(1L)).thenReturn(Optional.of(produto));
        when(lanceRepository.list("produto", produto)).thenReturn(Collections.singletonList(lanceAnterior));

        given()
          .contentType(ContentType.JSON)
          .body(novoLance)
          .when().post("/lances")
          .then()
             .statusCode(400)  // Verifica se retorna 400 Bad Request
             .body("message", equalTo("Lance inválido"));  // Mensagem de erro esperada

        verify(produtoRepository, times(1)).findByIdOptional(1L);
        verify(lanceRepository, times(1)).list("produto", produto);
    }

    @Test
    public void testObterHistoricoDeLances() {
        Produto produto = new ProdutoNotebook();
        produto.setId(1L);

        Lance lance = new Lance();
        lance.setProduto(produto);
        lance.setValor(2000.00);
        lance.setDataHora(LocalDateTime.now());

        when(lanceRepository.list("produto.id", 1L)).thenReturn(Collections.singletonList(lance));

        given()
          .when().get("/lances/produto/1")
          .then()
             .statusCode(200)
             .body("size()", greaterThanOrEqualTo(1))
             .body("[0].valor", equalTo(2000.00F));

        verify(lanceRepository, times(1)).list("produto.id", 1L);
    }
}
