package com.tha103.newview.act.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tha103.newview.act.model.ActDAO;
import com.tha103.newview.act.model.ActDAOHibernateImpl;
import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.actcategory.model.ActCategory;
import com.tha103.newview.actpic.model.ActPic;

@WebServlet("/GetActsWithPicsServlet")
public class ListActsWithPicsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
        ActDAO dao = new ActDAOHibernateImpl();      
        List<ActVO> actList = dao.getActPics();

        
        List<ActWithPicsDTO> actWithPicsList = new ArrayList<>();

        for (ActVO act : actList) {
            Hibernate.initialize(act.getActpics());
            

            
            Set<ActPic> actPics = act.getActpics();
            List<String> base64Images = new ArrayList<>(); 

            for (ActPic pic : actPics) {
                

              
                if (pic.getActPic() != null) {
                    byte[] picBytes = pic.getActPic();
                    String base64Image = Base64.getEncoder().encodeToString(picBytes);
                    base64Images.add(base64Image); 
                } else {
                    System.out.println("NULL");
                }
            }

            
            ActWithPicsDTO actWithPicsDTO = new ActWithPicsDTO();
            actWithPicsDTO.setActDate(act.getActDate());
            System.out.println(act.getActDate());
            actWithPicsDTO.setTime(act.getTime());
            actWithPicsDTO.setActID(act.getActID());
            System.out.println(act.getActID());
            actWithPicsDTO.setActScope(act.getActScope());
            System.out.println(act.getActScope());
            actWithPicsDTO.setActName(act.getActName());
            actWithPicsDTO.setActContent(act.getActContent());
            actWithPicsDTO.setActPrice(act.getActPrice());
            actWithPicsDTO.setActIntroduce(act.getActIntroduce());
            actWithPicsDTO.setBase64Images(base64Images); 
            actWithPicsDTO.setActCategoryName(act.getActCategoryID().getActCategoryName());
            actWithPicsDTO.setActAddress(act.getCityAddress());
            actWithPicsDTO.setCityAddress(act.getCityAddressID().getCityName());

            
            actWithPicsList.add(actWithPicsDTO);

            System.out.println("-----------------------");
        }
       
        Gson gson = new Gson();
        String jsonData = gson.toJson(actWithPicsList);

        
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        PrintWriter out = res.getWriter();
        out.print(jsonData);
        out.flush();
    }
}
