package com.mediaportal.mediaxpusers.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class DadosGeneratedRepository {

    @PersistenceContext
    private EntityManager em;

    public Long gerarAssetIdViaProcedure(String nome) {
        Query query = em.createNativeQuery("EXECUTE PROCEDURE mx_createasset(925, ?1, ' ', 0, 0, 0, 'informix')");
        query.setParameter(1, nome);

        Object result = query.getSingleResult();
        if (result instanceof Number) {
            return ((Number) result).longValue();
        }

        throw new IllegalStateException("Erro ao gerar o AssetId.");
    }
}
