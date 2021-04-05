package nishitachin_CSCI201L_Lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void printStudents() {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lab9?user=root&password=MediScope3155");
			st = conn.createStatement();
			rs = st.executeQuery("select count(*) numofstudents, grades.classname as classname from grades group by grades.classname order by numofstudents");
			System.out.println("Student Table");
			while (rs.next()) {
				String column1 = rs.getString("numofstudents");
				String column2 = rs.getString("classname");
//				String column3 = rs.getString("grades");
				System.out.println(column1 + "\t" + column2);
			}
//			
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}

	public static void printGrades() {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lab9?user=root&password=MediScope3155");
			st = conn.createStatement();
			rs = st.executeQuery("select s.name, g.classname, g.grade FROM studentinfo s, grades g WHERE s.sid = g.sid");
			System.out.println("\nGrades");
			while (rs.next()) {
				String column1 = rs.getString("classname");
				String column2 = rs.getString("name");
				String column3 = rs.getString("grade");
				System.out.println(column1 + "\t" + column2 + "\t" + column3);
			}
//			
		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		printStudents();
		printGrades();
	}
}
