package demo.java.project.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class KingInterceptor extends HttpSessionHandshakeInterceptor {

    private static final Logger log=  LoggerFactory.getLogger(KingInterceptor.class);

    //握手前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.trace("before handshake");
        System.out.print("---------------before handshake-----------------");
        if(request instanceof ServletServerHttpRequest){
             ServletServerHttpRequest servletRequest=(ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(true);
            String userName="";
            if (session != null) {
                userName = (String) session.getAttribute("username");
                if (userName == null) {
                    userName = "WEBSOCKET_USERNAME_IS_NULL";
                }
                System.out.print("userName="+userName);
                attributes.put(KingWebSocketHandler.WEB_SOCKET_USERNAME,userName);
            }else{
                attributes.put(KingWebSocketHandler.WEB_SOCKET_USERNAME,"NoUserName");
            }

        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    //握手后
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.trace("After handshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
