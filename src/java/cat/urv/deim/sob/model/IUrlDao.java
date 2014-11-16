/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

import cat.urv.deim.sob.DaoException;
import java.util.Collection;

/**
 *
 * @author danie_000
 */
public interface IUrlDao {
    public boolean addUrl(Url urln ,int idUser) throws DaoException;
    public Collection showUrl(int i) throws DaoException;
    public Url getLongUrl(String shortUrl, int id) throws DaoException;
}
