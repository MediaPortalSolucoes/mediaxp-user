package com.mediaportal.mediaxpusers.service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

        throw new IllegalStateException("Procedimento não retornou um AssetId válido.");
    }
}

