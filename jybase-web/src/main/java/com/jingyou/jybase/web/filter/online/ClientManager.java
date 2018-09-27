package com.jingyou.jybase.web.filter.online;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class ClientManager {
    private static ClientManager clientManager = new ClientManager();
    private ClientManager(){}
    private Map<String,Client> clientMap = new HashMap<String, Client>();
    private Map<String,HttpSession> sessionMap = new HashMap<String, HttpSession>();

    public static ClientManager getInstance(){
        return clientManager;
    }

    public int count(){
        return clientMap.size();
    }

    public void addClient(String account,Client client){
        clientMap.put(account,client);
    }

    public void removeClinet(String account){
        clientMap.remove(account);
    }

    public Client getClient(String account){
        return clientMap.get(account);
    }

    public Collection<Client> getAllClient(){
        return clientMap.values();
    }

    public  Map<String,Client> getClientMap(){
        return clientMap;
    }

    public void addSession(String sessionid,HttpSession session){
        sessionMap.put(sessionid,session);
    }

    public HttpSession removeSession(String sessionId){
        return sessionMap.remove(sessionId);
    }
}
