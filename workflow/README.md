# cartsummary flow:
# Controller layer:
  @GetMapping
  public Cart getCartSummary(){
    return cartItemService.getCartSummary(0L);
  }
* This annotation is used to map HTTP GET requests to a specific method in a Spring Controller.
* This declares a public method named getCartSummary that takes no parameters and returns a Cart object.
service layer:
 * Cart getCartSummary(Long cartId);
* The method signature Cart getCartSummary(Long cartId); indicates a method named getCartSummary that takes a Long parameter cartId and returns a Cart object.
# serviceimpl:
```
 @Override
  public Cart getCartSummary(Long cartId) {
    return getCartSummary(cartId, null);
  }
* This line returns the result of calling another getCartSummary method with two parameters: cartId and null. It seems to be providing a default value (null) for another parameter, possibly a CartItemState or a similar
#serviceimpl:
5.   private Cart getCartSummary(Long cartId, CartItemState cartItemState) {
//This is the beginning of the private method getCartSummary, which is also responsible for summarizing the cart.
6.     cartId = userSessionService.getUserId();
//Here cartid will get userid from ussersessionid
7.     List<CartItemEntity> cartItemEntities = findCartItemEntities(cartItemState);
// Calls the findCartItemEntities method to retrieve a list of CartItemEntity objects based on the provided cartItemState.
8.
9.     List<CartItem> cartItems = new ArrayList<>();
    // Ite will create a empty arraylist of cartitem.
10.    for (CartItemEntity entity : cartItemEntities) {
11.      CatalogItem catalogItem = catalogItemService.findById(entity.getCatalogItemId());
12.      cartItems.add(new CartItem().fromEntity(entity, catalogItem));
13.    }
      //this is a  for each loop which is iterate CartItemEntities. using findbyid we will get catalogitemid and store into catalogItem.
14.
15.    Double buyItemsTotalPrice = cartItems.stream()
16.        .filter(e -> e.getCartItemState() == CartItemState.BUY_NOW)
17.        .mapToDouble(CartItem::getTotal)
18.        .sum();
  //The filter operation is used to retain only those elements (CartItems) whose CartItemState is equal to BUY_NOW. Total Prices will store into buyItemsTotalPrice.
19.
20.    Cart cart = new Cart();
21.    cart.setCartId(cartId);
22.    cart.setTotal(buyItemsTotalPrice.floatValue());
23.    cart.setItems(cartItems);
24.    return cart;
//Finally, the method returns the populated cart object, providing a summary of the shopping cart, including the cartId, total price, and the list of items.
25.  }
```
