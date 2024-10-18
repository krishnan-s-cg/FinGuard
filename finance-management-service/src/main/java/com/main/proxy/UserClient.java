package com.main.proxy;
 

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
import com.main.entity.User;
 
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/dto/{id}")
    User getUserById(@PathVariable int userId);
}
