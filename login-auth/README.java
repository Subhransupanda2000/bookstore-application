# UserController
```
 @PostMapping("/login")
  public Token authenticate(@RequestBody User user){
    String tokenValue = userService.authenticate(user);
    Token token = new Token();
    token.setEncodedTokenValue(tokenValue);
    return token;
  }
```
* Using login url we will enter into controller layer .In this layer we will get tokenvalue by calling authenticate method from userService and the we will return token value here.
# UserService
```
 @Override
  public String authenticate(User user) {
    UserEntity entity = userEntityRepository.findByEmail(user.getEmail());
    if(entity == null){
      throw new AppRuntimeException("login failed");
    }
    String encodedPassword = entity.getPassword();
    boolean passwordMatched = passwordEncoder.matches(user.getPassword(), encodedPassword);

    if(passwordMatched){
      user = new User().fromEntity(entity);
      return authService.createAuthenticationContext(user);
    }
    throw new AppRuntimeException("login failed");
  }
```
* In this userservice we will authenticate the user it means the email and password that user enter while login we will check those credintials are valid or not.
# AuthService
```
public interface AuthService {
  public String createAuthenticationContext(User user);
}
```
# AuthServiceImpl
```

  @Override
  public String createAuthenticationContext(User user) {
    AuthenticationContext context = new AuthenticationContext();
    context.setUser(user);
    context.setUserId(user.getEmail());
    context.setHeaderTokenUUID(UUID.randomUUID().toString());
    context.setExpiryAt(authTokenTtl * 60 * 1000 + System.currentTimeMillis());
    authCacheClient.put(context.getUserId(), context);
    authCacheClient.put(context.getHeaderTokenUUID(), context);
    return AppUtils.getEncodedString(context.getHeaderTokenUUID());
  }
```
After validation of user a new authenticate context will create and we will set email token,expiry and we will store those credentials in cache.At the end a token will return as string.


# AppUtills
```
 public static String getEncodedString(String rawStr){
    return new String(Base64.getEncoder().encode(rawStr.getBytes(StandardCharsets.UTF_8)));
  }
```
# AuthenticationContext
```

@Data
public class AuthenticationContext {
  private String userId;
  private String headerTokenUUID;
  private Long expiryAt;
  private User user;
}
```
# AuthCacheClient
```

  public String put(String key, AuthenticationContext context){
    tokenMap.put(key, context);
    return key;
  }

```
# AuthenticationFilter
```
package com.example.bookstoreapp.filters;

import com.example.bookstoreapp.auth.AuthService;
import com.example.bookstoreapp.exceptions.AppRuntimeException;
import com.example.bookstoreapp.utils.AppUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class AuthenticationFilter implements Filter {

  private static final String[] patterns = {"/api/**", "/user/login", "/user/signup", "/health"};

  public static final String X_AUTH_TOKEN = "X-AUTH-TOKEN";

  private AuthService authService;

  public AuthenticationFilter(AuthService authService) {
    this.authService = authService;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    log.info("filter executed for url: {}", request.getRequestURL());
    log.info("filter executed for uri: {}", request.getRequestURI());
    if (matchUriPattern(request.getRequestURI())) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    boolean userContext = setUserContext(request, response);
    if(userContext) {
      filterChain.doFilter(servletRequest, servletResponse);
    }

  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }

  private boolean matchUriPattern(String requestUri) {
    for (String pattern : patterns) {
      boolean matched = AppUtils.matchesPattern(pattern, requestUri);
      if (matched) {
        return true;
      }
    }
    return false;
  }

  private boolean setUserContext(HttpServletRequest request, HttpServletResponse response) {
    String token = request.getHeader(X_AUTH_TOKEN);
    try {
      authService.setUserContext(token);
      return true;
    } catch (AppRuntimeException e) {
      log.error("error while setting user context: ", e);
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
    return false;
  }
}
```
 
