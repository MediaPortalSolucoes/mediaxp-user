package com.mediaportal.mediaxpusers.client;

import com.mediaportal.mediaxpusers.dtos.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "OPENIT-SERVICE/assets")
public interface UserClient {

    @PostMapping("/{id}")
    public void publishAsset(@PathVariable("id") Long id);


}
