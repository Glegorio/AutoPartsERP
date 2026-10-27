package com.autoparts.erp.repository;

import com.autoparts.erp.entity.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {

    Optional<Peca> findByCodigoBarra(String codigoBarra);

    boolean existsByCodigoBarra(String codigoBarra);
}
