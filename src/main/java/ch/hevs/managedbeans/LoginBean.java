package ch.hevs.managedbeans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.footballmanager.Football;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	public String logout() throws IOException {

	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.invalidateSession();

	    return "logout";
	}
	
	//Interface
	private Football foot;
	
	private long id;
	private String firstname;
	private String lastname;
	private President person;
	
	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
	}
	//TODO Modify to get a person by ID
	public void getPerson(){		
		this.person = foot.getPresidentById(id);
	}
	
	public String validateLoginCredentials() {
		String validationResult = "";
		Person pers=null;
		try {
			pers = foot.login(firstname, lastname);
		} catch (Exception e) {
			validationResult = "login";
			FacesContext.getCurrentInstance().addMessage("loginForm:firstname", new FacesMessage("Username Or Password Is Incorrect"));
		}
		
		if ((pers != null) &&
			 (firstname.equalsIgnoreCase(pers.getFirstname()) &&
					 lastname.equals(pers.getLastname()))) {
		 validationResult ="index";
		 id=pers.getId();
		}else
		{
		 validationResult = "login";
		 FacesContext.getCurrentInstance().addMessage("loginForm:loginName", new FacesMessage("FirstName Or Lastname Is Incorrect"));
		}
		
		return validationResult;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	

}
