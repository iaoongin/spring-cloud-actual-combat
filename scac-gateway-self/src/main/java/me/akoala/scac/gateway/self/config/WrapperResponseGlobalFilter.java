//package me.akoala.scac.gateway.self.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.reactivestreams.Publisher;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.core.io.buffer.DefaultDataBufferFactory;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.Charset;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Component
//@Slf4j
//public class WrapperResponseGlobalFilter implements WebFilter, Ordered {
//
//	@Override
//	public int getOrder() {
//		// -1 is response write filter, must be called before that
//		return -2;
//	}
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//		ServerHttpResponse originalResponse = exchange.getResponse();
//		DataBufferFactory bufferFactory = originalResponse.bufferFactory();
//		ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
//			@Override
//			public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//				AtomicReference<String> bodyRef = new AtomicReference<>();
//				if (body instanceof Flux) {
//					Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
//
//					return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
//
//						DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
//						DataBuffer join = dataBufferFactory.join(dataBuffers);
//
//						byte[] content = new byte[join.readableByteCount()];
//
//						join.read(content);
//						// 释放掉内存
//						DataBufferUtils.release(join);
//						String str = new String(content, Charset.forName("UTF-8"));
//
//						originalResponse.getHeaders().setContentLength(str.getBytes().length);
//						log.error("gateway catch service exception error:"+ str);
//
////						JsonResult result = new JsonResult();
////				        result.setCode(ErrorCode.SYS_EXCEPTION.getCode());
////				        result.setMessage(ErrorCode.SYS_EXCEPTION.getMsg());
//
//						return bufferFactory.wrap(str.getBytes());
//					}));
//
//				}
//				// if body is not a flux. never got there.
//	                return super.writeWith(body);
//	            }
//	        };
//	        // replace response with decorator
//	        return chain.filter(exchange.mutate().response(decoratedResponse).build());
//	    }
//
//}