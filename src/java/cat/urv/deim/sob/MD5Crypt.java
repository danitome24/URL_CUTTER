/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
public class MD5Crypt {
    
    private final String plainPass;
    
    public MD5Crypt(String pass){
        this.plainPass = pass;
    }
    
    public String cryptMD5() throws NoSuchAlgorithmException{
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = plainPass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digested.length; i++) {
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    }
    
}
