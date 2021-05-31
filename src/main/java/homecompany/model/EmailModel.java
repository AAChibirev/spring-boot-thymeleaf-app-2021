package homecompany.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmailModel {

    @Email
    @NotNull
    private String emailTo;

    private String subject = "test";

    private String message;

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
