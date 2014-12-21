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
        boolean insertRel = false;
        try {
            con = createConnection();
            String sql = "INSERT INTO URL (URL,URL_SHORT,NUM_VISITS) VALUES (?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, url.getUrl());
            ps.setString(2, url.getUrlShort());
            ps.setInt(3, 0);
            ps.executeUpdate();
            insert = true;
            //Buscamos la id de la url introducida
            String sql2 = "SELECT ID_URL FROM URL WHERE URL_SHORT = ?";
            ps=con.prepareStatement(sql2);
            ps.setString(1, url.getUrlShort());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                url.setIdUrl(rs.getInt("ID_URL"));
                
                
                insertRel = insertRelation(url.getIdUrl(),idUser);
                
            }
            
            boolean isDone = insertRel && insert;
            return isDone;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }

    }
    @Override
    public Url findByUrlShort(Url url) throws DaoException{
        Url urlFind = new Url();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        
        
        try {
            con = createConnection();
            String sql = "SELECT * FROM URL WHERE URL_SHORT=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, url.getUrlShort());
            rs = ps.executeQuery();
            if(rs.next()){
                urlFind.setIdUrl(rs.getInt("ID_URL"));
                urlFind.setUrlShort(rs.getString("URL_SHORT"));
                
            } else{
                
            }
             return urlFind;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        }finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
                }
    }
    
    @Override
    public boolean insertRelation(int idUrl, int idUser)throws DaoException{
        boolean insert = false;
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = createConnection();
            String sql = "INSERT INTO URL_USER (ID_URL,ID_USER) VALUES (?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUrl);
            ps.setInt(2, idUser);
            ps.executeUpdate();
            insert = true;
            return insert;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        }finally{
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }
    }
    
    @Override
    public boolean findRelationByUrl(int url,int user)throws DaoException{
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs= null;
        boolean exists  =false;
        try {
            con = createConnection();
            String sql = "SELECT * FROM URL_USER WHERE ID_URL = ? AND ID_USER = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, url);
            ps.setInt(2, user);
            rs = ps.executeQuery();
            if(rs.next()){
                exists= true;
            }
            return exists;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        }finally{
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }
    }

    @Override
    public Collection showUrl(int id, int page) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        Collection retornUrl = new LinkedList();
        int urlPage = 5;
        int offset = urlPage*(page-1);
        try {
            con = createConnection();
            String sql =    "SELECT *\n" +
                            "FROM URL U\n" +
                            "LEFT JOIN URL_USER U2\n" +
                            "ON U.ID_URL=U2.ID_URL\n" +
                            "WHERE U2.ID_USER = ?\n" +
                            "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, offset);
            ps.setInt(3, Config.NUM_OF_ROWS_PER_PAGE);
            rs = ps.executeQuery();
            
            String sqlRow = "SELECT COUNT(ID_USER) FROM URL_USER WHERE ID_USER = ?";
            ps = con.prepareStatement(sqlRow);
            ps.setInt(1, id);
            ResultSet rsRow = ps.executeQuery();
            rsRow.next();
            this.numberOfRows = rsRow.getInt(1);
            
            while (rs.next()) {
                Url url = new Url();
                url.setIdUrl(rs.getInt("ID_URL"));
                url.setUrl(rs.getString(Config.ATTR_URL_NAME));
                url.setUrlShort(rs.getString("URL_SHORT"));
                url.setNumVisits(rs.getInt(Config.ATTR_URL_NUMVISITS));               
                retornUrl.add(url);
            }
            
            
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
    public Url getLongUrl(String shortUrl) throws DaoException {
        PreparedStatement ps = null;

        Connection con = null;
        ResultSet rs = null;
        Url url = new Url();
        try {
            con = createConnection();
            String sql = "SELECT URL FROM URL WHERE URL_SHORT = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, shortUrl);
            rs = ps.executeQuery();
            if(rs.next()){
                url.setUrl(rs.getString("URL"));
                updateVisits(url.getUrl());
            }
            return url;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }

    }

    private void updateVisits(String url) throws DaoException {
        PreparedStatement ps2 = null;
        Connection con2 = null;
        try {
            con2 = createConnection();
            String sqlUpdateVis = "UPDATE URL SET NUM_VISITS = NUM_VISITS+1 WHERE URL=?";
            ps2 = con2.prepareStatement(sqlUpdateVis);
            ps2.setString(1, url);
            ps2.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UrlDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public int getNumberOfRow(){
        return this.numberOfRows;
    }
    
    @Override
    public boolean deleteUrl(int id, int idUser) throws DaoException{
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = createConnection();
            String sql = "DELETE FROM URL_USER WHERE ID_URL = ? AND ID_USER = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, idUser);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }
    }
    @Override
    public Collection fetchAllUrlFromUser(int idUser) throws DaoException{
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        Collection retornUrl = new LinkedList();
        try {
            con = createConnection();
            String sql = "SELECT *\n" +
                            "FROM URL U\n" +
                            "LEFT JOIN URL_USER U2\n" +
                            "ON U.ID_URL=U2.ID_URL\n" +
                            "WHERE U2.ID_USER = ?\n";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Url url = new Url(rs.getString(Config.ATTR_URL_NAME),rs.getInt("ID_URL"),rs.getString("URL_SHORT"),rs.getInt(Config.ATTR_URL_NUMVISITS));              
                retornUrl.add(url);
            }                  
        return retornUrl;           
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }      
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
