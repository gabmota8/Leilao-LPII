---
openapi: 3.0.3
info:
  title: sistema-leilao API
  version: 1.0.0-SNAPSHOT
paths:
  /lances:
    post:
      tags:
      - Lance Controller
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/Lance"
      responses:
        "200":
          description: OK
  /lances/produto/{produtoId}:
    get:
      tags:
      - Lance Controller
      parameters:
      - name: produtoId
        in: path
        required: true
        schema:
          format: int64
          type: integer
      responses:
        "200":
          description: OK
  /leiloes/{id}:
    get:
      tags:
      - Leilao Controller
      parameters:
      - name: id
        in: path
        required: true
        schema:
          format: int64
          type: integer
      responses:
        "200":
          description: OK
  /produtos:
    get:
      tags:
      - Produto Controller
      responses:
        "200":
          description: OK
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: "#/components/schemas/Produto"
    post:
      tags:
      - Produto Controller
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/Produto"
      responses:
        "200":
          description: OK
  /produtos/{id}:
    get:
      tags:
      - Produto Controller
      parameters:
      - name: id
        in: path
        required: true
        schema:
          format: int64
          type: integer
      responses:
        "200":
          description: OK
    put:
      tags:
      - Produto Controller
      parameters:
      - name: id
        in: path
        required: true
        schema:
          format: int64
          type: integer
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/Produto"
      responses:
        "200":
          description: OK
    delete:
      tags:
      - Produto Controller
      parameters:
      - name: id
        in: path
        required: true
        schema:
          format: int64
          type: integer
      responses:
        "200":
          description: OK
components:
  schemas:
    Cliente:
      type: object
      properties:
        id:
          format: int64
          type: integer
        nome:
          type: string
        email:
          type: string
        cpf:
          type: string
    InstituicaoFinanceira:
      type: object
      properties:
        id:
          format: int64
          type: integer
        nome:
          type: string
        cnpj:
          type: string
    Lance:
      type: object
      properties:
        id:
          format: int64
          type: integer
        produto:
          $ref: "#/components/schemas/Produto"
        cliente:
          $ref: "#/components/schemas/Cliente"
        valor:
          format: double
          type: number
        dataHora:
          $ref: "#/components/schemas/LocalDateTime"
    Leilao:
      type: object
      properties:
        id:
          format: int64
          type: integer
        endereco:
          type: string
        cidade:
          type: string
        estado:
          type: string
        dataVisita:
          $ref: "#/components/schemas/LocalDateTime"
        dataLeilao:
          $ref: "#/components/schemas/LocalDateTime"
        produtos:
          type: array
          items:
            $ref: "#/components/schemas/Produto"
        instituicaoFinanceira:
          $ref: "#/components/schemas/InstituicaoFinanceira"
        status:
          type: string
    LocalDateTime:
      format: date-time
      type: string
      example: 2022-03-10T12:15:50
    Produto:
      type: object
      properties:
        id:
          format: int64
          type: integer
        nome:
          type: string
        precoInicial:
          format: double
          type: number
        leilao:
          $ref: "#/components/schemas/Leilao"
        vendido:
          type: boolean
