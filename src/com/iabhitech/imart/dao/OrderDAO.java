/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */
package com.iabhitech.imart.dao;

import com.iabhitech.imart.dbutil.DBConnection;
import com.iabhitech.imart.pojo.ProductPojo;
import com.iabhitech.imart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abhineet Verma
 */
public class OrderDAO {

    public static String getNextOrderID() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select max(order_id) from orders");
        rs.next();
        String lastId = rs.getString(1);
        if (lastId == null) {
            return "O-101";
        }
        int o_id = Integer.parseInt(lastId.substring(2));
        o_id += 1;
        return "O-" + o_id;
    }

    public static boolean addOrder(ArrayList<ProductPojo> al, String ordId) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("insert into orders values (?,?,?,?,?,?,?)");
        int count = 0;
        for (ProductPojo p : al) {
            ps.setString(1, ordId);
            ps.setString(2, p.getProductId());
            ps.setDouble(3, p.getProductPrice());
            ps.setDouble(4, p.getOurPrice());
            ps.setInt(5, p.getTax());
            ps.setInt(6, p.getQuantity());
            ps.setString(7, UserProfile.getUserId());
            count += ps.executeUpdate();
        }
        return count == al.size();
    }

    public static List<ProductPojo> getOrders(String orderId) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("select "
                        + "orders.order_id,"
                        + "orders.userid,"
                        + "orders.p_id,"
                        + "products.p_name,"
                        + "products.p_company_name,"
                        + "orders.p_price,"
                        + "orders.our_price,"
                        + "orders.p_tax,"
                        + "orders.quantity "
                        + "from orders, products "
                        + "where "
                        + "orders.p_id = products.p_id "
                        + "and "
                        + "orders.userid=? "
                        + "and "
                        + "orders.order_id=?");
        ps.setString(1, UserProfile.getUserId());
        ps.setString(2, orderId);
        ResultSet rs = ps.executeQuery();
        ArrayList<ProductPojo> orders = new ArrayList<>();
        while (rs.next()) {

            ProductPojo p = new ProductPojo(
                    rs.getString("p_id"),
                    rs.getString("p_name"),
                    rs.getString("p_company_name"),
                    rs.getDouble("p_price"),
                    rs.getDouble("our_price"),
                    rs.getInt("p_tax"),
                    rs.getInt("quantity")
            );
            orders.add(p);
        }
        return orders;
    }

    public static List<ProductPojo> getAllOrders(String orderId) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("select "
                        + "orders.order_id,"
                        + "orders.userid,"
                        + "orders.p_id,"
                        + "products.p_name,"
                        + "products.p_company_name,"
                        + "orders.p_price,"
                        + "orders.our_price,"
                        + "orders.p_tax,"
                        + "orders.quantity "
                        + "from orders, products "
                        + "where "
                        + "orders.p_id = products.p_id "
                        + "and "
                        + "orders.order_id=?");
        ps.setString(1, orderId);
        ResultSet rs = ps.executeQuery();
        ArrayList<ProductPojo> orders = new ArrayList<>();
        while (rs.next()) {

            ProductPojo p = new ProductPojo(
                    rs.getString("p_id"),
                    rs.getString("p_name"),
                    rs.getString("p_company_name"),
                    rs.getDouble("p_price"),
                    rs.getDouble("our_price"),
                    rs.getInt("p_tax"),
                    rs.getInt("quantity")
            );
            orders.add(p);
        }
        return orders;
    }

    public static List<String> getAllOrderIds(String userid) throws SQLException {
        String sql;
        if (userid == null) {
            sql = "select distinct order_id from orders order by order_id desc";
        } else {
            sql = "select distinct order_id from orders where userid=? order by order_id desc";
        }
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement(sql);
        if (userid != null) {
            ps.setString(1, userid);
        }
        ResultSet rs = ps.executeQuery();
        ArrayList<String> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getString("order_id"));
        }
        return ids;
    }
}
