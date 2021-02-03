package com.sylinx.service.impl;

import com.sylinx.model.LoginUser;
import com.sylinx.model.ResponseInfo;
import com.sylinx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    WebClient webClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        String service = "/getUser";
//        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//        formData.add("userName", username);
//        LoginUser loginUser = webClient.post().uri(service).contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(LoginUser.class).block();

		LoginUser loginUser = userService.getUser(username);
        return loginUser;
    }

}
