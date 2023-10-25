package lab.album.model.converter;

import java.time.LocalDate;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalDate.class, managed = true)
public class DateModelConverter implements Converter<LocalDate> {

    @Override
    public LocalDate getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        if (value == null || value.isEmpty())
            return null; 
        return LocalDate.parse(value);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, LocalDate value) {
        return value == null ? "" : value.toString();
    }

}
