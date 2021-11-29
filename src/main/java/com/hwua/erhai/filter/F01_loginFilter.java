package com.hwua.erhai.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "F01_loginFilter",urlPatterns = "/*")
public class F01_loginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;

        HttpSession session=httpServletRequest.getSession(false);
        if (session!=null&&session.getAttribute("username")!=null){
            chain.doFilter(request, response);
            return;
        }
        String url=httpServletRequest.getRequestURI();
        String ctxPath=httpServletRequest.getContextPath();
        String path=url.substring(ctxPath.length());
        if (path.equals("/")||path.startsWith("/index")||
        path.startsWith("/login")||path.startsWith("/doLogin")||
        path.startsWith("/static")||path.startsWith("/register")||path.startsWith("/doRegister")){
            chain.doFilter(request, response);
            return;
        }
       response.setCharacterEncoding("UTF-8");//设置编码
        response.setContentType("text/html;charset=utf-8");//设置浏览器代码
        httpServletResponse.getWriter().write("用户没有登录，请登录后在访问此页面，将在3秒后跳转到登录页面");
        httpServletResponse.setHeader("refresh",String.format("3;url=%s/login.jsp",ctxPath));
    }
}
