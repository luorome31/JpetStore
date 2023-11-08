package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.LineItem;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.domain.Sequence;
import csu.web.mypetstore.persistence.ItemDao;
import csu.web.mypetstore.persistence.LineItemDao;
import csu.web.mypetstore.persistence.OrderDao;
import csu.web.mypetstore.persistence.SequenceDao;
import csu.web.mypetstore.persistence.impl.ItemDaoImpl;
import csu.web.mypetstore.persistence.impl.LineItemDaoImpl;
import csu.web.mypetstore.persistence.impl.OrderDaoImpl;
import csu.web.mypetstore.persistence.impl.SequenceDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
  private OrderDao orderDao = new OrderDaoImpl();
  private LineItemDao lineItemDao = new LineItemDaoImpl();
  private ItemDao itemDao=new ItemDaoImpl();
  private SequenceDao sequenceDao = new SequenceDaoImpl();
  public void insertOrder(Order order) {
    order.setOrderId(getNextId("ordernum"));
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      String itemId = lineItem.getItemId();
      Integer increment = lineItem.getQuantity();
      Map<String, Object> param = new HashMap<String, Object>(2);
      param.put("itemId", itemId);
      param.put("increment", increment);
      itemDao.updateInventoryQuantity(param);
    }
    orderDao.insertOrder(order);
    orderDao.insertOrderStatus(order);
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      lineItem.setOrderId(order.getOrderId());
      lineItemDao.insertLineItem(lineItem);
    }
  }


  public Order getOrder(int orderId) {
    Order order = orderDao.getOrder(orderId);
    order.setLineItems(lineItemDao.getLineItemsByOrderId(orderId));

    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      Item item = itemDao.getItem(lineItem.getItemId());
      item.setQuantity(itemDao.getInventoryQuantity(lineItem.getItemId()));
      lineItem.setItem(item);
    }

    return order;
  }
  public int getNextId(String name) {
    Sequence sequence = new Sequence(name, -1);
    sequence = sequenceDao.getSequence(sequence);
    if (sequence == null) {
      throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
              + " sequence).");
    }
    Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
    sequenceDao.updateSequence(parameterObject);
    return sequence.getNextId();
  }

  public List<Order> getOrdersByUsername(String username) {
    return orderDao.getOrdersByUsername(username);
  }
}
