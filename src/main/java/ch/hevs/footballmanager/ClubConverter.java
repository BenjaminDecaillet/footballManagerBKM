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

@FacesConverter("clubConverter")
public class ClubConverter implements Converter {
	
	
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
			Club club = foot.getClubById(Long.valueOf(submittedValue)) ; 
            return club;
        } catch (NumberFormatException | NamingException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Club ID", submittedValue)), e);
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Club) {
            return String.valueOf(((Club) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Club", modelValue)));
        }
	}
}