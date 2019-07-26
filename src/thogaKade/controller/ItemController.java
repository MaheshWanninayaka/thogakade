/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thogaKade.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import thogaKade.db.DBConnection;
import thogaKade.model.Item;
import thogaKade.model.OrderDetail;

/**
 *
 * @author student
 */
public class ItemController {

    public static ArrayList<String> getAllItemCodes() throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select code From Item");
        ArrayList<String> itemCodeList = new ArrayList<>();
        while (rst.next()) {
            itemCodeList.add(rst.getString("code"));
        }
        return itemCodeList;
    }

    public static Item searchItem(String code) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("Select * From Item where code='" + code + "'");
        if (rst.next()) {
            return new Item(code, rst.getString("description"), rst.getDouble("unitPrice"), rst.getInt("qtyOnHand"));
        } else {
            return null;
        }
    }

    public static boolean updateItemStock(ArrayList<OrderDetail> orderDetails) throws ClassNotFoundException, SQLException {

        for (OrderDetail orderDetail : orderDetails) {
            boolean updateStock = updateItemStock(orderDetail);
            if (!updateStock) {
                return false;
            }
        }
        return true;
    }

    public static boolean updateItemStock(OrderDetail orderDetail) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "Update Item set qtyOnHand=qtyOnHand-? where code=?";
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, orderDetail.getQty());
        stm.setObject(2, orderDetail.getItemCode());
        return stm.executeUpdate() > 0;
    }
}
