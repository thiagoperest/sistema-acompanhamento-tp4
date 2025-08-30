package br.edu.infnet;

import br.edu.infnet.controller.ClienteController;
import br.edu.infnet.dto.ClienteDto;
import br.edu.infnet.dto.ResponseDto;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static ClienteController clienteController;
    private static ClienteDto clienteLogado;

    public static void main(String[] args) {
        clienteController = new ClienteController();

        criarDiretorioData();

        criarDadosExemplo();

        System.out.println("=================================================");
        System.out.println("    SISTEMA DE ACOMPANHAMENTO DE PEDIDOS");
        System.out.println("           Instituto Infnet - PB TP4");
        System.out.println("=================================================");

        boolean executando = true;
        while (executando) {
            if (clienteLogado == null) {
                executando = exibirMenuLogin();
            } else {
                executando = exibirMenuPrincipal();
            }
        }

        System.out.println("\nSistema encerrado!");
        scanner.close();
    }

    private static void criarDiretorioData() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            System.out.println("Diretório 'data' criado.");
        }
    }

    private static void criarDadosExemplo() {
        ResponseDto<List<ClienteDto>> response = clienteController.get();

        if (response.isSuccess() && response.getData().isEmpty()) {
            System.out.println("Criando dados de exemplo...");

            // Cliente de exemplo
            ClienteDto cliente = new ClienteDto(
                    "João Silva",
                    "joao@email.com",
                    "(11) 99999-9999",
                    "Rua das Flores, 123, São Paulo, SP"
            );

            ResponseDto<ClienteDto> resultado = clienteController.register(cliente);
            if (resultado.isSuccess()) {
                System.out.println("Cliente de exemplo criado!");
                System.out.println("Login: joao@email.com | Senha: 123456");
            }
        }
    }

    private static boolean exibirMenuLogin() {
        System.out.println("\n=== MENU DE ACESSO ===");
        System.out.println("1. Fazer Login");
        System.out.println("2. Registrar Novo Cliente");
        System.out.println("3. Listar Clientes (Debug)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;
                case 2:
                    registrarCliente();
                    break;
                case 3:
                    listarClientesDebug();
                    break;
                case 0:
                    return false;
                default:
                    System.out.println("Opção inválida!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um número válido!");
            scanner.nextLine();
        }

        return true;
    }

    private static boolean exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("Bem-vindo(a), " + clienteLogado.getNome() + "!");
        System.out.println();
        System.out.println("1. Visualizar Meu Perfil");
        System.out.println("2. Atualizar Meus Dados");
        System.out.println("3. Listar Todos os Clientes");
        System.out.println("4. Buscar Cliente por Email");
        System.out.println("5. Deletar Cliente");
        System.out.println("0. Logout");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    visualizarPerfil();
                    break;
                case 2:
                    atualizarDados();
                    break;
                case 3:
                    listarTodosClientes();
                    break;
                case 4:
                    buscarClientePorEmail();
                    break;
                case 5:
                    deletarCliente();
                    break;
                case 0:
                    clienteLogado = null;
                    System.out.println("Logout realizado com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um número válido!");
            scanner.nextLine();
        }

        return true;
    }

    private static void realizarLogin() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();
        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        ResponseDto<ClienteDto> response = clienteController.login(email, senha);

        if (response.isSuccess()) {
            clienteLogado = response.getData();
            System.out.println("\nLogin realizado com sucesso!");
            System.out.println("Bem-vindo(a), " + clienteLogado.getNome());
        } else {
            System.out.println("\n- " + response.getMessage());
            System.out.println("\nRegistre um cliente primeiro ou use o de exemplo:");
            System.out.println("Email: joao@email.com | Senha: 123456");
        }
    }

    private static void registrarCliente() {
        System.out.println("\n=== REGISTRAR NOVO CLIENTE ===");

        System.out.print("Nome completo: ");
        String nome = scanner.nextLine().trim();

        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();

        System.out.print("Telefone (opcional): ");
        String telefone = scanner.nextLine().trim();

        System.out.print("Endereço (opcional): ");
        String endereco = scanner.nextLine().trim();

        ClienteDto clienteDto = new ClienteDto(nome, email, telefone, endereco);

        ResponseDto<ClienteDto> response = clienteController.register(clienteDto);

        if (response.isSuccess()) {
            System.out.println("\nCliente registrado com sucesso!");
            System.out.println("ID: " + response.getData().getId());
            System.out.println("Senha padrão: 123456");
        } else {
            System.out.println("\nErro: " + response.getMessage());
        }
    }

    private static void visualizarPerfil() {
        System.out.println("\n=== MEU PERFIL ===");
        System.out.println("ID: " + clienteLogado.getId());
        System.out.println("Nome: " + clienteLogado.getNome());
        System.out.println("E-mail: " + clienteLogado.getEmail());
        System.out.println("Telefone: " + (clienteLogado.getTelefone() != null ? clienteLogado.getTelefone() : "Não informado"));
        System.out.println("Endereço: " + (clienteLogado.getEndereco() != null ? clienteLogado.getEndereco() : "Não informado"));
        System.out.println("Cadastrado em: " + (clienteLogado.getDataCadastro() != null ? clienteLogado.getDataCadastro() : "N/A"));
    }

    private static void atualizarDados() {
        System.out.println("\n=== ATUALIZAR DADOS ===");
        System.out.println("Deixe em branco para manter o valor atual");

        System.out.print("Novo nome (" + clienteLogado.getNome() + "): ");
        String nome = scanner.nextLine().trim();
        if (!nome.isEmpty()) {
            clienteLogado.setNome(nome);
        }

        System.out.print("Novo telefone (" + (clienteLogado.getTelefone() != null ? clienteLogado.getTelefone() : "N/A") + "): ");
        String telefone = scanner.nextLine().trim();
        if (!telefone.isEmpty()) {
            clienteLogado.setTelefone(telefone);
        }

        System.out.print("Novo endereço (" + (clienteLogado.getEndereco() != null ? clienteLogado.getEndereco() : "N/A") + "): ");
        String endereco = scanner.nextLine().trim();
        if (!endereco.isEmpty()) {
            clienteLogado.setEndereco(endereco);
        }

        ResponseDto<ClienteDto> response = clienteController.put(clienteLogado);

        if (response.isSuccess()) {
            clienteLogado = response.getData();
            System.out.println("\nDados atualizados com sucesso!");
        } else {
            System.out.println("\nErro: " + response.getMessage());
        }
    }

    private static void listarTodosClientes() {
        System.out.println("\n=== TODOS OS CLIENTES ===");
        ResponseDto<List<ClienteDto>> response = clienteController.get();

        if (response.isSuccess()) {
            List<ClienteDto> clientes = response.getData();

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
            } else {
                System.out.println("Total de clientes: " + clientes.size());
                System.out.println();
                for (ClienteDto cliente : clientes) {
                    System.out.printf("ID: %d | Nome: %s | E-mail: %s%n",
                            cliente.getId(), cliente.getNome(), cliente.getEmail());
                }
            }
        } else {
            System.out.println("Erro: " + response.getMessage());
        }
    }

    private static void buscarClientePorEmail() {
        System.out.println("\n=== BUSCAR CLIENTE POR EMAIL ===");
        System.out.print("Digite o e-mail: ");
        String email = scanner.nextLine().trim();

        ResponseDto<ClienteDto> response = clienteController.findByEmail(email);

        if (response.isSuccess()) {
            ClienteDto cliente = response.getData();
            System.out.println("\nCliente localizado:");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("E-mail: " + cliente.getEmail());
            System.out.println("Telefone: " + (cliente.getTelefone() != null ? cliente.getTelefone() : "N/A"));
        } else {
            System.out.println("\n✗ " + response.getMessage());
        }
    }

    private static void deletarCliente() {
        System.out.println("\n=== DELETAR CLIENTE ===");

        ResponseDto<List<ClienteDto>> listaResponse = clienteController.get();

        if (!listaResponse.isSuccess() || listaResponse.getData().isEmpty()) {
            System.out.println("Nenhum cliente disponível para deletar.");
            return;
        }

        System.out.println("Clientes disponíveis:");
        for (ClienteDto cliente : listaResponse.getData()) {
            System.out.printf("ID: %d | Nome: %s | E-mail: %s%n",
                    cliente.getId(), cliente.getNome(), cliente.getEmail());
        }

        System.out.print("\nDigite o ID do cliente a ser deletado: ");

        try {
            Long id = scanner.nextLong();
            scanner.nextLine();

            if (id.equals(clienteLogado.getId())) {
                System.out.print("Você está tentando deletar sua própria conta. Confirma? (S/N): ");
                String confirmacao = scanner.nextLine().trim().toLowerCase();

                if (!confirmacao.equals("s") && !confirmacao.equals("sim")) {
                    System.out.println("Operação cancelada.");
                    return;
                }
            }

            System.out.print("Tem certeza que deseja deletar este cliente? (S/N): ");
            String confirmacao = scanner.nextLine().trim().toLowerCase();

            if (confirmacao.equals("s") || confirmacao.equals("sim")) {
                ResponseDto<Boolean> response = clienteController.delete(id);

                if (response.isSuccess() && response.getData()) {
                    System.out.println("Cliente deletado com sucesso!");

                    if (id.equals(clienteLogado.getId())) {
                        System.out.println("Sua conta foi deletada. Fazendo logout...");
                        clienteLogado = null;
                    }
                } else {
                    System.out.println("Erro: " + response.getMessage());
                }
            } else {
                System.out.println("Operação cancelada.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um ID válido!");
            scanner.nextLine();
        }
    }

    private static void listarClientesDebug() {
        System.out.println("\n=== DEBUG: LISTA DE CLIENTES ===");
        ResponseDto<List<ClienteDto>> response = clienteController.get();

        System.out.println("Response Status: " + (response.isSuccess() ? "SUCESSO" : "ERRO"));
        System.out.println("Message: " + response.getMessage());

        if (response.isSuccess() && response.getData() != null) {
            List<ClienteDto> clientes = response.getData();
            System.out.println("Quantidade: " + clientes.size());

            for (ClienteDto cliente : clientes) {
                System.out.println("- " + cliente);
            }
        }
    }
}
