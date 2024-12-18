package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreStep {
    Response response;
    private String urlBase;

    public void definirUrl(String url) {
        urlBase = url;
    }


    public void validacionRespuesta(int statusCode) {
        Assert.assertEquals("validacion de respuesta", statusCode, response.statusCode());
    }


    public void crearOrden(int id, int petId, int cantidad) {

        String body = "{\n" +
                "  \"id\":"+id+",\n" +
                "  \"petId\":"+petId+",\n" +
                "  \"quantity\":"+cantidad+",\n" +
                "  \"shipDate\": \"2024-12-17T23:17:49.487Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        response = RestAssured
                .given()
                .baseUri(urlBase)
                .header("Content-Type", "application/json")
                .relaxedHTTPSValidation()
                .body(body)
                .log().all()
                .post("/store/order")
                .then()
                .extract().response()
        ;
    }

    public void consultarOrden(int id) {
        response = RestAssured
                .given()
                .baseUri(urlBase)
                .header("Content-Type", "application/json")
                .relaxedHTTPSValidation()
                .log().all()
                .get("/store/order/"+id)
                .then()
                .extract().response();

        System.out.println("Respuesta JSON: " + response.getBody().jsonPath().prettify());
    }

    public void validarCuerpoDeRespuesta(int id, int petId) {

        int responseId = response.body().path("id");
        int responsePetId = response.body().path("petId");

        Assert.assertEquals("El id no coincide con el esperado", id, responseId);
        Assert.assertEquals("El petId no coincide con el esperado", petId, responsePetId);

        System.out.println("ID en respuesta: " + responseId);
        System.out.println("petId en respuesta: " + responsePetId);

    }
}
