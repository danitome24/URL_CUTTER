/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

import cat.urv.deim.sob.Config;

/**
 *
 * @author Daniel
 */
public class UserDaoFactory {
    
    public static UserDaoImp getUserDAO(String type) { 
        if (type.equalsIgnoreCase(Config.JDBC_DRIVER)) {
            return new UserDaoImp();
        } else {
            return new UserDaoImp();
        }
    }
}
