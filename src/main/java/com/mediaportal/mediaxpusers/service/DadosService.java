package com.mediaportal.mediaxpusers.service;

import com.mediaportal.mediaxpusers.client.UserClient;
import com.mediaportal.mediaxpusers.dtos.DadosDTO;
import com.mediaportal.mediaxpusers.models.DadosEntity;
import com.mediaportal.mediaxpusers.repository.DadosRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DadosService {

    private final DadosRepository dadosRepository;
    private final UserClient userClient;

    public DadosService(DadosRepository dadosRepository, UserClient userClient) {
        this.dadosRepository = dadosRepository;
        this.userClient = userClient;
    }

    public void salvarDados(DadosDTO dadosDTO) {
        DadosEntity entity = new DadosEntity();
        entity.setNome(dadosDTO.getNome());
        entity.setCpf(dadosDTO.getCpf());
        entity.setEmail(dadosDTO.getEmail());

        dadosRepository.save(entity);
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
