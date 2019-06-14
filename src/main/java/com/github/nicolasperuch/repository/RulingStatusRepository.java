package com.github.nicolasperuch.repository;

import com.github.nicolasperuch.entity.RulingStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulingStatusRepository extends CrudRepository<RulingStatusEntity, Integer> {
}
