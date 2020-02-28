package ca.sheridancollege.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1901563381857983966L;
	  private int pId;
	 	 private String firstName;
		 private String lastName;
		 private String email;
		 private String phone;
		 private String doctor;
		  private String vDate;
		  private String vReason;
		  private String vFeedback;
		  private String vPrescription;
		  private String vNext;
		

		 
}
