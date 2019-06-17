package com.github.nicolasperuch.repository;

import com.github.nicolasperuch.entity.RulingVoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulingVoteRepository extends CrudRepository<RulingVoteEntity, Integer> {
    List<RulingVoteEntity> findByRulingId(Integer rulingId);
}
