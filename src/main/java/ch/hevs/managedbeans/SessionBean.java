package ch.hevs.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class SessionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8000710510234249415L;
	
	public SessionBean() {
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Logs the current user out by invalidating the session.
     * @return &quot;logout&quot; which is used by the {@literal faces-config.xml}
     * to redirect back to the {@literal index.xhtml} page.
     */
    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        
        return "logout";
    }
}
