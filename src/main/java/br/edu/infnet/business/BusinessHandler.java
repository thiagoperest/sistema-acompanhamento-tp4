package br.edu.infnet.business;

import java.util.List;

public abstract class BusinessHandler<T> {

    public abstract T create(T object);

    public abstract T update(T object);

    public abstract T findById(Long id);

    public abstract List<T> findAll();

    public abstract boolean delete(Long id);

    protected abstract void validateBusinessRules(T object);

    protected void validateNotNull(T object, String fieldName) {
        if (object == null) {
            throw new IllegalArgumentException(fieldName + " não pode ser nulo");
        }
    }

    protected void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " não pode ser vazio");
        }
    }

    protected void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("E-mail deve ter formato válido");
        }
    }

    protected void validateId(Long id, String fieldName) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(fieldName + " deve ser um ID válido");
        }
    }
}
