package ch.hevs.footballmanager;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.League;

@FacesConverter("leagueConverter")
public class LeagueConverter implements Converter {
	
	
	private Football foot;	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }
		
		try {
			// use JNDI to inject reference to bank EJB
			InitialContext ctx = new InitialContext();
			foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
			League league = foot.getLeagueById(Long.valueOf(submittedValue)) ; 
            return league;
        } catch (NumberFormatException | NamingException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid League ID", submittedValue)), e);
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof League) {
            return String.valueOf(((League) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid League", modelValue)));
        }
	}
}