package com.leilao.resource;



import com.leilao.entity.Lance;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class LanceResourceTest {

    @Test
    public void testRegistrarLanceValido() {
        Lance lance = new Lance();
        lance.setValor(2000.00);
        lance.setDataHora(LocalDateTime.now());

        given()
          .contentType(ContentType.JSON)
          .body(lance)
          .when().post("/lances")
          .then()
             .statusCode(201)  // Verifica se o lance foi criado
             .body("valor", equalTo(2000.00F));
    }

    @Test
    public void testRegistrarLanceInvalido() {
        // Vamos supor que o último lance registrado foi de 2000.00, então um lance menor seria inválido.
        Lance lance = new Lance();
        lance.setValor(1500.00);  // Valor inválido, menor que o último lance
        lance.setDataHora(LocalDateTime.now());

        given()
          .contentType(ContentType.JSON)
          .body(lance)
          .when().post("/lances")
          .then()
             .statusCode(400)  // Verifica se retorna 400 Bad Request
             .body("message", containsString("Lance inválido"));  // Mensagem de erro esperada
    }

    @Test
    public void testObterHistoricoDeLances() {
        given()
          .when().get("/lances/produto/1")  // Supondo que o produto com ID 1 tenha lances registrados
          .then()
             .statusCode(200)
             .body("size()", is(greaterThan(0)))  // Verifica que pelo menos um lance foi retornado
             .body("[0].valor", greaterThanOrEqualTo(2000.00F));  // Verifica se o valor do primeiro lance é correto
    }
}
