/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thogaKade.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import thogaKade.db.DBConnection;
import thogaKade.model.Order;

/**
 *
 * @author student
 */
public class OrderController {

    public static boolean addOrder(Order order) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String SQL = "Insert into Orders Values(?,?,?)";
            PreparedStatement stm = connection.prepareStatement(SQL);
            stm.setObject(1, order.getId());
            stm.setObject(2, order.getOrderDate());
            stm.setObject(3, order.getCustomerId());
            boolean orderIsAdded = stm.executeUpdate() > 0;
            if (orderIsAdded) {
                boolean addOrderDetail = OrderDetailController.addOrderDetail(order.getOrderDetailList());
                if (addOrderDetail) {
                    boolean isUpdateStock = ItemController.updateItemStock(order.getOrderDetailList());
                    if (isUpdateStock) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
