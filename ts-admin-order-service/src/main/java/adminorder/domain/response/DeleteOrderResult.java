package adminorder.domain.response;

import adminorder.domain.bean.Order;

public class DeleteOrderResult {
    private boolean status;

    private String message;

    private Order order;

    public DeleteOrderResult(){

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
