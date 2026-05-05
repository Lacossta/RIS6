package com.anysound.validator;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator(value = "flightNumberValidator", managed = true)
public class FlightNumberValidator implements Validator<String> {
    private static final String REGEX = "^[A-Z]{2}-\\d{3,4}$";

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null || value.isBlank()) return;
        if (!value.matches(REGEX)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ошибка", "Номер рейса должен быть формата XX-1234"));
        }
    }
}