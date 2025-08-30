package br.edu.infnet.business;

import br.edu.infnet.dto.ClienteDto;
import br.edu.infnet.model.ClienteModel;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteBusinessHandler extends BusinessHandler<ClienteDto> {

    private final ClienteModel clienteModel;

    public ClienteBusinessHandler() {
        this.clienteModel = new ClienteModel();
    }

    @Override
    public ClienteDto create(ClienteDto clienteDto) {
        validateBusinessRules(clienteDto);

        ClienteModel modelo = convertDtoToModel(clienteDto);

        if (modelo.getSenha() == null || modelo.getSenha().isEmpty()) {
            modelo.setSenha("123456");
        }

        modelo.save(modelo);

        return convertModelToDto(modelo);
    }

    @Override
    public ClienteDto update(ClienteDto clienteDto) {
        validateBusinessRules(clienteDto);
        validateId(clienteDto.getId(), "ID do Cliente");

        ClienteModel existente = clienteModel.findById(clienteDto.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        ClienteModel modelo = convertDtoToModel(clienteDto);
        modelo.setSenha(existente.getSenha());
        modelo.update(modelo);

        return convertModelToDto(modelo);
    }

    @Override
    public ClienteDto findById(Long id) {
        validateId(id, "ID do Cliente");

        ClienteModel modelo = clienteModel.findById(id);
        return modelo != null ? convertModelToDto(modelo) : null;
    }

    @Override
    public List<ClienteDto> findAll() {
        List<ClienteModel> modelos = clienteModel.findAll();
        return modelos.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Long id) {
        try {
            validateId(id, "ID do Cliente");
            clienteModel.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void validateBusinessRules(ClienteDto clienteDto) {
        validateNotNull(clienteDto, "Cliente");
        validateNotEmpty(clienteDto.getNome(), "Nome");
        validateNotEmpty(clienteDto.getEmail(), "E-mail");
        validateEmail(clienteDto.getEmail());

        ClienteModel existente = clienteModel.findByEmail(clienteDto.getEmail());
        if (existente != null && !existente.getId().equals(clienteDto.getId())) {
            throw new IllegalArgumentException("E-mail já cadastrado para outro cliente");
        }

        if (clienteDto.getTelefone() != null && !clienteDto.getTelefone().trim().isEmpty()) {
            String telefone = clienteDto.getTelefone().replaceAll("[^0-9]", "");
            if (telefone.length() < 10 || telefone.length() > 11) {
                throw new IllegalArgumentException("Telefone deve ter 10 ou 11 dígitos");
            }
        }
    }

    public ClienteDto login(String email, String senha) {
        validateNotEmpty(email, "E-mail");
        validateNotEmpty(senha, "Senha");
        validateEmail(email);

        List<ClienteModel> clientes = clienteModel.findAll();

        ClienteModel cliente = clientes.stream()
                .filter(c -> c.autenticar(email, senha))
                .findFirst()
                .orElse(null);

        return cliente != null ? convertModelToDto(cliente) : null;
    }

    public ClienteDto findByEmail(String email) {
        validateNotEmpty(email, "E-mail");
        validateEmail(email);

        ClienteModel modelo = clienteModel.findByEmail(email);
        return modelo != null ? convertModelToDto(modelo) : null;
    }

    private ClienteDto convertModelToDto(ClienteModel modelo) {
        ClienteDto dto = new ClienteDto();
        dto.setId(modelo.getId());
        dto.setNome(modelo.getNome());
        dto.setEmail(modelo.getEmail());
        dto.setTelefone(modelo.getTelefone());
        dto.setEndereco(modelo.getEndereco());
        dto.setDataCadastro(modelo.getCreatedAt());
        return dto;
    }

    private ClienteModel convertDtoToModel(ClienteDto dto) {
        ClienteModel modelo = new ClienteModel();
        modelo.setId(dto.getId());
        modelo.setNome(dto.getNome());
        modelo.setEmail(dto.getEmail());
        modelo.setTelefone(dto.getTelefone());
        modelo.setEndereco(dto.getEndereco());
        return modelo;
    }
}
