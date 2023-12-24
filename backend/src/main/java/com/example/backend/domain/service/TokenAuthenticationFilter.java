//package com.example.backend.domain.service;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtService jwtService;
//
//    public TokenAuthenticationFilter(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    )
//            throws ServletException, IOException {
//        String accessToken = request.getHeader("Authorization");
//
//        if (accessToken != null && !accessToken.isEmpty() && jwtService.isTokenValid(accessToken)) {
//            // Xác thực thành công, tiếp tục chuỗi filter
//            filterChain.doFilter(request, response);
//        } else {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Unauthorized: Token không tồn tại hoặc không hợp lệ");
//        }
//    }
//}
