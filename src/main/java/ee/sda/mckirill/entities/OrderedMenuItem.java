package ee.sda.mckirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name= "ordered_menu_item")
public class OrderedMenuItem {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @OneToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "total_price", nullable = false)
    private BigDecimal sum;

    public OrderedMenuItem () {
        
    }

    public OrderedMenuItem(MenuItem menuItem, Integer quantity, BigDecimal sum) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}