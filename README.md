# Sistema de Acompanhamento de Pedidos - INFNET - PB TP4

![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.8+-green.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)
![Status](https://img.shields.io/badge/Status-Ativo-success.svg)

Sistema desenvolvido como projeto prático para gerenciamento de clientes com operações CRUD completas, implementando conceitos de orientação a objetos, persistência em arquivos CSV e arquitetura em camadas.

## Sobre o Projeto

Este projeto foi desenvolvido como parte do **TP4 - Projeto de Bloco: Desenvolvimento Back-end** do Instituto Infnet, implementando um sistema de gerenciamento de clientes com funcionalidades completas de cadastro, autenticação, visualização, atualização e exclusão de dados.

**Instituto Infnet** - Projeto de Bloco  
**Disciplina:** Desenvolvimento Back-end  
**Aluno:** Thiago Teodoro Peres

## Arquitetura

O sistema implementa uma arquitetura em camadas seguindo os padrões MVC e Clean Architecture:

```
Presentation Layer (Main.java)
        ↓
Controller Layer (ClienteController)
        ↓
Business Layer (ClienteBusinessHandler)
        ↓
Model Layer (ClienteModel)
        ↓
Data Layer (CSV Files)
```

O sistema foi modelado seguindo princípios de orientação a objetos, com separação de responsabilidades entre as camadas de apresentação, controle, negócio e persistência.

## Funcionalidades Implementadas

- **Sistema de Login/Logout** - Autenticação de clientes via email e senha
- **Gestão de Clientes** - CRUD completo com validações
- **Visualização de Perfis** - Dados pessoais e histórico de cadastro
- **Atualização de Contato** - Modificação de dados dos clientes
- **Validações de Negócio** - Email único, telefone e campos obrigatórios e opcionais
- **Persistência em CSV** - Armazenamento automático com criação de arquivos no formato CSV
- **Tratamento de Erros** - Captura e exibição adequada de exceções

## Como Executar

### Pré-requisitos
- Java 8 ou superior
- Maven 3.8 ou superior (opcional)

### Execução

1. **Clone e compile:**
   ```bash
   git clone https://github.com/thiagoperest/sistema-acompanhamento-tp4.git
   cd sistema-acompanhamento-tp4
   ```

2. **Execute a aplicação:**
   ```bash
   java -cp target/classes br.edu.infnet.Main
   ```

### Login de Teste
```
Email: joao@email.com
Senha: 123456
```

## Estrutura do Projeto

```
src/main/java/br/edu/infnet/
├── Main.java                          # Interface de linha de comando
├── controller/
│   ├── Controller.java                # Classe base para controllers
│   └── ClienteController.java         # Controller específico de clientes
├── business/
│   ├── BusinessHandler.java           # Classe base para regras de negócio
│   └── ClienteBusinessHandler.java    # Lógica de negócio de clientes
├── model/
│   ├── Model.java                     # Classe base para persistência
│   └── ClienteModel.java              # Model específico de clientes
└── dto/
    ├── ClienteDto.java                # DTO para transferência de dados
    ├── ResponseDto.java               # Padronização de respostas
    ├── PedidoDto.java                 # DTO para pedidos (futuro)
    └── StatusPedidoDto.java           # DTO para status (futuro)

data/                                  # Diretório para arquivos CSV
└── clientes.csv                       # Dados dos clientes persistidos
```

## Arquivos CSV

O sistema utiliza arquivo CSV para persistência dos dados:

**clientes.csv**
```csv
id;createdAt;updatedAt;nome;email;senha;telefone;endereco
1;2025-08-30 10:30:00;2025-08-30 10:30:00;João Silva;joao@email.com;123456;(11) 99999-9999;Rua das Flores, 123, São Paulo, SP
```

## Débito Técnico

### Funcionalidades Futuras
O sistema de **pedidos e status** não foi implementado nesta versão e está planejado para o próximo TP.

## Casos de Uso Atendidos

- **UC01** - Fazer Login no Sistema
- **UC02** - Registrar Novo Cliente
- **UC03** - Visualizar Meu Perfil
- **UC04** - Atualizar Meus Dados
- **UC05** - Listar Todos os Clientes
- **UC06** - Buscar Cliente por Email
- **UC07** - Deletar Cliente
- **UC08** - Fazer Logout
- **UC09** - Listar Clientes (Debug)

## Tecnologias Utilizadas

- **Java 17** - Linguagem de programação principal
- **Arquitetura MVC** - Separação de responsabilidades
- **CSV** - Persistência simples de dados
- **Padrões de Design** - Template Method, Strategy, DTO, Repository

## Contato

**Thiago Teodoro Peres**  
Email: thiago.peres@al.infnet.edu.br  
Instituto Infnet - Desenvolvimento Back-end

---

**Projeto desenvolvido para o Instituto Infnet - TP4**