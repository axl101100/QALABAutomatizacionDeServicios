package com.nttdata.glue;

import com.nttdata.steps.PetStoreStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ro.Si;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

@Slf4j
public class PetStoreStepDef {

    @Steps
    PetStoreStep tienda;


    @Given("la url es {string}")
    public void laUrlEs(String url) {
        tienda.definirUrl(url);

    }

    @Then("valido el codigo de respuesta sea {int}")
    public void validoElCodigoDeRespuestaSea(int statusCode) {

        tienda.validacionRespuesta(statusCode);

    }

    @When("creo la mascota con ID {int} y PetID {int} y quantity {int}")
    public void creoLaMascotaConIDIdYPetIDPetIdYQuantityCantidad(int id, int petId, int cantidad) {
        tienda.crearOrden(id,petId,cantidad);
    }

    @When("consulto la orden con id {int}")
    public void consultoLaOrdenConId(int id) {
        tienda.consultarOrden(id);
    }

    @And("valido que el cuerpo de la respuesta contiene el id {int} y petId {int}")
    public void validoQueElCuerpoDeLaRespuestaContieneElIdYPetId(int id, int petId) {
        tienda.validarCuerpoDeRespuesta(id, petId);
    }
}
