package com.penelopef.controller;

import com.penelopef.models.Contact;
import com.penelopef.views.ContactView;

import static com.penelopef.tools.RegexTools.match;

public class ContactController {
    private Contact model;
    private ContactView view;

    private final String emailRegex = "(\\w|\\d|[._-])+@((\\w|\\d|-)+\\.)+(\\w|\\d)+";
    private final String phoneRegex = "0[0-9]{9}";

    public ContactController(Contact model, ContactView view) {
        this.model = model;
        this.view = view;
    }

    public void changeMail() {
        String newEmail = view.enterNewEmail();

        if (emailHasRightFormat(newEmail)) {
            model.setEmail(newEmail);
            view.changeSuccessful("email");
        }
        else view.formatError();
    }

    private boolean emailHasRightFormat(String newEmail) {
        return match(newEmail, emailRegex);
    }

    public void changePhone() {
        String newPhone = view.enterNewPhone();

        if (phoneHasRightFormat(newPhone)) {
            model.setPhone(newPhone);
            view.changeSuccessful("phone");
        }
        else view.formatError();
    }

    private boolean phoneHasRightFormat(String newPhone) {
        return match(newPhone, phoneRegex);
    }
}
