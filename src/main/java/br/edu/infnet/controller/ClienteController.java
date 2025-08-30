package br.edu.infnet.controller;

import br.edu.infnet.business.ClienteBusinessHandler;
import br.edu.infnet.dto.ClienteDto;
import br.edu.infnet.dto.ResponseDto;

import java.util.List;

public class ClienteController extends Controller<ClienteDto> {

    private final ClienteBusinessHandler business;

    public ClienteController() {
        this.business = new ClienteBusinessHandler();
    }

    @Override
    protected List<ClienteDto> findAll() {
        return business.findAll();
    }

    @Override
    protected ClienteDto findById(Long id) {
        return business.findById(id);
    }

    @Override
    protected ClienteDto create(ClienteDto clienteDto) {
        return business.create(clienteDto);
    }

    @Override
    protected ClienteDto update(ClienteDto clienteDto) {
        return business.update(clienteDto);
    }

    @Override
    protected boolean deleteById(Long id) {
        return business.delete(id);
    }

    public ResponseDto<ClienteDto> login(String email, String senha) {
        try {
            ClienteDto cliente = business.login(email, senha);
            if (cliente != null) {
                return new ResponseDto<>(true, "Login realizado com sucesso!", cliente);
            } else {
                return new ResponseDto<>(false, "Atenção - Credenciais inválidas!", null);
            }
        } catch (Exception e) {
            return new ResponseDto<>(false, "Erro no login: " + e.getMessage(), null);
        }
    }

    public ResponseDto<ClienteDto> findByEmail(String email) {
        try {
            ClienteDto cliente = business.findByEmail(email);
            if (cliente != null) {
                return new ResponseDto<>(true, "Cliente encontrado!", cliente);
            } else {
                return new ResponseDto<>(false, "Cliente não encontrado!", null);
            }
        } catch (Exception e) {
            return new ResponseDto<>(false, "Erro: " + e.getMessage(), null);
        }
    }

    public ResponseDto<ClienteDto> register(ClienteDto clienteDto) {
        return post(clienteDto);
    }
}
