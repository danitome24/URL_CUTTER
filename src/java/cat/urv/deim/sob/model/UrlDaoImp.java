/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

import cat.urv.deim.sob.DaoException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie_000
 */
public class UrlDaoImp implements IUrlDao {

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
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }

    public ResultSet showUrl(int id) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = createConnection();
            String sql = "SELECT URL,NUM_VISITS FROM URL WHERE ID_USER = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
        return rs;
    }

    private Connection createConnection() throws Exception {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection("jdbc:derby://localhost:1527/SOB_DB", "root", "root");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
