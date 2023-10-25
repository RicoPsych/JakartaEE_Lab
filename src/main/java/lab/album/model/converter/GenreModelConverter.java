package lab.album.model.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import lab.album.entities.Album.Genre;

@FacesConverter(forClass = Genre.class, managed = true)
public class GenreModelConverter implements Converter<Genre> {

    @Override
    public Genre getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        if (value == null)
            return null; 
        return Genre.valueOf(value);
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Genre value) {
        return value == null ? "" : value.toString();
    }

}
