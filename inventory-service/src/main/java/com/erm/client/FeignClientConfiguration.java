//package com.erm.client;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//@Configuration
//public class FeignClientConfiguration {
//
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                JwtAuthenticationToken authentication =
//                    (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//                String token = authentication.getToken().getTokenValue();
//                requestTemplate.header("Authorization", "Bearer " + token);
//            }
//        };
//    }
//}
