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
# AppUtills
```
 public static String getEncodedString(String rawStr){
    return new String(Base64.getEncoder().encode(rawStr.getBytes(StandardCharsets.UTF_8)));
  }
```
#AuthenticationContext
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
