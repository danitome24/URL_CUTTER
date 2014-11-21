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

/**
 *
 * @author Daniel
 */
public class UserDaoImp implements IUserDao {

    @Override
    public boolean add(User user) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = createConnection();
            String sql = "INSERT INTO USUARI (FIRSTNAME,LASTNAME,EMAIL,PASSWORD,USERNAME) VALUES ('" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.getUserName() + "')";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }

    }

    @Override
    public User findUserByName(User u) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        String userDb;
        User userId = new User();
        try {
            con = createConnection();
            String sql = "SELECT * FROM USUARI WHERE USUARI.\"USERNAME\"= ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, ((User) u).getUserName());
            ResultSet rs = ps.executeQuery();
            rs.next();

            userDb = rs.getString(Config.ATTR_USER_USERNAME);
            if (u.getUserName().equals(userDb)) {
                userId.setId(rs.getInt(Config.ATTR_USER_ID));
                userId.setEmail(rs.getString(Config.ATTR_USER_EMAIL));
                userId.setUserName(rs.getString(Config.ATTR_USER_USERNAME));
                userId.setLastName(rs.getString(Config.ATTR_USER_LASTNAME));
                userId.setFirstName(rs.getString(Config.ATTR_USER_FIRSTNAME));
                userId.setPassword(rs.getString(Config.ATTR_USER_PASSWORD));
            }
            return userId;
        } catch (Exception ex) {
            ex.printStackTrace();throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }

    }

    public boolean updatePassword(String newPass, int idUser) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        String userDb;
        boolean updated = false;
        try {
            con = createConnection();
            String sql = "UPDATE USUARI SET PASSWORD = ? WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, newPass);
            ps.setInt(2, idUser);
            ps.executeUpdate();
            updated = true;
            return updated;
        } catch (Exception ex) {
            ex.printStackTrace();throw new DaoException(ex.getMessage());
        } finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }
       
    }

    /**
     *
     * @param email
     * @return
     * @throws DaoException
     */
    @Override
    public User findUserByEmail(String email) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        String userDb;
        User userReturn = new User();
        out.println("EMAIL" + email);
        int idUser = -1;
        try {
            con = createConnection();
            String sql = "SELECT ID FROM USUARI WHERE EMAIL='" + email + "'";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            idUser = Integer.parseInt(rs.getString(Config.ATTR_USER_ID));
            userReturn.setId(idUser);
            return userReturn;
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }finally {
            try {if (ps != null) {ps.close();}} catch (Exception ex) {}
            try {if (con != null) {con.close();}} catch (Exception ex) {}
        }    
    }

    @Override
    public boolean login(User u) throws DaoException {
        PreparedStatement ps = null;
        Connection con = null;
        boolean exists = false;
        try {
            con = createConnection();
            String userDb = null;
            String passDb = null;
            String sql = "SELECT * FROM usuari where usuari.\"USERNAME\"= ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, ((User) u).getUserName());

            ResultSet rs = ps.executeQuery();
            rs.next();
            try {
                userDb = rs.getString(Config.ATTR_USER_USERNAME);
                passDb = rs.getString(Config.ATTR_USER_PASSWORD);
                out.println(rs.getString(Config.ATTR_USER_USERNAME));
                out.println(rs.getString(Config.ATTR_USER_PASSWORD));
            } catch (Exception e) {
                out.println("No hay usuario igual");
            }

            if ((u.getUserName().equals(userDb)) && (u.getPassword().equals(passDb))) {
                exists = true;
            }
            return exists;  
            } catch (Exception ex) {
                throw new DaoException(ex.getMessage());
            }finally {
                try {if (ps != null) {ps.close();}} catch (Exception ex) {}
                try {if (con != null) {con.close();}} catch (Exception ex) {}
            }  
    }
    @Override
    public boolean userPassIsCorrect(User user) throws DaoException{
        boolean exists = false;
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = createConnection();
            String sql = "SELECT * FROM usuari where usuari.\"USERNAME\"= ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, ((User) user).getUserName());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String pass = rs.getString(Config.ATTR_USER_PASSWORD);
            if (user.getPassword().equals(pass)){
                exists = true;
                out.println("Contraseña correcta");
            }
            else{
                out.println("Contraseña incorrecta");
            }
        } catch (Exception ex) {
            throw new DaoException(ex.getMessage());
        }
        return exists;
    }

    private Connection createConnection() throws Exception {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection("jdbc:derby://localhost:1527/SOB_P1_DB", "root", "root");
            //SOB_DB o SOB_P1_DB
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

}
