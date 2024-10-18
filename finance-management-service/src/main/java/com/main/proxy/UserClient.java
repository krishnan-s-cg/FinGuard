package com.main.proxy;
 

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.main.dto.UserDto;
 
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable int userId);
}
