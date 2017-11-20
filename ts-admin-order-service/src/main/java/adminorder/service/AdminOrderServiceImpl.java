package adminorder.service;

import adminorder.domain.bean.Order;
import adminorder.domain.request.AddOrderRequest;
import adminorder.domain.request.DeleteOrderRequest;
import adminorder.domain.request.UpdateOrderRequest;
import adminorder.domain.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public GetAllOrderResult getAllOrders(String id) {
        if(checkId(id)){
            System.out.println("[Admin Order Service][Get All Orders]");
            //Get all of the orders
            ArrayList<Order> orders = new ArrayList<Order>();
            //From ts-order-service
            QueryOrderResult result = restTemplate.getForObject(
                    "http://ts-order-service:12031/order/findAll",
                    QueryOrderResult.class);
            if(result.isStatus()){
                System.out.println("[Admin Order Service][Get Orders From ts-order-service successfully!]");
                orders.addAll(result.getOrders());
            }
            else
                System.out.println("[Admin Order Service][Get Orders From ts-order-service fail!]");
            //From ts-order-other-service
            result = restTemplate.getForObject(
                    "http://ts-order-other-service:12032/orderOther/findAll",
                    QueryOrderResult.class);
            if(result.isStatus()){
                System.out.println("[Admin Order Service][Get Orders From ts-order-other-service successfully!]");
                orders.addAll(result.getOrders());
            }
            else
                System.out.println("[Admin Order Service][Get Orders From ts-order-other-service fail!]");
            //Return orders
            GetAllOrderResult getAllOrderResult = new GetAllOrderResult();
            getAllOrderResult.setStatus(true);
            getAllOrderResult.setMessage("Get the orders successfully!");
            getAllOrderResult.setOrders(orders);
            return getAllOrderResult;
        }
        else{
            System.out.println("[Admin Order Service][Wrong Admin ID]");
            GetAllOrderResult result = new GetAllOrderResult();
            result.setStatus(false);
            result.setMessage("The loginId is Wrong: " + id);
            return result;
        }
    }

    @Override
    public DeleteOrderResult deleteOrder(DeleteOrderRequest request) {
        return null;
    }

    @Override
    public UpdateOrderResult updateOrder(UpdateOrderRequest request) {
        return null;
    }

    @Override
    public AddOrderResult addOrder(AddOrderRequest request) {
        if(checkId(request.getId())){
            AddOrderResult addOrderResult = restTemplate.getForObject(
                    "http://ts-order-other-service:12032/order/findAll",
                    AddOrderResult.class);
        }
        else{
            System.out.println("[Admin Order Service][Wrong Admin ID]");
            AddOrderResult result = new AddOrderResult();
            result.setStatus(false);
            result.setMessage("The loginId is Wrong: " + request.getId());
            return result;
        }
        return null;
    }

    private boolean checkId(String id){
        if("1d1a11c1-11cb-1cf1-b1bb-b11111d1da1f".equals(id)){
            return true;
        }
        else{
            return false;
        }
    }
}
