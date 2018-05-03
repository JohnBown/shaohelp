package com.etc.jump.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {

	//驱动类名称
	public  static final String DRIVER="com.mysql.jdbc.Driver";
	//连接的数据库地址
	public  static final String PATH="jdbc:mysql://172.0.0.1:3306/shaohelp";
	//用户名
	public  static final String NAME="root";
	//密码
	public static final String PWD="1234";

	//连接对象
	private Connection con;

	//预编译语句对象
	private PreparedStatement ps;

	//结果集对象
	private ResultSet rs;

	/**获取连接的对象
	 * @author Administrator
	 * @since 2018年4月8日14:01:21
	 * @return 连接对象
	 */
	public Connection getCon(){
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(PATH, NAME, PWD);
		} catch (Exception e) {
			System.out.println("连接数据库异常，原因："+e.getMessage());
		}
		return con;
	}


	/**
	 * 关闭数据库连接的方法
	 */
	public void closeAll(){
		try {
			if(rs!=null){
				rs.close();
			}		
			if(ps!=null){
				ps.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (Exception e) {
			System.out.println("关闭数据库时发生异常，异常原因："+e.getMessage());
		}
	}

	/**
	 * 增删改通用方法
	 * @param sql   增删改语句
	 * @param obj   动态参数
	 * @return      受影响行数
	 */
	public int update(String sql,Object...obj){
		int result=0; //受影响行数
		try {
			con=getCon(); //获取连接对象
			ps=con.prepareStatement(sql); //获取预编译语句对象
			if(obj!=null){
				//遍历动态数组
				for(int i=0;i<obj.length;i++){
					//设置？对应的参数
					ps.setObject(i+1, obj[i]);
				}
			}
			//执行sql语句，返回受影响行数
			result=ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("执行sql语句发生异常，异常原因："+e.getMessage());
		}finally{
			closeAll();
		}
		return result;
	}

	/**
	 * 查询的通用方法
	 * @param sql  查询语句
	 * @param obj  动态参数
	 * @return     结果集
	 */
	public  ResultSet query(String sql,Object...obj){
		try {
			con=getCon();
			ps=con.prepareStatement(sql);
			if(obj!=null){
				//遍历动态数组
				for(int i=0;i<obj.length;i++){
					//设置？对应的参数
					ps.setObject(i+1, obj[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			System.out.println("执行sql语句发生异常，异常原因："+e.getMessage());
		}
		//不能关闭连接，因为结果集的数据还没取出，等外部方法取出结果集的数据后再调用closeall方法
		return rs;
	}

}
