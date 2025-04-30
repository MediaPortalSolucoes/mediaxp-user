package com.mediaportal.mediaxpusers.repository;

import com.mediaportal.mediaxpusers.models.DadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DadosRepository extends JpaRepository<DadosEntity, Long> {

    Optional<DadosEntity> findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
