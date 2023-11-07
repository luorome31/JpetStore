package csu.web.mypetstore.domain;

import java.io.Serializable;

public class ItemBasic implements Serializable {
    private String username;
    private String itemId;
    private int number;

    public ItemBasic() {
        // 默认构造函数
    }

    public ItemBasic(String username, String itemId, int number) {
        this.username = username;
        this.itemId = itemId;
        this.number = number;
    }

    // Getter 和 Setter 方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
