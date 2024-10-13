package com.leilao.resource;

import com.leilao.entity.Leilao;
import com.leilao.repository.LeilaoRepository;
import com.leilao.service.LeilaoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@QuarkusTest
public class LeilaoResourceTest {

    @InjectMocks
    LeilaoService leilaoService;

    @Mock
    LeilaoRepository leilaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarLeilaoPorIdExistente() {
        Leilao leilao = new Leilao();
        leilao.setId(1L);
        leilao.setEndereco("Rua ABC, 123");
        leilao.setCidade("São Paulo");
        leilao.setEstado("SP");
        leilao.setDataVisita(LocalDateTime.now().plusDays(1));
        leilao.setDataLeilao(LocalDateTime.now().plusDays(2));
        leilao.setStatus("EM_ABERTO");

        when(leilaoRepository.findByIdOptional(1L)).thenReturn(Optional.of(leilao));

        given()
          .when().get("/leiloes/1")
          .then()
             .statusCode(200)
             .body("endereco", equalTo("Rua ABC, 123"))
             .body("cidade", equalTo("São Paulo"));

        // Verificar se o método de mocking foi chamado corretamente
        verify(leilaoRepository, times(1)).findByIdOptional(1L);
    }

    @Test
    public void testBuscarLeilaoPorIdInexistente() {
        when(leilaoRepository.findByIdOptional(1L)).thenReturn(Optional.empty());

        given()
          .when().get("/leiloes/1")
          .then()
             .statusCode(404);

        // Verificar se o método de mocking foi chamado corretamente
        verify(leilaoRepository, times(1)).findByIdOptional(1L);
    }


    @SuppressWarnings("static-access")
    @Test
    public void testCriarLeilaoEndpoint() {
        Leilao leilao = new Leilao();
        leilao.setEndereco("Rua ABC, 123");
        leilao.setCidade("São Paulo");
        leilao.setEstado("SP");
        leilao.setDataVisita(LocalDateTime.now().plusDays(1));
        leilao.setDataLeilao(LocalDateTime.now().plusDays(2));
        leilao.setStatus("EM_ABERTO");

        doNothing().when(leilaoRepository).persist(leilao);

        given()
          .contentType(ContentType.JSON)
          .body(leilao)
          .when().post("/leiloes")
          .then()
             .statusCode(201)
             .body("endereco", equalTo("Rua ABC, 123"))
             .body("cidade", equalTo("São Paulo"));

        // Verificar se o método de mocking foi chamado corretamente
        verify(leilaoRepository, times(1)).persist(leilao);
    }

    private Mockito doNothing() {
   
      throw new UnsupportedOperationException("Unimplemented method 'doNothing'");
    }

    @Test
    public void testListarLeiloesEndpoint() {
        Leilao leilao = new Leilao();
        leilao.setId(1L);
        leilao.setEndereco("Rua ABC, 123");
        leilao.setCidade("São Paulo");
        leilao.setEstado("SP");
        leilao.setDataVisita(LocalDateTime.now().plusDays(1));
        leilao.setDataLeilao(LocalDateTime.now().plusDays(2));
        leilao.setStatus("EM_ABERTO");

        when(leilaoRepository.listAll()).thenReturn(Collections.singletonList(leilao));

        given()
          .when().get("/leiloes")
          .then()
             .statusCode(200)
             .body("size()", is(greaterThan(0)))
             .body("[0].endereco", equalTo("Rua ABC, 123"));

        // Verificar se o método de mocking foi chamado corretamente
        verify(leilaoRepository, times(1)).listAll();
    }
}
