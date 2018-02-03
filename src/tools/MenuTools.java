package tools;

import java.util.function.Consumer;

import static java.lang.Boolean.FALSE;

public class MenuTools {

    public static class MenuContext {
        public Boolean leaveCurrentMenu = FALSE;
    }

    public static void showMenu(Consumer<MenuContext> menu) {
        MenuContext context = new MenuContext();

        while (!context.leaveCurrentMenu) {
            menu.accept(context);
        }
    }
}
