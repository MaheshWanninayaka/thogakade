/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thogaKade.model;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Order {
    private String id;
    private String orderDate;
    private String customerId;
    private ArrayList<OrderDetail> orderDetailList;

    public Order() {
    }

    public Order(String id, String orderDate, String customerId, ArrayList<OrderDetail> orderDetailList) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.orderDetailList = orderDetailList;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderDetailList
     */
    public ArrayList<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    /**
     * @param orderDetailList the orderDetailList to set
     */
    public void setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
    
}
