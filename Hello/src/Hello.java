

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
/**
 * @author XCream
 *
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hello() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			Connection conn = DriverManager.getConnection(url, "hr", "ysqewb");
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM DEP_DETAILS_VIEW");
			response.getWriter().append(getRsTable(rs));
			response.getWriter().append("<br/>Printed at:"+new Date());
			rs.close();
			stm.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @param rs(ResultSet)
	 * @return (String) table html from ResultSet
	 */
	private String getRsTable(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			if (count < 1)
				return null;
			String[] name = new String[count];
			for (int i = 0; i < count; i++)
				name[i] = rsmd.getColumnName(i + 1);
			String tableStr = "<table style='font-size:12px' cellpadding=8 border=1 cellspacing=0><tr bgcolor=yellow>";
			for (int c = 0; c < count; c++) {
				tableStr += "<td><B>" + name[c] + "</B></td>";
			}
			tableStr += "</tr>";
			while (rs.next()) {
				tableStr += "<tr>";
				for (int c = 1; c <= count; c++) {
					tableStr += "<td>" + rs.getString(c) + "</td>";
				}
				tableStr += "</tr>";
			}
			return tableStr + "</table>";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
