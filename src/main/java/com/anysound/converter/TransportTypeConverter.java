package com.anysound.converter;
import com.anysound.model.TransportType;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "transportTypeConverter", managed = true)
public class TransportTypeConverter implements Converter<TransportType> {
    @Override
    public TransportType getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) return null;
        return TransportType.valueOf(value);
    }
    @Override
    public String getAsString(FacesContext context, UIComponent component, TransportType value) {
        return (value == null) ? "" : value.name();
    }
}