/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
import cat.urv.deim.sob.MD5Crypt;
import cat.urv.deim.sob.model.User;
import cat.urv.deim.sob.model.UserDaoFactory;
import cat.urv.deim.sob.model.UserDaoImp;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danie_000
 */
public class ForgetPassCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email;
        String randomPass;

        email = request.getParameter("email");

        UserDaoImp userDao = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        User idUser;
        try {
            idUser = userDao.findUserByEmail(email);
            
            if (idUser.getId() == -1) {
                
                request.setAttribute("emailError", "This e-amail is not in the system");
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/rememberPass.jsp").forward(request, response);
            } else {
                randomPass = generateRandomString();
                MD5Crypt md = new MD5Crypt(randomPass);
                String randomPassMD5 = md.cryptMD5();
                boolean introduit = resetNewPassword(randomPassMD5, idUser.getId());
                if (introduit) {
                    Properties props = new Properties();
                    String msgBody = "Your new Password is: " + randomPass;
                    props.put("mail.smtp.port", "465");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.debug", "false");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.ssl.enable", "true");
                    Session session = Session.getInstance(props, new SocialAuth());
                    try {
                        Message msg = new MimeMessage(session);
                        msg.setFrom(new InternetAddress(Config.FROM_ADDRESS, "Administrator of URL cutter"));
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, "Mr. User"));
                        msg.setSubject("New Password to your account");
                        msg.setText(msgBody);
                        Transport.send(msg);
                        
                        request.setAttribute("emailSent", "The e-mail has been sent");
                        ServletContext context = request.getSession().getServletContext();
                        context.getRequestDispatcher("/rememberPass.jsp").forward(request, response);
                    } catch (MessagingException mex) {
                        mex.printStackTrace();
                    }
                }
            }
        } catch (DaoException ex) {
            ex.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ForgetPassCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String generateRandomString() {
        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < Config.RANDOM_STRING_LENGTH; i++) {
            int number = getRandomNumber();
            char ch = Config.CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(Config.CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    private boolean resetNewPassword(String newPass,int idUser) throws DaoException {
        UserDaoImp userDao = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        boolean actualitzat = userDao.updatePassword(newPass, idUser);
        return actualitzat;
    }

    class SocialAuth extends Authenticator {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(Config.FROM_ADDRESS, Config.PASSWORD);

        }
    }

}
