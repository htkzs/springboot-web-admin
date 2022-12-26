package com.atguigu.admin.controller;


import com.atguigu.admin.bean.Account;
import com.atguigu.admin.bean.City;
import com.atguigu.admin.bean.User;
import com.atguigu.admin.service.AccountService;
import com.atguigu.admin.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
public class IndexController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;

    @Autowired
    CityService cityService;

//    @Autowired
    StringRedisTemplate redisTemplate;

    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city){

        cityService.saveCity(city);
        return city;
    }

    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") Long id){
        return cityService.getById(id);
    }

    @ResponseBody
    @GetMapping("/acct")
    public Account getById(@RequestParam("id") Long id){

        return accountService.getAcctByid(id);
    }


    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account_tbl", Long.class);
        return aLong.toString();
    }

    /**
     * 来登录页 来到首页 通过URL: http://localhost:8080/ 或者  http://localhost:8080/login
     * @return
     */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    /*
     * @Author GhostGalaxy
     * @Description 处理表单的登录功能   th:action="@{/login}  注意在SpringMVC封装表单数据到一个javaBean中时 表单的name值要和实体类的字段名称一样，否则封装失败
     *
     * @Date 10:26:36 2022/12/24
     * @Param [user, session, model]
     * @return String
     **/
    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){ //RedirectAttributes

        if(StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())){
            //把登陆成功的用户保存起来
            session.setAttribute("loginUser",user);
            //登录成功重定向到main.html;  重定向防止表单重复提交  因为请求转发的地址栏不会改变，当用户再次刷新时就是一次新的提交 而重定向地址栏会改变再次刷新，只是刷新当前页面
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","账号密码错误");
            //回到登录页面
            return "login";
        }
    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){

        log.info("当前方法是：{}","mainPage");
        //是否登录。  拦截器，过滤器
//        Object loginUser = session.getAttribute("loginUser");
//        if(loginUser != null){
//            return "main";
//        }else {
//            //回到登录页面
//            model.addAttribute("msg","请重新登录");
//            return "login";
//        }
//        ValueOperations<String, String> opsForValue =
//                redisTemplate.opsForValue();
//
//        String s = opsForValue.get("/main.html");
//        String s1 = opsForValue.get("/sql");
//
//
//        model.addAttribute("mainCount",s);
//        model.addAttribute("sqlCount",s1);

        return "main";

    }
}
