package lab.song.model.converter;

import java.util.Optional;
import java.util.UUID;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import lab.album.entities.Album;
import lab.album.service.AlbumService;


@FacesConverter(forClass = _AlbumModel.class, managed = true)
public class AlbumModelConverter implements Converter<_AlbumModel>{
    private final AlbumService albumService;

    @Inject
    public AlbumModelConverter(AlbumService albumService){
        this.albumService = albumService;
    }
    @Override
    public _AlbumModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Album> album = albumService.find(UUID.fromString(value));
        return album.map(_album -> _AlbumModel.mapper(_album)).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, _AlbumModel value) {
        return value == null ? "" : value.getId().toString();
    }


}


