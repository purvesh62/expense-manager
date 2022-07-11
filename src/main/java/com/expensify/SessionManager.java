package com.expensify;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpSession;


public class SessionManager {

    public static JSONObject getSession(HttpSession session) {
        return (JSONObject) session.getAttribute(session.getId());
    }

    public static void setSession(HttpSession session, Object value) {
        session.setAttribute(session.getId(), value);
    }

    public static void removeSession(HttpSession session) {
        session.removeAttribute(session.getId());
    }
}
