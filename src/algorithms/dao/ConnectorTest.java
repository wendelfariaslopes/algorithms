package algorithms.dao;
//package jportfolio.com.database;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//
//
//public class ConnectorTest {
//	
//	public Category select(Long id) {
//
//		Connection conn = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//
//		Category obj = null;
//
//		try {
//
//			conn = Conexao.getConnection();
//
//			pst = conn.prepareStatement(SELECT_ID);
//			pst.setLong(1, id);
//
//			rs = pst.executeQuery();
//
//			if (rs.next()) {
//
//				obj = new Category();
//				
//					obj.setId(rs.getLong("id"));
//					obj.setIdFathe(rs.getLong("id_father"));
//					obj.setNamed(rs.getString("named"));
//					obj.setDescription(rs.getString("description"));
//					obj.setIdLanguage(rs.getLong("id_language"));
//					obj.setIdReference(rs.getLong("id_reference"));
//					
//			} 
//
//		} catch (Exception e) {
//
//			System.out.println(e.getMessage());
//
//		} finally {
//			Conexao.closeConnection(conn, pst, rs);
//		}
//
//		return obj;
//	}
//	
//	public boolean insert(Category obj) {
//
//		Connection conn = null;
//		PreparedStatement pst = null;
//
//		boolean status = false;
//
//		try {
//
//			conn = Conexao.getConnection();
//
//			pst = conn.prepareStatement(INSERT);
//
//			pst.setString(1, Convert.stringToStrPattern(obj.getNamed()));
//			pst.setString(2, obj.getDescription());
//			pst.setLong(3, obj.getIdFathe());
//			pst.setLong(4, obj.getIdLanguage());
//			pst.setLong(5, obj.getIdReference());
//
//			if(pst.executeUpdate()==1)
//			  status = true;
//
//		} catch (Exception e) {
//			System.out.println("Erros: \n" + e.getMessage());
//			status = false;
//		} finally {
//			Conexao.closeConnection(conn, pst, null);
//		}
//
//		return status;
//	}
//	
//
//}
