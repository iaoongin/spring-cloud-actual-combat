//package me.akoala.scac.gateway.self.config;
//
//import org.reactivestreams.Publisher;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Component
//public class ModifyBodyGatewayFilterImpl implements GatewayFilter {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        ServerHttpResponse response = exchange.getResponse();
//        DataBufferFactory dataBufferFactory = response.bufferFactory();
//
//        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
//
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                if (body instanceof Flux) {
//                    Flux<? extends DataBuffer> flux = (Flux<? extends DataBuffer>) body;
//
//                    return super.writeWith(flux.buffer().map(dataBuffers -> {
//                        DataBuffer join = dataBufferFactory.join(dataBuffers);
//                        byte[] content = new byte[join.readableByteCount()];
//                        join.read(content);
//                        DataBufferUtils.release(join);
//                        response.getHeaders().setContentLength(content.length);
//                        return dataBufferFactory.wrap(content);
//                    }));
//                }
//                return super.writeWith(body);
//            }
//        };
//
//        ServerWebExchange swe = exchange.mutate().response(decoratedResponse).build();
//        return chain.filter(swe);
//    }
//}