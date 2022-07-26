package com.expensify.model;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpSession;


public class SessionManager {

    public static JSONObject getSession(HttpSession session) {
        Object cache =  session.getAttribute(session.getId());
        if (cache != null){
            return (JSONObject) cache;
        }
        return new JSONObject();
    }

    public static void setSession(HttpSession session, Object value) {
        session.setAttribute(session.getId(), value);
    }

    public static void removeSession(HttpSession session) {
        session.removeAttribute(session.getId());
    }
}
