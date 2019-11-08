package demo.java.project.demo.Controller;

import demo.java.project.demo.Content.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/home")
public class HomeController {


    @RequestMapping(value = "/demo")
    @CrossOrigin
    public Result demo(@RequestParam(value = "username",required = true)String username, HttpServletRequest request){
        String userName=request.getParameter("username");
        Result result=new Result();
        result.setCode(200);
        result.setMessage("调用测试成功！");
        result.setData(userName);
        return result;
    }




}
