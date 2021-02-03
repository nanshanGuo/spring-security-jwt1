package com.sylinx.controller;

import com.sylinx.filter.TokenFilter;
import com.sylinx.model.LoginUser;
import com.sylinx.model.ResponseInfo;
import com.sylinx.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TokenController {

    @Autowired
    TokenService tokenService;

    @Autowired
    WebClient webClient;

    @PostMapping({"/tokencheck"})
    public ResponseInfo token(HttpServletRequest request) {

        String token = TokenFilter.getToken(request);
        String service = "/tokencheck";
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("token",token);
        ResponseInfo responseInfo = webClient.post().uri(service).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(ResponseInfo.class).block();

        return responseInfo;
    }
}
