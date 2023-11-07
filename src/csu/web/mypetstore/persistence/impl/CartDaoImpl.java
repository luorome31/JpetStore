package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.persistence.CartDao;

import java.util.List;

public class CartDaoImpl implements CartDao {
    @Override
    public void addToCart(String username, String itemId, int quantity) {

    }

    @Override
    public void removeFromCart(String username, String itemId) {

    }

    @Override
    public void updateCartItemQuantity(String username, String itemId, int quantity) {

    }

    @Override
    public List<CartItem> getCartItemsByUsername(String username) {
        return null;
    }
}
