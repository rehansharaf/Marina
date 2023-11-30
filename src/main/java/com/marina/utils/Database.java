package com.marina.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	public static final String dbClassName= "com.mysql.cj.jdbc.Driver";
    Connection myCon = null;
    Statement mySt,mySt1;
    PreparedStatement myPst,myPst1;
    ResultSet rs1,rs2,rs3,rs4;
    String myQuery1,myQuery2;
    String mySQL,myUName,myPswd;
	
	public Database() {
		
		 	mySQL  	= "jdbc:mysql://40.122.188.55:3306/pgtest_marina2?autoReconnect=true&useSSL=false";
	    	myUName = "adnan_sharaf";
	    	myPswd  =  "MLM@Sharaf_@dnan!";
	    	try {
				Class.forName(dbClassName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    	try {
				myCon = DriverManager.getConnection(mySQL,myUName,myPswd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public void deleteReservation(String slipName) {
		
		checkDBConnection();
		
		try {
	
			myQuery1 = "select count(*) as count From pgtest_marina2.inventory_item where slip_id = ? and deleted_by is null ;";
	        myPst = myCon.prepareStatement(myQuery1);
	        myPst.setString(1, slipName);
	        rs1 = myPst.executeQuery();
	        rs1.next();
	        
	        int count = rs1.getInt("count");
	        
	        if(count > 0) {
	        	
	        	myQuery1 = "select * From pgtest_marina2.inventory_item where slip_id = ? and deleted_by is null ;";
		        myPst = myCon.prepareStatement(myQuery1);
		        myPst.setString(1, slipName);
		        rs1 = myPst.executeQuery();
		        rs1.next();
		        
		        String inventory_id = rs1.getString("inventory_id");
		        
		        myQuery1 = "select count(*) as count From pgtest_marina2.reservations where slip_id = ? ;";
		        myPst = myCon.prepareStatement(myQuery1);
		        myPst.setString(1, inventory_id);
		        rs1 = myPst.executeQuery();
		        rs1.next();
		        
		        int count_reservation = rs1.getInt("count");
		        
		        if(count_reservation > 0) {
		        	
		        	    myQuery1 = "select * From pgtest_marina2.reservations where slip_id = ? ;";
				        myPst = myCon.prepareStatement(myQuery1);
				        myPst.setString(1, inventory_id);
				        rs1 = myPst.executeQuery();
				        rs1.next();
				        
				        int reservation_id = rs1.getInt("id");
				        
				         mySt = myCon.createStatement ();
						 myQuery1 = "delete from pgtest_marina2.reservations where id = ? ;";
						 myPst = myCon.prepareStatement (myQuery1);
						 myPst.setInt(1, reservation_id);
						 myPst.executeUpdate();
						 
						 mySt = myCon.createStatement ();
						 myQuery1 = "delete from pgtest_marina2.orders where reservation_id = ? ;";
						 myPst = myCon.prepareStatement (myQuery1);
						 myPst.setInt(1, reservation_id);
						 myPst.executeUpdate();
		        	
		        }
		        
	        	
	        }
	        
	        myCon.close();
	        
		}catch(SQLException sql) {
			
			
		}
	
		
	}
	
	public int getPricingDaily(String rateGroupName, int year, int month, int day) {

		checkDBConnection();
		int dayRate = 0;
		try {
			myQuery1 = "select count(*) as count From pgtest_marina2.price_groups where name = ? ;";
	        myPst = myCon.prepareStatement(myQuery1);
	        myPst.setString(1, rateGroupName);
	        rs1 = myPst.executeQuery();
	        rs1.next();
	        
	        int count = rs1.getInt("count");
	        if(count > 0) {
	        	
	        	myQuery1 = "select id From pgtest_marina2.price_groups where name = ? ;";
	            myPst = myCon.prepareStatement(myQuery1);
	            myPst.setString(1, rateGroupName);
	            rs1 = myPst.executeQuery();
	            rs1.next();
	            
	            long groupID = rs1.getLong("id");
	            
	            Long[][] bucket_detail = new Long[3][2];
	            int index = 0;
	            myQuery1 = "select * from pgtest_marina2.boat_buckets where type = ? ;";
	            myPst = myCon.prepareStatement(myQuery1);
	            myPst.setLong(1, groupID);
	            rs1 = myPst.executeQuery();
	            while(rs1.next()) {
	            	
	            	bucket_detail[index][0] = rs1.getLong("id");
	            	bucket_detail[index][1] = rs1.getLong("max");
	            	index++;
	            }
	            
	            //select * from pricing_daily where group_id = 1 and year = 2023 and month = 11 and bucket_id = 81 order by id desc;
	            
	            myQuery1 = "select * from pricing_daily where group_id = ? and year = ? and month = ? and bucket_id = ? order by id desc;";
	            myPst = myCon.prepareStatement(myQuery1);
	            myPst.setLong(1, groupID);
	            myPst.setInt(2, year);
	            myPst.setInt(3, month);
	            myPst.setLong(4, bucket_detail[0][0]);
	            rs1 = myPst.executeQuery();
	            rs1.next();
	            
	            dayRate = rs1.getInt("day_"+day);
	           
	            
	        }
			
	        myCon.close();
	        
		}catch(SQLException sqe) {}
		
		return dayRate;
		
	}
	
	
	public void checkDBConnection() {
		try {
		
			myQuery1 = "select * From pgtest_marina2.login_tokens limit 1 ;";
	        myPst = myCon.prepareStatement(myQuery1);
	        rs1 = myPst.executeQuery();
	        rs1.next();
	        
		}catch(SQLException sqe) {
			
			mySQL  	= "jdbc:mysql://40.122.188.55:3306/pgtest_marina2?autoReconnect=true&useSSL=false";
	    	myUName = "adnan_sharaf";
	    	myPswd  =  "MLM@Sharaf_@dnan!";
	    	try {
				Class.forName(dbClassName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    	try {
				myCon = DriverManager.getConnection(mySQL,myUName,myPswd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
