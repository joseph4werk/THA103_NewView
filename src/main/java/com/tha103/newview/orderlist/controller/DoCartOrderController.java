package com.tha103.newview.orderlist.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.orderlist.model.OrderListVO;
import com.tha103.newview.orderlist.service.OrderListService;
import com.tha103.newview.orderlist.service.OrderListServiceImpl;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.newview.orders.service.OrdersService;
import com.tha103.newview.orders.service.OrdersServiceImpl;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.newview.user.model.UserVO;

@WebServlet("/DoOrder")
public class DoCartOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");

		// 先將Json資料取出並轉換成個別值
		String comfirmOrderList = req.getParameter("comfirmOrderList");
		System.out.println(comfirmOrderList);// 得出資料
		
		// 轉回字串
		Gson gson = new Gson();
		Type listType = new TypeToken<List<OrderItemDTO>>() {}.getType();
		List<OrderItemDTO> orders = gson.fromJson(comfirmOrderList, listType);

		// 搭配OrderItemDTO迴圈取值
		   OrdersService orderSvc = new OrdersServiceImpl();
		   Map<Integer, Integer> pubIDToTotalPrice = new HashMap<>();
		   Map<Integer, Integer> pubIDToTotalActCount = new HashMap<>();
		   
		    for (OrderItemDTO order : orders) {
		        // 獲取訂單訊息
		        int actID = order.getActID();
		        int pubID = order.getPubID();
		        int actPrice = order.getActPrice();
		        int discount = 0;//未修正
		        int actCount = order.getActCount();//未修正
		        int actPriceTotal = order.getActPriceTotal();//未修正
		        int discountPrice = actPriceTotal - discount;//未修正
		        String seatRowsColumns = order.getRowColumn();

		        // 獲取購買人User訊息
		        String currentUserID = (String) req.getSession().getAttribute("userID");
		        int userID = Integer.parseInt(currentUserID);
		        
		        // 創建訂單
		        OrdersVO orderVO = new OrdersVO();
		        UserVO userVO = new UserVO();
		        PublisherVO pubVO = new PublisherVO();
		        
		        if (pubIDToTotalPrice.containsKey(pubID)) {
		            int totalPrice = pubIDToTotalPrice.get(pubID);
		            totalPrice += actPriceTotal;
		            pubIDToTotalPrice.put(pubID, totalPrice);
		            int totalActCount = pubIDToTotalActCount.get(pubID);
		            totalActCount += actCount;
		            pubIDToTotalActCount.put(pubID, totalActCount);
		            
		        } else {
		            pubIDToTotalPrice.put(pubID, actPriceTotal);
		            pubIDToTotalActCount.put(pubID, actCount);
		        }
		        
		        if (pubIDToTotalPrice.containsKey(pubID)) {
		        	orderSvc.deleteOrdersbyUserIDandPubID(userID, pubID);
		            
		        	orderVO.setUserVO(userVO);
		            userVO.setUserID(userID);
		            orderVO.setOrdTotal(pubIDToTotalPrice.get(pubID));  // 設置成壘加後價格
		            orderVO.setDiscountPrice(pubIDToTotalPrice.get(pubID));// 設置成壘加後價格
		            Timestamp orderTime = new Timestamp(System.currentTimeMillis());
		            orderVO.setOrdTime(orderTime);
		            orderVO.setPublisherVO(pubVO);
		            pubVO.setPubID(pubID);
		            orderVO.setOrdType(3);
		            orderVO.setActQuantity(pubIDToTotalActCount.get(pubID));
		            
		      
		        } else {
		            // 如果不是同廠商，用原本邏輯
		            orderVO.setUserVO(userVO);
		            userVO.setUserID(userID);
		            orderVO.setOrdTotal(actPriceTotal);
		            orderVO.setDiscountPrice(actPriceTotal);
		            Timestamp orderTime = new Timestamp(System.currentTimeMillis());
		            orderVO.setOrdTime(orderTime);
		            orderVO.setPublisherVO(pubVO);
		            pubVO.setPubID(pubID);
		            orderVO.setOrdType(3);
		            orderVO.setActQuantity(actCount);     
	           
		        }        
		        
		        orderSvc.insertOrders(orderVO);

		        // 儲存訂單明細開始
		        OrderListService orderlistSvc = new OrderListServiceImpl();
		        ActVO actVO = new ActVO();

		        // 使用 splitSeatInfo 方法拆分座位
		        List<String> seats = splitSeatInfo(seatRowsColumns);
		        for (String seat : seats) {
		            
		        	OrderListVO orderListVO = new OrderListVO();
		        	
		        	// 儲存訂單明細
		        	// OrderID(還沒改)
		        	Integer newOrderID = orderSvc.getOrderBy(userID,pubID);
		            orderListVO.setOrdersVO(orderVO);
		            orderVO.setOrderID(newOrderID);
		            // ActID
		            orderListVO.setActVO(actVO);
		            actVO.setActID(actID);
		            // 其他
		            orderListVO.setActTotal(actPrice);
		            Timestamp orderListTime = new Timestamp(System.currentTimeMillis());
		            orderListVO.setOrderListTime(orderListTime);
		        	orderListVO.setSeatRowsColumns(seat);
		            orderListVO.setType(0);
		            
		            orderlistSvc.insert(orderListVO);
		        }
		    }

		    System.out.print("Order and Order Detail Inserted Successfully!");
		
		 // -----------Json重導---------//

			HttpSession session = req.getSession();
			String location = (String) session.getAttribute("location");
			session.removeAttribute("location");
			String redirectPath = location != null ? location : "checkout_checkbuy_1.html";

			Map<String, Object> jsonData = new HashMap<>();
			jsonData.put("location", redirectPath);
			String json = gson.toJson(jsonData);
			res.getWriter().write(json);

	
	}
	
	
	
	public static List<String> splitSeatInfo(String seatRowsColumns) {
	    List<String> seats = new ArrayList<>();
	    Pattern pattern = Pattern.compile("第.*?列");
	    Matcher matcher = pattern.matcher(seatRowsColumns);

	    while (matcher.find()) {
	        String seat = matcher.group();
	        seats.add(seat);
	    }

	    return seats;
	}


}
