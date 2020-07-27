package vfb.ebi.dataingest.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import vfb.ebi.dataingest.api.APIAccessException;
import vfb.ebi.dataingest.api.DataIngestAPI;
import vfb.ebi.dataingest.api.WebAppConfig;
import vfb.ebi.dataingest.ui.elements.OrcidSignInButton;

import javax.security.auth.login.FailedLoginException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

public class LoginView extends DataIngestView {

    public LoginView(Navigator navigator) {
        super(navigator);
        Button signIn = new OrcidSignInButton();
        signIn.addClickListener(this::signIn);
        addComponent(signIn);
    }

    private void signIn(Button.ClickEvent clickEvent) {
        goToExternalLogin();
    }

    private void goToExternalLogin() {
        Page.getCurrent().open(WebAppConfig.getInstance().authoriseURL,null);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        URI location = Page.getCurrent().getLocation();
        System.out.println("ENTER LOGIN: "+location);
        List<NameValuePair> params = URLEncodedUtils.parse(location, Charset.forName("UTF-8"));

        Optional<String> code = Optional.empty();

        for (NameValuePair param : params) {
            System.out.println(param.getName());
            if(param.getName().equals("code")) {
                code = Optional.of(param.getValue());
            }
        }

        if (code.isPresent()) {
            System.out.println("Authenticating with authorisation code.");
            try {
                DataIngestAPI.getInstance().login(code.get());
            } catch (FailedLoginException e) {
                e.printStackTrace();
            }
        }
    }

    private Optional<String> parseCode(URI location) {

        return Optional.empty();
    }

}
