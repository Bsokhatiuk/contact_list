package app.validator;

import app.model.Record;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RecordValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Record.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Record record = (Record) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (record.getName().length() < 4 || record.getName().length() > 32) {
            errors.rejectValue("name", "Size.newRecordForm.name");
        }
        if (record.getSurname().length() < 4 || record.getSurname().length() > 32) {
            errors.rejectValue("surname", "Size.newRecordForm.surname");
        }
        if (record.getPatronymic().length() < 4 || record.getPatronymic().length() > 32) {
            errors.rejectValue("patronymic", "Size.newRecordForm.patronymic");
        }
        if (record.getPatronymic().length() < 4 || record.getPatronymic().length() > 32) {
            errors.rejectValue("mobilePhone", "Size.newRecordForm.mobilePhone");
        }
    }
}
