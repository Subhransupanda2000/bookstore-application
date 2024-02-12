# cartsummary flow:
# Controller layer:
  @GetMapping
  public Cart getCartSummary(){
    return cartItemService.getCartSummary(0L);
  } provide line by line details
* This annotation is used to map HTTP GET requests to a specific method in a Spring Controller.
* This declares a public method named getCartSummary that takes no parameters and returns a Cart object.
service layer:
 * Cart getCartSummary(Long cartId);
* The method signature Cart getCartSummary(Long cartId); indicates a method named getCartSummary that takes a Long parameter cartId and returns a Cart object.
# serviceimpl layer:
 @Override
  public Cart getCartSummary(Long cartId) {
    return getCartSummary(cartId, null);
  }
* This line returns the result of calling another getCartSummary method with two parameters: cartId and null. It seems to be providing a default value (null) for another parameter, possibly a CartItemState or a similar
