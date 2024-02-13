# Ask
As of now when we do checkout it shows us buy now items.

We want to do place order. With place order

user should able to link existing address for order
use should specify the existing address is of type home or office for the order.
we should prepare a order summary for the order.
after successful order placed the units should be reduced in catalog for the items
# Design summary
* we will make a simple placeorder api.
* We will make a entity called addresstype. from that address type we have to write logic in such a way that we can get the userid and user address.
* We will write a business logic on ordersummary than we can show the payment gateway.
* At the end we will update the cartitem quantity using a simple foe loop.
