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
# AuthenticationFilter
```
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
```
* It Checks if the requested URI matches any of the patterns that should bypass authentication. If so, the filter chain is continued without performing authentication.
* If the URI doesn't match the bypass patterns, it tries to set the user context using the setUserContext method. If successful, the filter chain is continued.

```
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
* This method extracts the authentication token from the request header and attempts to set the user context using the AuthService. If successful, it returns true. If an exception occurs (likely due to an invalid token), it logs an error and sets the response status to UNAUTHORIZED. It returns false in this case.
  ```
   @Override
  public void setUserContext(String tokenStr) {
    tokenStr = StringUtils.isEmpty(tokenStr) ? StringUtils.EMPTY : AppUtils.getDecodedString(tokenStr);
    AuthenticationContext context = authCacheClient.get(tokenStr);

    if(context == null){
      log.warn("authentication is null for token: {}", tokenStr);
      throw new AppRuntimeException("authentication context is empty");
    }

    if(System.currentTimeMillis() > context.getExpiryAt()){
      log.warn("authentication expired for user: {}", context.getUserId());
      throw new AppRuntimeException("token expired");
    }

    userContextService.setUser(context.getUser());
    log.info("user context set for user: {}", context.getUser());
  }
  ```
  * Decode the token string using AppUtils.getDecodedString() and handle empty strings.
  * Then Retrieve the authentication context from the cache based on the decoded token.
  * Then it Check if the authentication context is null. It will throw uthentication context is empty.
  * if the token has expired by comparing the current time with the expiry time in the context then it will throw  oken expired.
  * Else it Set the user context using the user from the authentication context.
  * Log information about the successful setting of the user context

