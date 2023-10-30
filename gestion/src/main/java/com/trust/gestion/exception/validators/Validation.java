package com.trust.gestion.exception.validators;

import com.trust.gestion.exception.OwnerValidException;

import java.util.Optional;

public interface Validation<R, E> {
    void validate(R resource, Optional<E> existingEntity) throws OwnerValidException;

}
