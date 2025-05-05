package com.mediaportal.mediaxpusers.client;

import com.mediaportal.mediaxpusers.dtos.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "OPENIT-SERVICE", url = " ")
public interface UserClient {

    @PostMapping("/assets/{id}")
    UserDataDTO getUserById(@PathVariable("id") Long id);


}
