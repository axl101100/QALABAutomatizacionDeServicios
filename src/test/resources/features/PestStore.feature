@PestStore
Feature:  PetStore Api

  @crearOrden
  Scenario Outline: Crear orden en PestStore
    Given la url es "https://petstore.swagger.io/v2"
    When  creo la mascota con ID <id> y PetID <petId> y quantity <cantidad>
    Then valido el codigo de respuesta sea 200
    And valido que el cuerpo de la respuesta contiene el id <id> y petId <petId>
    Examples:
      | id  | petId | cantidad |
      | 51  | 30   |  6        |
      | 60  | 40    | 8        |


  @consultaOrden
  Scenario Outline: Consultar Order creada
    Given la url es "https://petstore.swagger.io/v2"
    When consulto la orden con id <id>
    Then valido el codigo de respuesta sea 200
    And valido que el cuerpo de la respuesta contiene el id <id> y petId <petId>
    Examples:
      | id  | petId |
      | 51   | 30    |
      | 60   | 40    |