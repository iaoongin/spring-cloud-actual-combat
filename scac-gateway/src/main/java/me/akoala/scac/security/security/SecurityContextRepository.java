//package me.akoala.scac.security.security;
//
//import io.netty.buffer.ByteBufAllocator;
//import me.akoala.scac.security.rr.LoginRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferUtils;
//import org.springframework.core.io.buffer.NettyDataBufferFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextImpl;
//import org.springframework.security.web.server.context.ServerSecurityContextRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.nio.CharBuffer;
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Component
//public class SecurityContextRepository implements ServerSecurityContextRepository {
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityContextRepository.class);
//
//    private static final String TOKEN_PREFIX = "Bearer ";
//
//    @Autowired
//    private ReactiveAuthenticationManager authenticationManager;
//
//    @Override
//    public Mono save(ServerWebExchange swe, SecurityContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public Mono load(ServerWebExchange swe) {
//        ServerHttpRequest request = swe.getRequest();
//        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        String authToken = null;
//
//        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
//            authToken = authHeader.replace(TOKEN_PREFIX, "");
//        } else {
//            logger.warn("couldn't find bearer string, will ignore the header.");
//
//            Object[] obj = new Object[1];
//            swe.getFormData().subscribe(multiValueMap ->
//                    obj[0] = multiValueMap
//
//            );
//            System.out.println(obj[0]);
////            String s = resolveBodyFromRequest(request);
////            System.out.println(s);
//        }
//
//        Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
//        return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
//    }
//
//    /**
//     * 从Flux<DataBuffer>中获取字符串的方法
//     *
//     * @return 请求体
//     */
////    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
////        //获取请求体
////        Flux<DataBuffer> body = serverHttpRequest.getBody();
////
////        AtomicReference<String> bodyRef = new AtomicReference<>();
////        body.subscribe(buffer -> {
////            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
////            DataBufferUtils.release(buffer);
////            bodyRef.set(charBuffer.toString());
////        });
////        //获取request body
////        return bodyRef.get();
////    }
//    private DataBuffer stringBuffer(String value) {
//        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
//
//        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
//        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
//        buffer.write(bytes);
//        return buffer;
//    }
//
//    /**
//     * 获取请求体中的字符串内容
//     *
//     * @param serverHttpRequest
//     * @return
//     */
//    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
//        //获取请求体
//        Flux<DataBuffer> body = serverHttpRequest.getBody();
//        StringBuilder sb = new StringBuilder();
//
//        body.subscribe(buffer -> {
//            byte[] bytes = new byte[buffer.readableByteCount()];
//            buffer.read(bytes);
//            DataBufferUtils.release(buffer);
//            String bodyString = new String(bytes, StandardCharsets.UTF_8);
//            sb.append(bodyString);
//        });
//        return sb.toString();
//
//    }
//
//}