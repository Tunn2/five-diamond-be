package online.fivediamond.be.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import online.fivediamond.be.entity.Account;
import online.fivediamond.be.exception.AuthException;
import online.fivediamond.be.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
public class Filter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    // list danh sach api valid
    private final List<String> AUTH_PERMISSION = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/api/login",
            "/api/register",
            "/api/send-mail",
            "/api/user/{id}",
            "/api/login-google",
            "/api/accounts",
            "/api/diamond",
            "/api/diamond/{id}",
            "/api/diamond/search",
            "/api/diamond/update",
            "/api/product",
            "/api/product/{id}",
            "/api/product/available",
            "/api/product-line",
            "/api/product-line/available",
            "/api/product-line/{id}",
            "/api/forgot-password",
            "/api/category",
            "/api/category/{id}",
            "/api/certificate",
            "/api/certificate/{id}",
            "/api/certificate/available",
            "/api/collection",
            "/api/collection/{id}",
            "/api/comment/{id}",
            "/api/promotion/code",
            "/api/dashboard/account",
            "/api/dashboard/revenue",
            "/api/dashboard/account-by-month",
            "/api/dashboard/best-seller"
    );

    private boolean isPermitted(String uri) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return AUTH_PERMISSION.stream().anyMatch(pattern -> pathMatcher.match(pattern, uri));
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI(); //login, register
        if (isPermitted(uri)) {
            // yêu cầu truy cập 1 api => ai cũng truy cập đc
            filterChain.doFilter(request, response); // cho phép truy cập dô controller
        } else {
            String token = getToken(request);
            System.out.println(token);
            if (token == null) {
                resolver.resolveException(request, response, null, new AuthException("Empty token!"));
                return;
            }

            Account account;
            try {
                // từ token tìm ra thằng đó là ai
                account = tokenService.extractAccount(token);
            } catch (ExpiredJwtException expiredJwtException) {
                // token het han
                resolver.resolveException(request, response, null, new AuthException("Expired Token!"));
                return;
            } catch (MalformedJwtException malformedJwtException) {
                resolver.resolveException(request, response, null, new AuthException("Invalid Token!"));
                return;
            }
            // token dung
            UsernamePasswordAuthenticationToken
                    authenToken =
                    new UsernamePasswordAuthenticationToken(account, token, account.getAuthorities());
            authenToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenToken);
            // token ok, cho vao`
            filterChain.doFilter(request, response);
        }

    }

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.substring(7);
    }
}
