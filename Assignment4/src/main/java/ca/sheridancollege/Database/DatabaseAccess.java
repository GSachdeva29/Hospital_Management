package ca.sheridancollege.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Doctor;
import ca.sheridancollege.beans.DoctorEdit;
import ca.sheridancollege.beans.User;

@Repository
public class DatabaseAccess 
{
	 @Autowired
	  protected NamedParameterJdbcTemplate jdbc;
	  
	  public void addPatient(Doctor doctor)
	  {
		  MapSqlParameterSource parameters = new MapSqlParameterSource();
			
			String query = "Insert into doctor (pId,  firstName,lastName,email,phone,doctor) values" + 
					  "(:pId, :firstName, :lastName, :email, :phone, :doctor)";
		    
			parameters.addValue("pId",doctor.getPId());
			parameters.addValue("firstName", doctor.getFirstName());
			parameters.addValue("lastName", doctor.getLastName());
			parameters.addValue("email", doctor.getEmail());
			parameters.addValue("phone",  doctor.getPhone());
			parameters.addValue("doctor", doctor.getDoctor());
			jdbc.update(query, parameters);
	  }
	  
	  public ArrayList<Doctor> getPatients()
	  { 
	  String  query="SELECT * FROM doctor ";
	  ArrayList<Doctor> hospitals=(ArrayList<Doctor>)jdbc.query(query,new
	  BeanPropertyRowMapper<Doctor>(Doctor.class)); 
	  return hospitals;
	  }
	  
	  public Doctor getPatientsByID(int pId)
		{
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM doctor WHERE  pId= :pId ";
			
			parameters.addValue("pId", pId);
			
			  ArrayList<Doctor> doctor=(ArrayList<Doctor>)jdbc.query(query,parameters, new
					  BeanPropertyRowMapper<Doctor>(Doctor.class));
			if(doctor.size()>0)
			{
				return doctor.get(0);
			}
			else
			return null;
		}
	  
	  public void editPatient(Doctor doctor)
		{
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			
			String query = "UPDATE doctor SET  firstName = :firstName,lastName = :lastName,email= :email,phone= :phone,"
					+ "doctor= :doctor WHERE pId= :pId";
		    
			parameters.addValue("pId", doctor.getPId());
			parameters.addValue("firstName", doctor.getFirstName());
			parameters.addValue("lastName", doctor.getLastName());
			parameters.addValue("email", doctor.getEmail());
			parameters.addValue("phone", doctor.getPhone());
			parameters.addValue("doctor", doctor.getDoctor());
			
			jdbc.update(query, parameters);
		
		}
	  public void editPatientDetail(Doctor doctor)
	  {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			Doctor d = getPatientsByID(doctor.getPId());
			
			String query = "UPDATE doctor SET  vDate = :vDate,vReason = :vReason,vFeedback= :vFeedback,vPrescription= :vPrescription,"
					+ "vNext= :vNext WHERE pId= :pId";
		    
			parameters.addValue("pId", doctor.getPId());
			parameters.addValue("vDate", doctor.getVDate());
			parameters.addValue("vReason", doctor.getVReason());
			parameters.addValue("vFeedback", doctor.getVFeedback());
			parameters.addValue("vPrescription", doctor.getVPrescription());
			parameters.addValue("vNext", doctor.getVNext());
			
			jdbc.update(query, parameters);
		
		}
		public void deletePatientsById(int pId)
		{
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query="Delete from doctor WHERE pId=:pId";
			parameters.addValue("pId", pId);
			jdbc.update(query,parameters);
		
		}
		
		public void Dummy()
		  {
			  MapSqlParameterSource parameters = new MapSqlParameterSource();
				String query = "Insert into doctor (pId,firstName,lastName,email,phone,doctor, vDate,vReason,vFeedback,vPrescription,vNext) values" +
			            "('1','Gaurav','Sachdeva','gs@gmail.com','6476732287','Harleen','4/5/2019','eye infection','null','just started','7/5/2019'),"+
						"('2','Gagan','Singh','gagans@gmail.com','6475672287','Saman','4/3/2019','shoulder pain','null','extreme pain','7/3/2019'),"+
						"('3','Neha','Sachdeva','nehas@gmail.com','6476731234','Divjot','2/2/2019','fever','null','null','5/2/2019'),"+
						"('4','Rupinder','Sharma','rupis@gmail.com','6476756787','Divjot','4/5/2016','typhoid','null','just started','7/5/2016'),"+
						"('5','Ramya','Gajula','rgajula@gmail.com','6476739997','Saman','7/9/2018','ear infection','null','null','11/9/2018'),"+
						"('6','Gurpartaap','Gill','ggill@gmail.com','6476123457','Saman','4/1/2017','fever','null','just started','7/1/2017'),"+
						"('7','Priyanka','Sekhon','priyankas@gmail.com','6345632287','Kamal','4/5/2019','TB','null','3rd stage','7/5/2019'),"+
						"('8','Anmol','Sachdeva','anmols@gmail.com','6476738887','Harleen','4/3/2019','eye pain','null','eye-sight test','7/3/2019'),"+
						"('9','Ishaan','Sharma','isharma@gmail.com','6476755557','Kamal','6/5/2019','ankle broken','null','scanning','9/5/2019'),"+
						"('10','Ashu','Kaur','kaurashu@gmail.com','6476731111','Saman','2/2/2018','eye infection','null','just started','5/2/2018'),"+
						"('11','Gaurav','Sachdeva','gs@gmail.com','6476732287','Harleen','4/5/2019','eye infection','null','just started','7/5/2019'),"+
						"('12','Gagan','Singh','gagans@gmail.com','6475672287','Saman','4/3/2019','shoulder pain','null','extreme pain','7/3/2019'),"+
						"('13','Neha','Sachdeva','nehas@gmail.com','6476731234','Divjot','2/2/2019','fever','null','null','5/2/2019'),"+
						"('14','Rupinder','Sharma','rupis@gmail.com','6476756787','Divjot','4/5/2016','typhoid','null','just started','7/5/2016'),"+
						"('15','Ramya','Gajula','rgajula@gmail.com','6476739997','Saman','7/9/2018','ear infection','null','null','11/9/2018'),"+
						"('16','Gurpartaap','Gill','ggill@gmail.com','6476123457','Saman','4/1/2017','fever','null','just started','7/1/2017'),"+
						"('17','Priyanka','Sekhon','priyankas@gmail.com','6345632287','Kamal','4/5/2019','TB','null','3rd stage','7/5/2019'),"+
						"('18','Anmol','Sachdeva','anmols@gmail.com','6476738887','Harleen','4/3/2019','eye pain','null','eye-sight test','7/3/2019'),"+
						"('19','Ishaan','Sharma','isharma@gmail.com','6476755557','Kamal','6/5/2019','ankle broken','null','scanning','9/5/2019'),"+
						"('20','Ashu','Kaur','kaurashu@gmail.com','6476731111','Saman','2/2/2018','eye infection','null','just started','5/2/2018')";
				jdbc.update(query,parameters);
		  }
		
