package com.penelopef.tools;

import java.util.function.Consumer;

public class MenuTools {

    public static class MenuContext {
        public Boolean leaveCurrentMenu = false;
    }

    public static void showMenu(Consumer<MenuContext> menu) {
        MenuContext context = new MenuContext();

        while (!context.leaveCurrentMenu) {
            menu.accept(context);
        }
    }
}
