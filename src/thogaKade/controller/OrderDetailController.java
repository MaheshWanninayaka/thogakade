/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thogaKade.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import thogaKade.db.DBConnection;
import thogaKade.model.OrderDetail;

/**
 *
 * @author student
 */
public class OrderDetailController {

    public static boolean addOrderDetail(ArrayList<OrderDetail> orderDetailList) throws ClassNotFoundException, SQLException {
        for (OrderDetail orderDetail : orderDetailList) {
            boolean addOrderDetail = addOrderDetail(orderDetail);
            if(!addOrderDetail){
                return false;
            }
        }
        return  true;
    }

    public static boolean addOrderDetail(OrderDetail orderDetail) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "Insert into OrderDetail values(?,?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, orderDetail.getOrderId());
        stm.setObject(2, orderDetail.getItemCode());
        stm.setObject(3, orderDetail.getQty());
        stm.setObject(4, orderDetail.getUnitPrice());
        return stm.executeUpdate()>0;
    }
}
