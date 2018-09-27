package com.jingyou.jybase.web.util;

import com.jingyou.jybase.common.util.EncryptUtil;
import com.jingyou.jybase.common.util.PropertiesUtil;
import org.apache.commons.mail.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/1/6.
 */
public class EmailUtil {
    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    /**
     * 发送邮件
     * @param to
     * @param subject
     * @param msg
     */
    public static void send(String to,String subject,String msg){
        Email email = getEmail(new SimpleEmail());
        try {
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(msg);
            email.setSSLOnConnect(false);
            logger.debug("sending email...");
            email.send();
            logger.debug("send email success!");
        } catch (EmailException e) {
            e.printStackTrace();
            logger.error("mail"+e.getMessage());
        }
    }

    /**
     * 发送带HTML格式邮件
     * @param to
     * @param subject
     * @param msg
     */
    public static void sendHtml(String to,String subject,String msg){
        Email email = getEmail(new HtmlEmail());
        try {
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(msg);
            email.setSSLOnConnect(false);
            logger.debug("sending email...");
            email.send();
            logger.debug("send email success!");
        } catch (EmailException e) {
            e.printStackTrace();
            logger.error("mail"+e.getMessage());
        }
    }

    /**
     * 发送带附件邮件
     * @param to
     * @param subject
     * @param msg
     * @param filePath
     */
    public static void send(String to,String subject,String msg,String filePath){
        MultiPartEmail email = (MultiPartEmail)getEmail(new MultiPartEmail());
        try {
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(msg);
            email.attach(new File(filePath));
            logger.debug("sending email...");
            email.send();
            logger.debug("send email success!");
        } catch (EmailException e) {
            e.printStackTrace();
            logger.error("mail"+e.getMessage());
        }

    }

    /**
     * 发送带多个附件附件邮件
     * @param to
     * @param subject
     * @param msg
     * @param filePaths
     */
    public static void send(String to,String subject,String msg,List<String> filePaths){
        MultiPartEmail email = (MultiPartEmail)getEmail(new MultiPartEmail());
        try {
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(msg);
            for (String filePath : filePaths) {
                email.attach(new File(filePath));
            }
            logger.debug("sending email...");
            email.send();
            logger.debug("send email success!");
        } catch (EmailException e) {
            e.printStackTrace();
            logger.error("mail"+e.getMessage());
        }

    }

    private static Email getEmail(Email email) {
        PropertiesUtil putil = PropertiesUtil.getInstance("/email.properties");
        String server = putil.getValueByKey("email.server");
        String port = putil.getValueByKey("email.port");
        String username = putil.getValueByKey("email.username");
        String password = "";
        try {
            password = EncryptUtil.decrypt(putil.getValueByKey("email.password"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("mail password:" + e.getMessage());
        }
        String outbox = putil.getValueByKey("email.outbox");
        email.setHostName(server);
        email.setSmtpPort(Integer.parseInt(port));
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        try {
            email.setFrom(outbox);
        } catch (EmailException e) {
            e.printStackTrace();
            logger.error("mail" + e.getMessage());
        }
        return email;
    }

    public static void main(String[] args) {
        String to = "weilaiboy@126.com";
        String subject = "通知";
        String msg = "test!";
        String filePath = "E:\\项目范围管理.pptx";
        send(to,subject,msg,filePath);
    }
}