		public Doctor getPatientsByFirstName(String firstName)
		{
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM doctor WHERE  firstName= :firstName ";
			
			parameters.addValue("firstName", firstName);
			
			  ArrayList<Doctor> doctor=(ArrayList<Doctor>)jdbc.query(query,parameters, new
					  BeanPropertyRowMapper<Doctor>(Doctor.class));
			if(doctor.size()>0)
			{
				return doctor.get(0);
			}
			else
			return null;
		}
		
		public Doctor getPatientsByLastName(String lastName)
		{
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM doctor WHERE  lastName= :lastName ";
			
			parameters.addValue("lastName", lastName);
			
			  ArrayList<Doctor> doctor=(ArrayList<Doctor>)jdbc.query(query,parameters, new
					  BeanPropertyRowMapper<Doctor>(Doctor.class));
			if(doctor.size()>0)
			{
				return doctor.get(0);
			}
			else
			return null;
		}
		
		public Doctor getPatientsByEMail(String email)
		{
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM doctor WHERE  email= :email ";
			
			parameters.addValue("email", email);
			
			  ArrayList<Doctor> doctor=(ArrayList<Doctor>)jdbc.query(query,parameters, new
					  BeanPropertyRowMapper<Doctor>(Doctor.class));
			if(doctor.size()>0)
			{
				return doctor.get(0);
			}
			else
			return null;
		}
		public Doctor getPatientsByPhone(String phone)
		{
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM doctor WHERE  phone= :phone ";
			
			parameters.addValue("phone", phone);
			
			  ArrayList<Doctor> doctor=(ArrayList<Doctor>)jdbc.query(query,parameters, new
					  BeanPropertyRowMapper<Doctor>(Doctor.class));
			if(doctor.size()>0)
			{
				return doctor.get(0);
			}
			else
			return null;
		}
		public User findUserAccount(String userName) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "SELECT * FROM sec_user where userName=:userName";
			parameters.addValue("userName", userName);
			ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters,
					new BeanPropertyRowMapper<User>(User.class));
			if (users.size()>0)
				return users.get(0);
			else
				return null;
		}
		
		public List<String> getRolesById(long userId) {
			ArrayList<String> roles = new ArrayList<String>();
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			String query = "select user_role.userId, sec_role.roleName "
					+ "FROM user_role, sec_role "
					+ "WHERE user_role.roleId=sec_role.roleId "
					+ "and userId=:userId";
			parameters.addValue("userId", userId);
			List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
			for (Map<String, Object> row : rows) {
				roles.add((String)row.get("roleName"));
			}
			return roles;
		}
		
		public static void addUser(String userName, String encryptedPassword) {
			try {
	            		Class.forName("com.mysql.cj.jdbc.Driver");
	            		Connection conn = null; 
	            		conn = DriverManager.getConnection
	            		("jdbc:mysql://localhost/hospital", "root", "root");        
				String q = 
					"insert into SEC_User "
					+ "(USERNAME, ENCRYPTEDPASSWORD, ENABLED)" 
					+ " values (?,?,1)";
				PreparedStatement ps = conn.prepareStatement(q);
				
				ps.setString(1, userName);
				ps.setString(2, encryptedPassword);
				ps.executeUpdate();	
				
	            		conn.close();
	        	} catch (Exception e) {
	            		System.out.println(e);
	        	}
		}
		
		public static void addRole(long userId, long roleId) {
			try {
	       		Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null; 
				conn = DriverManager.getConnection
					("jdbc:mysql://localhost/hospital", "root", "root");
				String q = 
					"insert into user_role (USERID, ROLEID)" + 
					"values (?, ?);";
				PreparedStatement ps = conn.prepareStatement(q);
				
				ps.setLong(1, userId);
				ps.setLong(2, roleId);
				ps.executeUpdate();	
				
	            		conn.close();
	        	} catch (Exception e) {
	            		System.out.println(e);
	        	}
		}
}
