package com.github.nicolasperuch.repository;

import com.github.nicolasperuch.entity.RulingStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RulingStatusRepository extends CrudRepository<RulingStatusEntity, Integer> {
    Optional<RulingStatusEntity> findByRulingId(Integer rulingId);
}
