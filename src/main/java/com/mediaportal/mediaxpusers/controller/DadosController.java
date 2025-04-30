package com.mediaportal.mediaxpusers.controller;

import com.mediaportal.mediaxpusers.dtos.DadosDTO;
import com.mediaportal.mediaxpusers.service.DadosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/dados")
public class DadosController {

    Logger log = LoggerFactory.getLogger(DadosController.class);

    private final DadosService dadosService;

    public DadosController(DadosService dadosService) {
        this.dadosService = dadosService;
    }

    @PostMapping
    public ResponseEntity<String> receberDados(@RequestBody DadosDTO dadosDTO) {
        try{
            dadosService.salvarDados(dadosDTO);
            log.debug("Dados salvo com sucesso");
            return ResponseEntity.ok("Dados recebidos e salvos com sucesso!");
        } catch (Exception e) {
            log.error("Erro ao salvar dados", e);
            throw new RuntimeException("Erro ao salvar os dados!", e);
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarPorCpf(@PathVariable String cpf) {
        try {
            boolean deletado = dadosService.deletarPorCpf(cpf);
            if (deletado) {
                log.debug("Dados deletado com sucesso");
                return ResponseEntity.noContent().build(); // 204
            } else {
                log.debug("CPF não existe");
                return ResponseEntity.notFound().build(); // 404
            }
        } catch (Exception e) {
            log.error("Erro ao deletar dados", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }


}


