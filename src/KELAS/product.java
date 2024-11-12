/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class product {

    int product_id, product_price, product_cat_id;
    String product_name, product_desc;

    private Connection konek;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;

    public product() throws SQLException {
        koneksi koneksi = new koneksi();
        konek = koneksi.konekDB();
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_cat_id() {
        return product_cat_id;
    }

    public void setProduct_cat_id(int product_cat_id) {
        this.product_cat_id = product_cat_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public void tambahProduct() throws SQLException {
        String quey = "INSERT INTO product VALUES(?,?,?,?,?)";
        try {
            ps = konek.prepareStatement(quey);
            ps.setInt(1, product_id);
            ps.setString(2, product_name);
            ps.setString(3, product_desc);
            ps.setInt(4, product_price);
            ps.setInt(5, product_cat_id);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan");

        }
    }

    public ResultSet tampilProduct() {

        query = "SELECT  "
                + "p.product_id ,"
                + "p.product_name ,  "
                + "p.product_desc , "
                + "p.product_price , "
                + "c.category_name  "
                + "FROM product p "
                + "JOIN  category c ON p.product_cat_id = c.category_id";
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Tampil");
        }
        return rs;
    }

    public void hapusProduk() {
        query = "DELETE FROM product WHERE product_id = ?";
        try {
            ps = konek.prepareStatement(query);
            ps.setInt(1, product_id);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Product berhasil dihapus");
        } catch (SQLException sQLException) {
            JOptionPane.showInputDialog(null, "product Gagal dihapus");
        }
    }

    public void ubahProduct() {

        query = "UPDATE product SET "
                + " product_name = ?,"
                + " product_desc = ?,"
                + " product_price = ?,"
                + " product_cat_id = ?"
                + " WHERE product_id = ?";
        try {

            ps = konek.prepareStatement(query);

            ps.setString(1, product_name);
            ps.setString(2, product_desc);
            ps.setInt(3, product_price);
            ps.setInt(4, product_cat_id);
            ps.setInt(5, product_id);

            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Product Berhasil Di Ubah");

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Product Gagal Di Ubah");
        }
    }

    public ResultSet comboCat() {
        query = "SELECT  p.product_cat_id, c.category_name FROM product p JOIN category;";
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");
        }
        return rs;
    }

    public ResultSet otoID() {

        query = "SELECT product_id FROM product ORDER BY product_id DESC LIMIT 1";
        try {
            st = konek.createStatement();
            rs = st.executeQuery(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to fetch next category ID: " + ex.getMessage());
        }
        return rs;
    }
}
