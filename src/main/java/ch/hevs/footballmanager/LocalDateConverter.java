package ch.hevs.footballmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="localDateTimeConverter")
public class LocalDateConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return LocalDate.parse(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// Format date to display it
		LocalDate dateValue = (LocalDate) value;
		return dateValue.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
}