package csu.web.mypetstore.domain;

import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;
import csu.web.mypetstore.service.CatalogService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    private static final long serialVersionUID = 8329559983943337176L;
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());

    private final List<CartItem> itemList = new ArrayList<CartItem>();
    private CartDao cartDao = new CartDaoImpl();
    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }
    private  String username;

    public void initCart(String username) {
        //从数据库中读取该用户的购物车信息
        this.username = username;
        List<ItemBasic> itemBasicList = cartDao.getCartItemsByUsername(username);

        for (ItemBasic itemBasic : itemBasicList) {
            CatalogService catalogService = new CatalogService();
            Item item = catalogService.getItem(itemBasic.getItemId());
            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(itemBasic.getNumber());
            cartItem.setInStock(true);
            itemMap.put(itemBasic.getItemId(), cartItem);
            itemList.add(cartItem);
        }
    }

    public List<CartItem> getCartItemList() {
        return itemList;
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public Iterator<CartItem> getAllCartItems() {
        return itemList.iterator();
    }

    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock) {
        CartItem cartItem = (CartItem) itemMap.get(item.getItemId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(0);
            cartItem.setInStock(isInStock);

            itemMap.put(item.getItemId(), cartItem);
            itemList.add(cartItem);
        }
        cartItem.incrementQuantity();
        //将该项添加到数据库中
        cartDao.addToCart(username, item.getItemId(), 1);
    }

    public Item removeItemById(String itemId) {
        CartItem cartItem = (CartItem) itemMap.remove(itemId);
        if (cartItem == null) {
            return null;
        } else {
            itemList.remove(cartItem);

            cartDao.removeFromCart(username, itemId);

            return cartItem.getItem();
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        cartItem.incrementQuantity();
        //将数据库中的相应的项进行数量的增加
        cartDao.updateCartItemQuantity(username, itemId, cartItem.getQuantity()+1);
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        cartItem.setQuantity(quantity);
        //将数据库中的相应的项进行数量的修改
        cartDao.updateCartItemQuantity(username, itemId, quantity);
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        Iterator<CartItem> items = getAllCartItems();
        while (items.hasNext()) {
            CartItem cartItem = (CartItem) items.next();
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }
    public void deleteCart(){
        cartDao.deleteCartByUsername(username);
    }
}
