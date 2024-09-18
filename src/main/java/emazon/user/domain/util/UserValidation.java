package emazon.user.domain.util;

import emazon.user.domain.exception.*;
import emazon.user.domain.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class UserValidation {

    public void validate(User user){
        validateRequiredFields(user);
        validateEmail(user.getUserEmail());
        validatePhone(user.getUserPhone());
        validateIdentityDocument(user.getUserIdentityDocument());
        validateAge(user.getUserBirthdate());
    }

    private void validateRequiredFields(User user) {
        if (user.getUserName() == null || user.getUserName().trim().isEmpty() ||
                user.getUserLastName() == null || user.getUserLastName().trim().isEmpty() ||
                user.getUserIdentityDocument() == null || user.getUserIdentityDocument().trim().isEmpty() ||
                user.getUserPhone() == null || user.getUserPhone().trim().isEmpty() ||
                user.getUserEmail() == null || user.getUserEmail().trim().isEmpty() ||
                user.getUserPassword() == null || user.getUserPassword().trim().isEmpty() ||
                user.getUserBirthdate() == null) {
            throw new MissingFieldException(UserValidationConstants.MISSING_FIELD);
        }
    }

    private void validateEmail(String email){
        if(email == null || email.trim().isEmpty()){
            throw new EmailValidationException(UserValidationConstants.EMAIL_MANDATORY);
        }
        if(!Pattern.matches(UserValidationConstants.EMAIL_REGEX, email)){
            throw new EmailValidationException(UserValidationConstants.EMAIL_INVALID);
        }
    }

    private void validatePhone(String phone){
        if (phone == null || phone.trim().isEmpty()) {
            throw new PhoneValidationException(UserValidationConstants.PHONE_MANDATORY);
        }
        if (!Pattern.matches(UserValidationConstants.PHONE_REGEX, phone)) {
            throw new PhoneValidationException(UserValidationConstants.PHONE_INVALID);
        }
    }

    private void validateIdentityDocument(String identityDocument){
        if (identityDocument == null || identityDocument.trim().isEmpty()) {
            throw new IdentityDocumentValidationException(UserValidationConstants.IDENTITY_DOCUMENT_MANDATORY);
        }
        if (!Pattern.matches(UserValidationConstants.IDENTITY_DOCUMENT_REGEX, identityDocument)) {
            throw new IdentityDocumentValidationException(UserValidationConstants.IDENTITY_DOCUMENT_INVALID);
        }
    }

    private void validateAge(LocalDate birthdate) {
        if (birthdate == null || Period.between(birthdate, LocalDate.now()).getYears() < UserValidationConstants.MINIMUM_AGE) {
            throw new AgeValidationException(UserValidationConstants.AGE_INVALID);
        }
    }
}