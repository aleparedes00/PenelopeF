package com.penelopef.tools;

import java.util.function.Consumer;

import static java.lang.Boolean.FALSE;
/*ALE
* This class will help to stay in the program/current-Menu
* it should work as a infinity loop (tant que tu sois loggé ou bien tant qu'il y a un active user)
* The implementation will be made through a functional interface [Every time we use this function (showMenu), it will take as parameter
* a function (MenuContext)]
*/

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
