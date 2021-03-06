package com.aaron.system.controller;

import com.aaron.activiti.util.Const;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Aaron
 * @description 登陆
 * @date 2019/4/18
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private IdentityService identityService;

    @RequestMapping(value = "/login")
    public String login(String id,String pwd,HttpServletRequest req){
        boolean b = identityService.checkPassword(id, pwd);
        User user = identityService.createUserQuery().userId(id).singleResult();
        if(b){
            req.getSession().setAttribute(Const.SESSION_ACCOUNT, user);
            return "redirect:/index/toHome";
        }else {
            req.getSession().removeAttribute(Const.SESSION_ACCOUNT);
            req.getSession().invalidate();
            return "error";
        }
    }
}
