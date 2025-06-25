package toll.tcm.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetExemptTypeNew {
	static Connection connection ;
	public static void main(String[] args) throws SQLException {
		String CD="04";
		
		String sql="select * from (select  t.version_no,m.exempt_type, rank() over (order by t.version_no desc) as available_exempt from  mvw_toll_exempt_type t,mvw_kent_exempt_type_master m where t.kent_exempt_id=m.kent_exempt_id  and t.vehicle_cd like '%"+CD+"' and t.toll_id= (select toll_id from mvw_kent_present_toll) group by m.exempt_type,t.version_no) h where available_exempt=1";
		Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String Exempt_Type="";
        while (resultSet.next()) {
            // Retrieve data from each row for the specific column
           Exempt_Type = resultSet.getString("Exempt_Type"); // Replace with the actual column name
            System.out.println(Exempt_Type);
            // Process the retrieved data
           
            
           
        }
	}

}
