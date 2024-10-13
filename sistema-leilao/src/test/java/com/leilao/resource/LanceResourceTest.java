package com.leilao.resource;

import com.leilao.entity.Lance;
import com.leilao.entity.ProdutoNotebook; // Importando a subclasse concreta
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
import static org.hamcrest.Matchers.*;
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
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarLanceValido() {
        ProdutoNotebook produto = new ProdutoNotebook(); // Usando ProdutoNotebook
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

        // Verificar se os métodos de mocking foram chamados corretamente
        verify(produtoRepository, times(1)).findByIdOptional(1L);
        verify(lanceRepository, times(1)).persist(lance);
    }

    @Test
    public void testRegistrarLanceInvalido() {
        ProdutoNotebook produto = new ProdutoNotebook(); // Usando ProdutoNotebook
        produto.setId(1L);

        Lance lance = new Lance();
        lance.setProduto(produto);
        lance.setValor(1500.00);  // Valor inválido, menor que o último lance
        lance.setDataHora(LocalDateTime.now());

        when(produtoRepository.findByIdOptional(1L)).thenReturn(Optional.of(produto));
        when(lanceRepository.list("produto", produto)).thenReturn(Collections.singletonList(lance));

        given()
          .contentType(ContentType.JSON)
          .body(lance)
          .when().post("/lances")
          .then()
             .statusCode(400)  // Verifica se retorna 400 Bad Request
             .body("message", containsString("Lance inválido"));  // Mensagem de erro esperada

        // Verificar se os métodos de mocking foram chamados corretamente
        verify(produtoRepository, times(1)).findByIdOptional(1L);
        verify(lanceRepository, times(1)).list("produto", produto);
    }

    @Test
    public void testObterHistoricoDeLances() {
        ProdutoNotebook produto = new ProdutoNotebook(); // Usando ProdutoNotebook
        produto.setId(1L);

        Lance lance = new Lance();
        lance.setProduto(produto);
        lance.setValor(2000.00);
        lance.setDataHora(LocalDateTime.now());

        when(lanceRepository.list("produto.id", 1L)).thenReturn(Collections.singletonList(lance));

        given()
          .when().get("/lances/produto/1")  // Supondo que o produto com ID 1 tenha lances registrados
          .then()
             .statusCode(200)
             .body("size()", is(greaterThan(0)))  // Verifica que pelo menos um lance foi retornado
             .body("[0].valor", greaterThanOrEqualTo(2000.00F));  // Verifica se o valor do primeiro lance é correto

        // Verificar se o método de mocking foi chamado corretamente
        verify(lanceRepository, times(1)).list("produto.id", 1L);
    }
}
