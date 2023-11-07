package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.CartItem;

import java.util.List;

public interface CartDao {
    void addToCart(String username, String itemId, int quantity);
    void removeFromCart(String username, String itemId);
    void updateCartItemQuantity(String username, String itemId, int quantity);
    List<CartItem> getCartItemsByUsername(String username);
}
