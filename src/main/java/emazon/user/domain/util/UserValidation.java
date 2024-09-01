package emazon.user.domain.util;

import emazon.user.domain.exception.AgeValidationException;
import emazon.user.domain.exception.EmailValidationException;
import emazon.user.domain.exception.IdentityDocumentValidationException;
import emazon.user.domain.exception.PhoneValidationException;
import emazon.user.domain.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class UserValidation {
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_REGEX = "^\\+?\\d{1,13}$";
    private static final String IDENTITY_DOCUMENT_REGEX = "^\\d+$";

    public void validate(User user){
        validateEmail(user.getUserEmail());
        validatePhone(user.getUserPhone());
        validateIdentityDocument(user.getUserIdentityDocument());
        validateAge(user.getUserBirthdate());
    }

    private void validateEmail(String email){
        if(email == null || email.trim().isEmpty()){
            throw new EmailValidationException("Email is mandatory");
        }
        if(!Pattern.matches(EMAIL_REGEX, email)){
            throw new EmailValidationException("Email should be valid");
        }
    }

    private void validatePhone(String phone){
        if (phone == null || phone.trim().isEmpty()) {
            throw new PhoneValidationException("Phone is mandatory");
        }
        if (!Pattern.matches(PHONE_REGEX, phone)) {
            throw new PhoneValidationException("Phone must be a maximum of 13 digits and can contain the symbol +");
        }
    }

    private void validateIdentityDocument(String identityDocument){
        if (identityDocument == null || identityDocument.trim().isEmpty()) {
            throw new IdentityDocumentValidationException("Identity Document is mandatory");
        }
        if (!Pattern.matches(IDENTITY_DOCUMENT_REGEX, identityDocument)) {
            throw new IdentityDocumentValidationException("Identity Document should be valid");
        }
    }
    private void validateAge(LocalDate birthdate) {
        if (birthdate == null || Period.between(birthdate, LocalDate.now()).getYears() < 18) {
            throw new AgeValidationException("User must be an adult");
        }
    }

}
