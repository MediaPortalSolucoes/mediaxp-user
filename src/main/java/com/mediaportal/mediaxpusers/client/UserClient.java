package com.mediaportal.mediaxpusers.client;

import com.mediaportal.mediaxpusers.dtos.DadosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "OPENIT-SERVICE", url = " ")
public interface UserClient {

    @PostMapping("/assets/{id}")
    DadosDTO getUserById(@PathVariable("id") Long id);


}
