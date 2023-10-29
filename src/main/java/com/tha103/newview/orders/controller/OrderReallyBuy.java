package com.tha103.newview.orders.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 引入相關的DAO和Service類別
import com.tha103.newview.act.model.ActDAO;
import com.tha103.newview.act.model.ActDAOHibernateImpl;
import com.tha103.newview.act.service.ActPicService;
import com.tha103.newview.act.service.ActPicServiceImpl;
import com.tha103.newview.act.service.ActService;
import com.tha103.newview.act.service.ActServiceImpl;
import com.tha103.newview.act.service.ActUpdateService;
import com.tha103.newview.act.service.ActUpdateServiceImpl;
import com.tha103.newview.actpic.model.ActPicDAO;
import com.tha103.newview.actpic.model.ActPicDAOHibernateImpl;
import com.tha103.newview.user.jedis.JedisPoolUtil;
import com.tha103.newview.websocketchat.service.RedisService;
import com.tha103.newview.websocketchat.service.RedisServiceImpl;

import redis.clients.jedis.JedisPool;

@WebServlet("/WantToBuy")
public class OrderReallyBuy extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 宣告相關的Service層物件
    private RedisService redisService;
    private ActService actService;
    private ActPicService actPicService;
    private ActUpdateService actUpdateService;

    public OrderReallyBuy() {
        super();
        // 初始化DAO和Service物件
        ActDAO actDAO = new ActDAOHibernateImpl();
        ActPicDAO actPicDAO = new ActPicDAOHibernateImpl();
        actService = new ActServiceImpl(actDAO);
        actPicService = new ActPicServiceImpl(actPicDAO);
        actUpdateService = new ActUpdateServiceImpl(actDAO, actPicDAO);
    }

    @Override
    public void init() throws ServletException {
        // 初始化Redis連線池並設定Service物件
        JedisPool jedisPool = JedisPoolUtil.getJedisPool();
        this.redisService = new RedisServiceImpl(jedisPool);
        ActDAO actDAO = new ActDAOHibernateImpl();
        this.actService = new ActServiceImpl(actDAO);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");

        // 請求中獲取action參數
        String action = req.getParameter("action");
        String userIDstr = (String) req.getSession().getAttribute("userid");
        String actIDstr =(String) req.getSession().getAttribute("actID");
        // 從session中找到userID和actID
        Integer userID;
        Integer actID;
        Map<String, String> seatNumber;
        try {
            userID = Integer.parseInt(userIDstr);
            actID = Integer.parseInt(actIDstr);
            seatNumber = redisService.findSeatsNumberByActIDAndUserName(actIDstr, userIDstr);
            System.out.println("處理購買流程   ");
            for (Map.Entry<String, String> entry : seatNumber.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());

                // 使用設定的key 去尋找特定座位資料
                String cartKey = "cart:" + userID + ":" + actID + ":" + entry.getKey()+":NotReallyBuy";
                String cartData = redisService.getCartDataFromRedis(cartKey);
                System.out.println(cartKey);
                System.out.println(cartData);
                if (cartData != null && cartData.contains(":NotReallyBuy")) {
                    cartData = cartData.replace(":NotReallyBuy", "buy");
                    System.out.println("成功");
                }

                if (cartData == null) {
                  
                    res.getWriter().write("{\"error\":\"Cart not found.\"}");
                } else {
                 
                    res.getWriter().write(cartData);
                }
            }
        } catch (NumberFormatException e) {
            // 若解析失敗，返回一個錯誤消息
            res.getWriter().write("{\"error\":\"Invalid userID or actID.\"}");
            return;
        }

        // 處理購買行為
        if ("buy".equals(action)) {
           
            System.out.println("處理購買流程   "+seatNumber);
        }
    }

}
