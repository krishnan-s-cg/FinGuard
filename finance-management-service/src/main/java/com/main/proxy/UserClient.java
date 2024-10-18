package com.main.proxy;
import com.main.dto.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/dto/{id}")
    User getUserById(@PathVariable int id);
}