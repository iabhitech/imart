/*
 * Super Market Management System
 * Designed By Abhineet Verma  * 
 */
package com.iabhitech.imart.dao;

import com.iabhitech.imart.dbutil.DBConnection;
import com.iabhitech.imart.pojo.ProductPojo;
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
public class ProductDAO {

    public static String getNextProductID() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select max(p_id) from products");
        rs.next();
        String lastId = rs.getString(1);
        if (lastId == null) {
            return "P101";
        }
        int p_id = Integer.parseInt(lastId.substring(1));
        p_id += 1;
        return "P" + p_id;
    }

    public static boolean addProduct(ProductPojo product) throws SQLException {

        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("insert into products values (?,?,?,?,?,?,?,'Y')");
        ps.setString(1, product.getProductId());
        ps.setString(2, product.getProductName());
        ps.setString(3, product.getProductCompany());
        ps.setDouble(4, product.getProductPrice());
        ps.setDouble(5, product.getOurPrice());
        ps.setInt(6, product.getTax());
        ps.setInt(7, product.getQuantity());
        return ps.executeUpdate() == 1;
    }

    public static List<ProductPojo> getProductDetails() throws SQLException {
        Statement st = DBConnection.getConnection()
                .createStatement();
        ResultSet rs = st.executeQuery("select * from products where status='Y'");
        ArrayList<ProductPojo> productsList = new ArrayList<>();
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
            productsList.add(p);
        }
        return productsList;
    }

    public static boolean deleteProduct(String p_id) throws SQLException {

        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("update products set status='N' where p_id=?");
        ps.setString(1, p_id);
        return ps.executeUpdate() == 1;
    }

    public static boolean updateProduct(ProductPojo p) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("update products set p_name=?, p_company_name=?, p_price=?, our_price=?,p_tax=?, quantity=? where p_id=?");
        ps.setString(1, p.getProductName());
        ps.setString(2, p.getProductCompany());
        ps.setDouble(3, p.getProductPrice());
        ps.setDouble(4, p.getOurPrice());
        ps.setInt(5, p.getTax());
        ps.setInt(6, p.getQuantity());
        ps.setString(7, p.getProductId());
        return ps.executeUpdate() == 1;

    }

    public static ProductPojo getProductDetail(String id) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("select * from products where P_ID = ? and status = 'Y'");
        ps.setString(1, id);
        ResultSet rs =ps.executeQuery();
        ProductPojo  p = null;
        if(rs.next()){
            p = new ProductPojo(
                    rs.getString("p_id"),
                    rs.getString("p_name"),
                    rs.getString("p_company_name"),
                    rs.getDouble("p_price"),
                    rs.getDouble("our_price"),
                    rs.getInt("p_tax"),
                    rs.getInt("quantity")
            );
        }
        return p;
    }
    
    public static boolean updateStocks(List<ProductPojo> productsList) throws SQLException{
        PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("update products set quantity=quantity-? and P_ID = ? and status = 'Y'");
        int rowsUpdated = 0;
        for(ProductPojo p: productsList){
            ps.setInt(1, p.getQuantity());
            ps.setString(2, p.getProductId());
            rowsUpdated += ps.executeUpdate();
        }
        return rowsUpdated == productsList.size();
    }
}
