# API Movexa

API REST para gerenciamento de usuários, robôs AGV e ordens de coleta.

## Sumário

- [Tecnologias](#tecnologias)
- [Como rodar o projeto](#como-rodar-o-projeto)
- [Entidades](#entidades)
- [Endpoints e exemplos de uso](#endpoints-e-exemplos-de-uso)
  - [Usuários](#usuários)
  - [Robôs AGV](#robôs-agv)
  - [Coletas (Pedidos)](#coletas-pedidos)
- [Tratamento de erros](#tratamento-de-erros)
- [Banco de dados H2](#banco-de-dados-h2)
- [Organização do projeto](#organização-do-projeto)

---

## Tecnologias

- Java 17
- Spring Boot 3.x
- Maven
- H2 Database (modo arquivo)
- Spring Data JPA
- Spring Security (desabilitado por padrão)

---

## Como rodar o projeto

1. Clone o repositório.
2. Execute `mvn spring-boot:run` ou rode a classe `ApiApplication`.
3. Acesse a API em `http://localhost:8080`.

---

## Entidades

### Usuário

- `id`: Long
- `email`: String
- `senhaHash`: String (armazenada criptografada)
- `papel`: String

### Robô AGV

- `id`: Long
- `identificador`: String
- `consumoBateria`: Double
- `nivelBateria`: Double
- `tempoEntreManutencoes`: Integer

### Pedido (Coleta)

- `id`: Long
- `origem`: String
- `destino`: String
- `data`: LocalDate
- `status`: String
- `usuarioId`: Long
- `finalizadoPor`: Long
- `observacoes`: String

---

## Endpoints e exemplos de uso

### Usuários

#### Listar usuários

- **GET** `/usuarios`
- **Resposta:**
```json
[
  {
    "email": "user@exemplo.com",
    "senha": "hash",
    "papel": "ADMIN"
  }
]
```

#### Criar usuário

- **POST** `/usuarios`
- **Body:**
```json
{
  "email": "novo@exemplo.com",
  "senha": "123456",
  "papel": "USER"
}
```
- **Resposta:** Usuário criado (sem senha em texto puro).

#### Atualizar usuário

- **PUT** `/usuarios/{id}`
- **Body:**
```json
{
  "email": "atualizado@exemplo.com",
  "senha": "novaSenha",
  "papel": "USER"
}
```

#### Remover usuário

- **DELETE** `/usuarios/{id}`

---

### Robôs AGV

#### Listar robôs

- **GET** `/robos-agv`
- **Resposta:**
```json
[
  {
    "id": 1,
    "identificador": "AGV-001",
    "consumoBateria": 2.5,
    "nivelBateria": 85.0,
    "tempoEntreManutencoes": 120
  }
]
```

#### Criar robô

- **POST** `/robos-agv`
- **Body:**
```json
{
  "identificador": "AGV-002",
  "consumoBateria": 3.1,
  "nivelBateria": 90.0,
  "tempoEntreManutencoes": 100
}
```

#### Atualizar robô

- **PUT** `/robos-agv/{id}`
- **Body:** (mesmo formato do POST)

#### Remover robô

- **DELETE** `/robos-agv/{id}`

---

### Coletas (Pedidos)

#### Criar coleta

- **POST** `/coletas`
- **Body:**
```json
{
  "origem": "Depósito A",
  "destino": "Setor B",
  "usuarioId": 1
}
```
- **Resposta:** Pedido criado com status `pendente`.

#### Concluir coleta

- **POST** `/coletas/{id}/concluir`
- **Body:**
```json
{
  "finalizadoPor": 2,
  "observacoes": "Entrega realizada sem problemas"
}
```
- **Resposta:** Pedido atualizado com status `concluido`.

---

## Tratamento de erros

- Validações retornam HTTP 400 com detalhes dos campos inválidos.
- Erros inesperados retornam HTTP 500 com mensagem.

---

## Banco de dados H2

- Console disponível em: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/sistemadb`
- Usuário: `sa` | Senha: `password`

---

## Organização do projeto

- `controller`: Endpoints REST
- `service`: Regras de negócio
- `repository`: Persistência JPA
- `model`: Entidades
- `dto`: Objetos de transferência de dados
- `config`: Configurações (ex: segurança)
- `exception`: Tratamento global de erros

---

## Observações

- Não há autenticação JWT implementada por padrão.
- Senhas de usuários são armazenadas criptografadas.
- Para integração, utilize os exemplos de JSON acima em ferramentas como Postman ou no frontend.

---