package cat.urv.deim.sob.model;

import cat.urv.deim.sob.DaoException;

public interface IUserDao {

    public boolean add(User u) throws DaoException;
    public int findUserByName(User u) throws DaoException;
    public int findUserByEmail(String email) throws DaoException;
    public boolean login(User u) throws DaoException;
    public boolean updatePassword(String s,int i) throws DaoException;
}
