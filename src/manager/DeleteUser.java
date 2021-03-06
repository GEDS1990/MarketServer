package manager;
import java.io.BufferedReader;
import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import com.sun.javafx.collections.MappingChange.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;

public class DeleteUser extends HttpServlet{
	private ResultSet r;
	private static Connection conn = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		 int deleteResult;//方法变量定义

		String userId;//输入
	 
	 
	 conn=login.Login.getCon();
	 
	 System.out.println("manager被访问了");
	 
	 req.setCharacterEncoding("utf-8");
	 resp.setCharacterEncoding("utf-8"); 

	 userId = req.getParameter("id");////key -value get方式获取url的键值对 
	  
	PrintWriter out=resp.getWriter();//输出流获取
	
	 JSONObject ret_obj = new JSONObject();

	 
       try {    
       String managerDeleteUser_delete = "DELETE  FROM market.user"
          		+ " where user_id = "+userId;
       PreparedStatement stmt = conn.prepareStatement(managerDeleteUser_delete);     
       deleteResult= stmt.executeUpdate(); 
  
   	
   	if(deleteResult==0) {  
		  ret_obj.put("status", false);
		  ret_obj.put("message", "删除会员失败");
	}else {
	   	ret_obj.put("status",true);
     } 
       stmt.close();
       
       }catch (SQLException e) {  	
        	  e.printStackTrace();

          } finally { 
               try {
                   conn.close(); 
              } catch(SQLException e) {
                   e.printStackTrace();
              }
          }
         
        out.print(ret_obj.toString());  
        out.flush();  
       
    }  
 
 @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        this.doGet(req, resp);  
    }  
}
