package com.tha103.newview.orderlist.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.newview.act.controller.ActWithPicsDTO;
import com.tha103.newview.act.service.ActServiceImpl;

@WebServlet("/SeatOrderList")
public class OrderListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String actID = request.getParameter("actID");
        System.out.println("收到   "+actID);
        String targetUserName = request.getParameter("targetUserName");
        String modificationCountStr = request.getParameter("modificationCount");
        
       
        Integer modificationCount = Integer.parseInt(modificationCountStr);
        
       
        Map<String, String> seatData = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("seatNumber_")) {
                String seatNumber = paramName.replace("seatNumber_", "");
                String seatInfo = request.getParameter(paramName);
                seatData.put(seatNumber, seatInfo);
            }
        }
        
        Integer rowIndex1 = null;
        Integer seatIndex1 = null;
        Timestamp lastEditedTime = new Timestamp(System.currentTimeMillis());
       
        System.out.println("Received actID: " + actID);
        System.out.println("Received targetUserName: " + targetUserName);
        System.out.println("Received modificationCount: " + modificationCount);
        System.out.println("Received lastEditedTime: " + lastEditedTime);
        
        if(actID != null && !actID.trim().isEmpty()) {
            
            Integer actIdValue = Integer.parseInt(actID);
            
            
            ActServiceImpl actService = new ActServiceImpl();
            ActWithPicsDTO actData = actService.getActWithPicturesById(actIdValue);
            
            if(actData != null) {
                System.out.println("Act Price Total: " +modificationCount * actData.getActPrice());                   
            } else {
                System.out.println("No data found for actID: " + actID);
            }
        } else {
            System.out.println("Invalid actID received.");
        }
      
     
        for (Map.Entry<String, String> entry : seatData.entrySet()) {
            String seatNumber = entry.getKey();
            String seatInfo = entry.getValue();
            System.out.println("Received seatNumber: " + seatNumber);
            System.out.println("Received seatInfo: " + seatInfo);
        }
        
        response.getWriter().write("Data received and printed successfully.");
    }
}
