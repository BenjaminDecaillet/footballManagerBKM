package ch.hevs.managedbeans;

import java.io.IOException;
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
	}
	
    /**
     * Logs the current user out by invalidating the session. redirect him to index page
     * @throws IOException 
     */
    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect("index.xhtml");
    }
}
