package com.leilao.resource;


import com.leilao.entity.*;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ProdutoResourceTest {

    @Test
    public void testCriarCarroProduto() {
        Produto carro = new Veiculo();
        carro.setNome("Carro - Ford Fiesta");
        carro.setPrecoInicial(25000.00);
        carro.setVendido(false);

        given()
          .contentType(ContentType.JSON)
          .body(carro)
          .when().post("/produtos")
          .then()
             .statusCode(201)
             .body("nome", equalTo("Carro - Ford Fiesta"))
             .body("precoInicial", equalTo(25000.00F));
    }

    @Test
    public void testCriarHubProduto() {
        Produto hub = new ProdutoHub();
        hub.setNome("Hub USB 3.0");
        hub.setPrecoInicial(100.00);
        hub.setVendido(false);

        given()
          .contentType(ContentType.JSON)
          .body(hub)
          .when().post("/produtos")
          .then()
             .statusCode(201)
             .body("nome", equalTo("Hub USB 3.0"))
             .body("precoInicial", equalTo(100.00F));
    }

    @Test
    public void testCriarNotebookProduto() {
        Produto notebook = new ProdutoNotebook();
        notebook.setNome("Notebook Dell XPS 13");
        notebook.setPrecoInicial(4500.00);
        notebook.setVendido(false);

        given()
          .contentType(ContentType.JSON)
          .body(notebook)
          .when().post("/produtos")
          .then()
             .statusCode(201)
             .body("nome", equalTo("Notebook Dell XPS 13"))
             .body("precoInicial", equalTo(4500.00F));
    }

    @Test
    public void testCriarMonitorProduto() {
        Produto monitor = new ProdutoMonitor();
        monitor.setNome("Monitor LG 24\"");
        monitor.setPrecoInicial(800.00);
        monitor.setVendido(false);

        given()
          .contentType(ContentType.JSON)
          .body(monitor)
          .when().post("/produtos")
          .then()
             .statusCode(201)
             .body("nome", equalTo("Monitor LG 24\""))
             .body("precoInicial", equalTo(800.00F));
    }

    @Test
    public void testCriarSwitchProduto() {
        Produto switchNetwork = new ProdutoSwitch();
        switchNetwork.setNome("Switch Cisco 24 portas");
        switchNetwork.setPrecoInicial(2000.00);
        switchNetwork.setVendido(false);

        given()
          .contentType(ContentType.JSON)
          .body(switchNetwork)
          .when().post("/produtos")
          .then()
             .statusCode(201)
             .body("nome", equalTo("Switch Cisco 24 portas"))
             .body("precoInicial", equalTo(2000.00F));
    }

    @Test
    public void testCriarRoteadorProduto() {
        Produto roteador = new ProdutoRoteador();
        roteador.setNome("Roteador TP-Link AC1200");
        roteador.setPrecoInicial(300.00);
        roteador.setVendido(false);

        given()
          .contentType(ContentType.JSON)
          .body(roteador)
          .when().post("/produtos")
          .then()
             .statusCode(201)
             .body("nome", equalTo("Roteador TP-Link AC1200"))
             .body("precoInicial", equalTo(300.00F));
    }

    @Test
    public void testListarProdutosEndpoint() {
        given()
          .when().get("/produtos")
          .then()
             .statusCode(200)
             .body("size()", is(greaterThanOrEqualTo(6)));  // Garante que ao menos 6 produtos foram criados
    }

    @Test
    public void testBuscarProdutoPorIdExistente() {
        given()
          .when().get("/produtos/1")  // Supondo que o produto com ID 1 exista
          .then()
             .statusCode(200)
             .body("id", equalTo(1));
    }

    @Test
    public void testAtualizarProduto() {
        Produto produtoAtualizado = new Veiculo();
        produtoAtualizado.setNome("Carro - Ford Fiesta 2019");
        produtoAtualizado.setPrecoInicial(27000.00);
        produtoAtualizado.setVendido(false);

        given()
          .contentType(ContentType.JSON)
          .body(produtoAtualizado)
          .when().put("/produtos/1")
          .then()
             .statusCode(200)
             .body("nome", equalTo("Carro - Ford Fiesta 2019"))
             .body("precoInicial", equalTo(27000.00F));
    }
}

    
   
