package SimpleBankTest.ui.validators;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;

/**
 * Created by ifAJARD on 4/11/14.
 */
public class PasswordValidator  extends
        AbstractValidator<String> implements Validator {

    public PasswordValidator() {
        super("The password provided is not valid");
    }

    @Override
    protected boolean isValidValue(String value) {
        if (value != null
                && (value.length() < 8 || !value.matches(".*\\d.*"))) {
            return false;
        }
        return true;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
