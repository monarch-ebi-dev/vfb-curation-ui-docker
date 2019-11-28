package vfb.ebi.dataingest.ui.elements;

import com.vaadin.server.ClassResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

public class OrcidSignInButton extends Button {

    public OrcidSignInButton() {
        setIcon(new ClassResource("/images/orcid-login.png"));
        setWidth(210, Unit.PIXELS);
        setHeight(75, Unit.PIXELS);
        setCaption("Login with orcid");
    }

}
