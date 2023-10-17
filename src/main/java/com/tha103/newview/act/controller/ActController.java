package com.tha103.newview.act.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.newview.act.model.ActDAO;
import com.tha103.newview.act.model.ActDAOHibernateImpl;
import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.actcategory.model.ActCategory;
import com.tha103.newview.cityaddress.model.CityAddress;

@WebServlet("/ActData")
public class ActController extends HttpServlet {
   

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	ActDAO actVO = new ActDAOHibernateImpl();
    	ActCategory actCategory = new ActCategory();
    	CityAddress city = new CityAddress();
    	ActWithPicsDTO actWithPicsDTO = new ActWithPicsDTO();
    	req.setCharacterEncoding("UTF-8"); 
    	
        res.setContentType("text/html; charset=UTF-8"); 
        List<ActVO> act = actVO.getAll();
        String actIDelete = req.getParameter("actIDelete");
        String action = req.getParameter("action");
        String actId = req.getParameter("actId");
       
        
        if ("update".equals(action)) {
        if(actId != null && actId != "") {
        System.out.println(actId);
        
        Integer ID = Integer.parseInt(actId);
        
        }else {
        	System.out.println("沒東西");
        	req.setAttribute("actIdError", "null");
        	req.getRequestDispatcher("ActJSP.jsp").forward(req, res);
        }
        if (actId != null && !actId.isEmpty()) {
            try {
                Integer intValue = Integer.parseInt(actId);

                
                ActVO matchingAct = null;
                for (ActVO acts : act) {
                    if (acts.getActID() == intValue) {
                        matchingAct = acts;
                        break;
                    }
                }

                if (matchingAct != null) {
                   
                    req.setAttribute("actData", matchingAct);

                   
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/update-act.jsp");
                    dispatcher.forward(req, res);
                } else {
                	req.setAttribute("actIdNotFound", "notFound");
                	req.getRequestDispatcher("ActJSP.jsp").forward(req, res);
                	
                }
            } catch (NumberFormatException e) {
            	req.setAttribute("actIdNotFound", "notFound");
            	req.getRequestDispatcher("ActJSP.jsp").forward(req, res);
            }
        } else {
        	req.setAttribute("actIdError", "null");
        	req.getRequestDispatcher("ActJSP.jsp").forward(req, res);
        }
        
        }    
        //收到修改
        if ("UP".equals(action)) {
        String actIdStr = req.getParameter("actId");
        System.out.println("-------------------------");
        
        System.out.println(actIdStr);
        String actName = req.getParameter("actName");
        System.out.println(actName);
        System.out.println("-------------------------");
        String actPriceStr = req.getParameter("actPrice");
        System.out.println(actPriceStr);
        System.out.println("-------------------------");
        String actCategoryID = req.getParameter("actCategory");
        System.out.println(actCategoryID);
        System.out.println("-------------------------");
        String publisherName = req.getParameter("publisher");
        String actTimeStr = req.getParameter("time");
        System.out.println(actTimeStr);
        String cityID = req.getParameter("cityName");
        String actIntroduce = req.getParameter("actIntroduce");
        String actDate = req.getParameter("actDate");
        String approvalConditionStr = req.getParameter("approvalCondition");
        System.out.println("結束");
        
        /*設定錯誤處理*/
        
        String[] fieldNames = {"actName", "actPrice","actCategory","publisher","time","cityName","actIntroduce","actDate","approvalCondition"};
        Map<String, String> fieldErrors = new HashMap<>();

        //檢查是否為空
        for (String fieldName : fieldNames) {
            String fieldValue = req.getParameter(fieldName);
            if (fieldValue == null || fieldValue.isEmpty()) {
            	System.out.println(fieldName);
                fieldErrors.put(fieldName + "Error", "不能為空");
            }
        }
        
        if (!fieldErrors.isEmpty()) {
            
            req.setAttribute("fieldErrors", fieldErrors);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/update-act.jsp");
            dispatcher.forward(req, res);
        } else {
        	ActVO updatedAct = new ActVO();
        	
        	Integer actID = Integer.parseInt(actIdStr);
        	System.out.println("真的有:    "+actID);
        	Integer actPrice = Integer.parseInt(actPriceStr);
        	Integer actCategoryNameI = Integer.parseInt(actCategoryID);
        	Integer cityIDI = Integer.parseInt(cityID);
        	Date actTime = null;
        	if (actTimeStr != null && !actTimeStr.isEmpty()) {
        		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        		try {
        			actTime = timeFormat.parse(actTimeStr);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}
        	Date actDateD = null;
        	if (actDate != null && !actDate.isEmpty()) {
        	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	    try {
        	    	actDateD = dateFormat.parse(actDate);
        	    } catch (Exception e) {
        	        e.printStackTrace();
        	    }
        	}
        	boolean approvalCondition = "true".equalsIgnoreCase(approvalConditionStr);
        	actCategory.setActCategoryID(actCategoryNameI);        	
        	city.setActAdressID(cityIDI);
        	
        	updatedAct.setActID(actID);
        	updatedAct.setActName(actName);
        	updatedAct.setActPrice(actPrice);
        	updatedAct.setTime(actTime);
        	updatedAct.setCityAddressID(city);
        	updatedAct.setActCategory(actCategory);
        	updatedAct.setActIntroduce(actIntroduce);
        	updatedAct.setPublisherVOs(publisherName);
        	updatedAct.setActDate(actDateD);
        	updatedAct.setApprovalCondition(approvalCondition);
        	
        	
            actVO.update(updatedAct) ;
            res.sendRedirect("ActJSP.jsp");
        }
        } 
        /*刪除*/
        if ("delete".equals(action)) {
        if (actIDelete != null && !actIDelete.isEmpty()) {
        	 try {
                 
                 actVO.delete(Integer.parseInt(actIDelete));

                 
                 res.setContentType("text/html");
                 res.getWriter().println("Act ID " + actIDelete + " 成功");
                 RequestDispatcher dispatcher = req.getRequestDispatcher("ActJSP.jsp");
                 dispatcher.forward(req, res);
             } catch (Exception e) {
                 e.printStackTrace();
                
                 res.setContentType("text/html");
                 res.getWriter().println("Act ID " + actIDelete + " 失败");
             }
         } else {
            
             res.setContentType("text/html");
             res.getWriter().println("Act ID 不能为空");
         }
    }
        
        
       
        
    }
    
}
