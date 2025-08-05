package com.upcns.demo.boundedContext.base;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@AllArgsConstructor
@Component
@RequestScope // 객체는 매 요청마다 생성
public class Rq {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public void setCookie(String name, long value) {
        setCookie(name, value + "");
    }

    public void setCookie(String name, String value) {
        resp.addCookie(new Cookie(name, value));
    }

    public boolean removeCookie(String name) {
        Cookie cookie = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (cookie != null) {
            cookie.setMaxAge(0); // 쿠키를 삭제하기 위해 만료 시간을 0으로 설정
            resp.addCookie(cookie); // 변경된 쿠키를 응답에 추가
            return true; // 쿠키가 성공적으로 삭제됨
        }

        return false;
    }

    public long getCookieAsLong(String name, long defaultValue) {
        String value = getCookie(name, null);

        if (value == null) return defaultValue;

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }

    private String getCookie(String name, String defaultValue) {
        if (req.getCookies() == null) return defaultValue;

        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(defaultValue);
    }

    public void setSession(String name, long value) {
        HttpSession session = req.getSession();
        session.setAttribute(name, value);
    }

    public long getSessionAsLong(String name, long defaultValue) {
        try {
            long value = (long) req.getSession().getAttribute(name);
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String getSessionAsStr(String name, String defaultValue) {
        try {
            String value = (String) req.getSession().getAttribute(name);
            return value != null ? value : defaultValue;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public boolean removeSession(String name) {
        HttpSession session = req.getSession();

        if(session.getAttribute(name) == null) return false;

        session.removeAttribute(name);
        return true;
    }

    // 디버깅용 함수
    public String getSessionDebugInfo() {
        HttpSession session = req.getSession();

        // 세션 ID
        String sessionId = session.getId();

        // 세션 속성 목록
        var attributeNames = session.getAttributeNames();

        // 세션 정보를 출력
        StringBuilder sessionInfo = new StringBuilder("Session ID: " + sessionId + "\nAttributes:\n");
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            Object value = session.getAttribute(name);
            sessionInfo.append(name).append(": ").append(value).append("\n");
        }

        return sessionInfo.toString();
    }

    public boolean isLogined() {
        long loginedMemberId = getSessionAsLong("loginedMemberId", 0L);
        return loginedMemberId > 0;
    }

    public boolean isLogout() {
        return !isLogined();
    }

    public long getLoginedMember() {
        return getSessionAsLong("loginedMemberId", 0L);
    }
}