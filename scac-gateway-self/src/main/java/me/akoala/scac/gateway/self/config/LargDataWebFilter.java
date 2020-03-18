//package me.akoala.scac.gateway.self.config;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Component
//public class LargDataWebFilter implements WebFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//
////        ServerHttpRequest request = exchange.getRequest();
//
//        //封装新的request
////        Flux<DataBuffer> bodyFlux = request.getBody();
////        HttpHeaders headers = request.getHeaders();
////        request = new ServerHttpRequestDecorator(request) {
////            @Override
////            public HttpHeaders getHeaders() {
////                long contentLength = headers.getContentLength();
////                HttpHeaders httpHeaders = new HttpHeaders();
////                httpHeaders.putAll(super.getHeaders());
////                if (contentLength > 0) {
////                    httpHeaders.setContentLength(contentLength);
////                } else {
////                    httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
////                }
////                return httpHeaders;
////            }
////
////            @Override
////            public Flux<DataBuffer> getBody() {
////                return bodyFlux;
////            }
////        };
//
//        ServerHttpResponse response = exchange.getResponse();
////        response.beforeCommit();
//        return chain.filter(exchange.mutate().request(request).build()).doFinally(signalType -> signalType.getClass());
//    }
//
//    @Override
//    public int getOrder() {
//        return -1;
//    }
//}
