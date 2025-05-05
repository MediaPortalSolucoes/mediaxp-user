package com.mediaportal.mediaxpusers.service;

import com.mediaportal.mediaxpusers.client.UserClient;
import com.mediaportal.mediaxpusers.dtos.UserDataDTO;
import com.mediaportal.mediaxpusers.models.DadosEntity;
import com.mediaportal.mediaxpusers.repository.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DadosService {

    private final DadosRepository dadosRepository;
    private final UserClient userClient;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DadosService(DadosRepository dadosRepository, UserClient userClient) {
        this.dadosRepository = dadosRepository;
        this.userClient = userClient;
    }

    public void createAssetUser(UserDataDTO dadosDTO) {

        String query = String.format("EXECUTE PROCEDURE mx_createasset(925, '%s', ' ', 0, 0, 0, 'informix')",
                dadosDTO.getName());

        jdbcTemplate.execute(query);

        Long assetid = getAssetUserId();
        insertAssetTvciIdentidade(assetid, dadosDTO);

    }

    public void insertAssetTvciIdentidade(Long assetid, UserDataDTO dadosDTO) {
        String query = "INSERT INTO tvci_identidade (assetid, nome, cpf, email) VALUES (?, ?, ?, ?)";
        System.out.println("INSERT " + query);
        jdbcTemplate.update(query, assetid, dadosDTO.getName(), dadosDTO.getCpf(), dadosDTO.getEmail());
    }

    public Long getAssetUserId() {
        String query = "SELECT MAX(assetid) as assetid FROM mc_assetmaster";

        return jdbcTemplate.queryForObject(query, Long.class);
    }

    @Transactional
    public boolean deletarPorCpf(String cpf) {
        Optional<DadosEntity> usuario = dadosRepository.findByCpf(cpf);
        if (usuario.isPresent()) {
            dadosRepository.delete(usuario.get());
            return true;
        }
        return false;
    }
}
