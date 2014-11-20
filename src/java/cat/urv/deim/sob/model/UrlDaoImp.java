/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie_000
 */
public class UrlDaoImp implements IUrlDao {

    private int numberOfRows = 0;
    @Override
    public boolean addUrl(Url url, int idUser) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        boolean insert = false;
        try {
            con = createConnection();
            String sql = "INSERT INTO URL (ID_USER,URL,NUM_VISITS,URL_SHORT) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.setString(2, url.getUrl());
            ps.setInt(3, 0);
            ps.setString(4, url.getUrlShort());
            ps.executeUpdate();
            out.println("La nueva URL ha sido introducida corectamente en la BD");
            insert = true;
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }

    }

    public Collection showUrl(int id, int page) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        Collection retornUrl = new LinkedList();
        int urlPage = 5;
        int offset = urlPage*page;
        try {
            con = createConnection();
            String sql = "SELECT URL,URL_SHORT,NUM_VISITS FROM URL WHERE ID_USER=? OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            
            String sqlRow = "SELECT COUNT(ID_USER) FROM URL WHERE ID_USER=?";
            ps = con.prepareStatement(sqlRow);
            ps.setInt(1, id);
            ResultSet rsRow = ps.executeQuery();
            rsRow.next();
            this.numberOfRows = rsRow.getInt(1);
            
            while (rs.next()) {
                Url url = new Url();
                url.setUrl(rs.getString(Config.ATTR_URL_NAME));
                url.setUrlShort(rs.getString("URL_SHORT"));
                url.setNumVisits(rs.getInt(Config.ATTR_URL_NUMVISITS));               
                retornUrl.add(url);
            }
            
            out.println("Nº of rows: "+this.numberOfRows);
            return retornUrl;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
                }

    }

    @Override
    public Url getLongUrl(String shortUrl, int idUser) throws DaoException {
        PreparedStatement ps = null;

        Connection con = null;
        ResultSet rs = null;
        Url url = new Url();
        try {
            con = createConnection();
            String sql = "SELECT URL FROM URL WHERE URL_SHORT = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, (String) shortUrl);
            rs = ps.executeQuery();
            rs.next();
            url.setUrl(rs.getString(Config.ATTR_URL_NAME));
            updateVisits(idUser, url.getUrl());
            return url;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
                }

    }

    private void updateVisits(int idUser, String url) throws DaoException {
        PreparedStatement ps2 = null;
        Connection con2 = null;
        try {
            con2 = createConnection();
            String sqlUpdateVis = "UPDATE URL SET NUM_VISITS = NUM_VISITS+1 WHERE ID_USER = ? AND URL=?";
            ps2 = con2.prepareStatement(sqlUpdateVis);
            ps2.setInt(1, idUser);
            ps2.setString(2, url);
            ps2.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UrlDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public int getNumberOfRow(){
        return this.numberOfRows;
    }

    private Connection createConnection() throws Exception {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection("jdbc:derby://localhost:1527/SOB_P1_DB", "root", "root");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
