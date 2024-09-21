package com.telran.gardenshop;

import com.telran.gardenshop.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private final OrderStatusService orderStatusService;
@Autowired
    public ScheduledTask(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }
    public void updateOrderStatus(){
        orderStatusService.updateOrderStatus();
    }
}
