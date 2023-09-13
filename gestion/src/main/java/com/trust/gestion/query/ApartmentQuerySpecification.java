package com.trust.gestion.query;

import com.trust.gestion.services.entities.ApartmentEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ApartmentQuerySpecification implements Specification<ApartmentEntity> {
    private QueryCriteria criteria;
    @Override
    public Specification<ApartmentEntity> and(Specification<ApartmentEntity> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<ApartmentEntity> or(Specification<ApartmentEntity> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<ApartmentEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getKey().equalsIgnoreCase("=")){
            return builder.equal(root.get(criteria.getKey()), criteria.getValue().toString());
        }

        return null;
    }
}
