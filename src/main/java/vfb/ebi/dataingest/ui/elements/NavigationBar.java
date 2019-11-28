package vfb.ebi.dataingest.ui.elements;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;
import vfb.ebi.dataingest.ui.DataIngestUI;

public class NavigationBar extends HorizontalLayout {

    public static String MANAGEDATASETS = "Manage datasets";
    public static String LOGIN = "Login";
    public static String MAIN = "Home";


    public NavigationBar(Navigator navigator) {
        Button bt_main = new Button(MAIN, event -> navigator.navigateTo(DataIngestUI.MAIN));
        Button bt_dataset = new Button(MANAGEDATASETS, event -> navigator.navigateTo(DataIngestUI.MANAGEDATASETS));
        Button bt_login = new Button(LOGIN, event -> navigator.navigateTo(DataIngestUI.LOGIN));

        addComponent(bt_main);
        addComponent(bt_dataset);
        addComponent(bt_login);

        setComponentAlignment(bt_main, Alignment.TOP_LEFT);
        setComponentAlignment(bt_dataset, Alignment.TOP_LEFT);
        setComponentAlignment(bt_login, Alignment.TOP_LEFT);
    }

}
