package br.edu.infnet.controller;

import br.edu.infnet.dto.ResponseDto;
import java.util.List;

public abstract class Controller<T> {

    public ResponseDto<List<T>> get() {
        try {
            List<T> items = findAll();
            return new ResponseDto<>(true, "Sucesso", items);
        } catch (Exception e) {
            return new ResponseDto<>(false, "Erro: " + e.getMessage(), null);
        }
    }

    public ResponseDto<T> get(Long id) {
        try {
            validateId(id);
            T item = findById(id);
            if (item != null) {
                return new ResponseDto<>(true, "Sucesso", item);
            } else {
                return new ResponseDto<>(false, "Item não encontrado", null);
            }
        } catch (Exception e) {
            return new ResponseDto<>(false, "Erro: " + e.getMessage(), null);
        }
    }

    public ResponseDto<T> post(T object) {
        try {
            validateObject(object);
            T created = create(object);
            return new ResponseDto<>(true, "Criado com sucesso", created);
        } catch (Exception e) {
            return new ResponseDto<>(false, "Erro: " + e.getMessage(), null);
        }
    }

    public ResponseDto<T> put(T object) {
        try {
            validateObject(object);
            T updated = update(object);
            return new ResponseDto<>(true, "Atualizado com sucesso", updated);
        } catch (Exception e) {
            return new ResponseDto<>(false, "Erro: " + e.getMessage(), null);
        }
    }

    public ResponseDto<Boolean> delete(Long id) {
        try {
            validateId(id);
            boolean deleted = deleteById(id);
            return new ResponseDto<>(deleted,
                    deleted ? "Deletado com sucesso" : "Item não encontrado",
                    deleted);
        } catch (Exception e) {
            return new ResponseDto<>(false, "Erro: " + e.getMessage(), false);
        }
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser um valor válido");
        }
    }

    private void validateObject(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Objeto não pode ser nulo");
        }
    }

    protected abstract List<T> findAll();
    protected abstract T findById(Long id);
    protected abstract T create(T object);
    protected abstract T update(T object);
    protected abstract boolean deleteById(Long id);
}
