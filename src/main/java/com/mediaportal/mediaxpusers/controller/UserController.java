package com.mediaportal.mediaxpusers.controller;

import com.mediaportal.mediaxpusers.dtos.UserDataDTO;
import com.mediaportal.mediaxpusers.service.DadosService;
import com.mediaportal.mediaxpusers.serviceImpl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    DadosService dadosService;


    @PostMapping("/createUser")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDataDTO userDTO) {
        try{
            userService.createAssetUser(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.CREATED);
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


