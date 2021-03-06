package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.Authorize;
import util.RequestUtil;

@Controller
@CrossOrigin
@RequestMapping("/private")
public class PrivateSearch{
    @Autowired
    RequestUtil requestUtil;

    String access_token = null;

    @SuppressWarnings("unused")
    private void setRequestUtil(RequestUtil requestUtil){
        this.requestUtil = requestUtil;
    }

    @RequestMapping("/authorize")
    public void authorize(HttpServletResponse response) throws IOException{
        response.sendRedirect("https:/github.com/login/oauth/authorize?client_id=2687357014dac98c13a9&scope=user");
    }

    @ResponseBody
    @RequestMapping("/gettoken")
    public String gettoken(HttpServletRequest request, HttpServletResponse response){
        try{
            String token = Authorize.getToken(request.getParameter("code"));
            return token;
        }catch(IOException e){
            e.printStackTrace();
            return "get token fail";
        }
    }
}