package com.assignment.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StockDAO {

	public static ArrayList<StockApiBean> getWatchList(String username) {
		ArrayList<StockApiBean> watchlist = new ArrayList<StockApiBean>();
		Connection con = null;
        PreparedStatement pstmt = null;
		ResultSet rs =null;
        try {
            con = DataConnect.getConnection();
            String sql = "select stockname, symbol from mystock.watchlist where username =? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			 while (rs.next()) {
				 StockApiBean stock = new StockApiBean();
				stock.setMyStockName(rs.getString("stockname"));
				stock.setWatchListSymbol(rs.getString("symbol"));
				watchlist.add(stock);
            	

            }

        } catch(Exception ex) {
			 System.out.println("Unable to update. An Exception has occurred! " + ex);
		 }
		finally
		{
			if(con!=null)
			{
				try { 
					 con.close(); 
					 } 
				catch (Exception e1) { 
						 
					 } 
				 con = null;
		   }
	  }
	    return watchlist;
	}

	public static Double getBalance(String username) {
		Double balance = null;
		Connection con = null;
        PreparedStatement pstmt = null;
		ResultSet rs =null;
        try {
            con = DataConnect.getConnection();
            String sql = "select balance from mystock.registration where username =? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			 while (rs.next()) {
				 
				balance =rs.getDouble("balance");
				
            	

            }

        } catch(Exception ex) {
			 System.out.println("Unable to update. An Exception has occurred! " + ex);
		 }
		finally
		{
			if(con!=null)
			{
				try { 
					 con.close(); 
					 } 
				catch (Exception e1) { 
						 
					 } 
				 con = null;
		   }
	  }
	    return balance;

}	
	

}
