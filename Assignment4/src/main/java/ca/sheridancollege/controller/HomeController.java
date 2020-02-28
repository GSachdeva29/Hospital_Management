package ca.sheridancollege.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.Database.DatabaseAccess;
import ca.sheridancollege.beans.Doctor;
import ca.sheridancollege.beans.DoctorEdit;

@Controller
public class HomeController 
{
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
  public String RHome()
  {
	  return "Home.html";
  }
	@GetMapping("/Register1")
	  public String register()
	  {
		  return "/Registrar/Register.html";
	  }
	@GetMapping("/RAddPage")
	public String goNew(@RequestParam int pId,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email,
			@RequestParam String phone,@RequestParam String doctor)
	{
		Doctor d = new Doctor();
		d.setPId(pId);
		d.setFirstName(firstName);
		d.setLastName(lastName);
		d.setEmail(email);
		d.setPhone(phone);
		d.setDoctor(doctor);
		da.addPatient(d);
	    return "/Registrar/Register.html";
	}
	
	@GetMapping("/view")
	public String goViewManagement(Model model)
	{
	    model.addAttribute("doctor", da.getPatients());
		return "/Registrar/view.html";
	}
	
	
	
	@GetMapping("/edit/{pId}")
	public String editContact(@PathVariable int pId,Model model)
	
	{
		Doctor d = da.getPatientsByID(pId);
		
		model.addAttribute("doctor",d);
		return "/Registrar/edit.html";
	}
	
	@GetMapping("/modify")
	public String editPatient(@RequestParam int pId,@RequestParam String firstName,@RequestParam String lastName,
			@RequestParam String email, @RequestParam String phone,@RequestParam String doctor, Model model)
	{
		Doctor d = new Doctor();
		d.setPId(pId);
		d.setFirstName(firstName);
		d.setLastName(lastName);
		d.setEmail(email);
		d.setPhone(phone);
		d.setDoctor(doctor);
		da.editPatient(d);
		return "/Registrar/Register.html";
	}
	
	@GetMapping ("/delete/{pId}")
	public String deleteBook(@PathVariable int pId)
	{
		Doctor h=da.getPatientsByID(pId);
		da.deletePatientsById(pId);
		return "/Registrar/Register.html";
	}
	
	@GetMapping("/viewDoctor")
	public String goViewDoctor(Model model)
	{
	    model.addAttribute("doctor", da.getPatients());
		return "/Doctor/view.html";
	}
	
	@GetMapping("/select/{pId}")
	public String goEditDoctor(@PathVariable int pId, Model model)
	{
	    model.addAttribute("doctorEdit", da.getPatientsByID(pId));
		return "/Doctor/edit.html";
	}
	
	@GetMapping("/modifyDetails")
	public String editPatientDetails(@RequestParam int pId,@RequestParam String vDate,@RequestParam String vReason,
			@RequestParam String vFeedback, @RequestParam String vPrescription,@RequestParam String vNext, Model model)
	{
		Doctor d = new Doctor();
		d.setPId(pId);
		d.setVDate(vDate);
		d.setVReason(vReason);
		d.setVFeedback(vFeedback);
		d.setVPrescription(vPrescription);
		d.setVNext(vNext);
		da.editPatientDetail(d);
		 model.addAttribute("doctor", da.getPatientsByID(pId));
		return "/Doctor/info.html";
	}
	@GetMapping("/dummy")
	public String goDummy()
	{	
		da.Dummy();
		return "Home.html";
	}
	
	@GetMapping("/search")
	public  String goSearch()
	{
		return "/Registrar/SearchByCardId.html";
	}
	@GetMapping("/searchFName")
	public  String goSearchFName()
	{
		return "/Registrar/SearchByFName.html";
	}
	@GetMapping("/searchLName")
	public  String goSearchLName()
	{
		return "/Registrar/SearchByLName.html";
	}
	@GetMapping("/searchEMail")
	public  String goSearchEMail()
	{
		return "/Registrar/SearchByEmail.html";
	}
	@GetMapping("/searchPhone")
	public  String goSearchPhone()
	{
		return "/Registrar/SearchByPhone.html";
	}
	
	@GetMapping("/id")
	public String searchBookId(@RequestParam int pId,Model model) 
	{		
		Doctor d = da.getPatientsByID(pId);
		model.addAttribute("doctor",d);
		return "/Registrar/search.html";
    }
	@GetMapping("/fName")
	public String searchBookFName(@RequestParam String firstName,Model model) 
	{		
		Doctor d = da.getPatientsByFirstName(firstName);
		model.addAttribute("doctor",d);
		return "/Registrar/search.html";
    }
	@GetMapping("/lName")
	public String searchBookLName(@RequestParam String lastName,Model model) 
	{		
		Doctor d = da.getPatientsByLastName(lastName);
		model.addAttribute("doctor",d);
		return "/Registrar/search.html";
    }
	@GetMapping("/email")
	public String searchBookEMail(@RequestParam String email,Model model) 
	{		
		Doctor d = da.getPatientsByEMail(email);
		model.addAttribute("doctor",d);
		return "/Registrar/search.html";
    }
	@GetMapping("/phone")
	public String searchBookPhone(@RequestParam String phone,Model model) 
	{		
		Doctor d = da.getPatientsByPhone(phone);
		model.addAttribute("doctor",d);
		return "/Registrar/search.html";
    }
	
	 @GetMapping("/login")
	    public String login() {
	        return "login.html";
	    }

	    @GetMapping("/access-denied")
	    public String accessDenied() {
	        return "access_denied.html";
	    }
		@GetMapping("/register")
		public String goRegistration () {
			return "register.html";
		}
		@PostMapping("/register")
		public String doRegistration(@RequestParam String username,
							@RequestParam String password, @RequestParam String user) {
			DatabaseAccess.addUser(username, encryptPassword(password));
			long userId = da.findUserAccount(username).getUserId();
			if(user.contains("management")) {
			DatabaseAccess.addRole(userId, 1);
			}
			if(user.contains("doctor")) {
			DatabaseAccess.addRole(userId, 2);
			} 
			return "redirect:/login";  
		}
		public static String encryptPassword(String password) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder.encode(password);
		}

}
