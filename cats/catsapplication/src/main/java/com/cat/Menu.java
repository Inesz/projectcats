package com.cat;

import java.util.*;
import java.util.logging.Logger;

public class Menu {

    private static final Logger LOGGER = Logger.getLogger("Menu");
    //private static List<String> options = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
    //private static Map<Integer, String> menu = new LinkedHashMap<Integer, String>(Map.of(1,"Show cats data", 2, "Add cat to list", 3, "Remove cat from list", 4, "Exit"));
    private static Map<String, String> menu = new TreeMap<>(Map.of("1","Show cats data", "2", "Add cat to list", "3", "Remove cat from list", "4", "Exit"));

    public static void main(String[] args) {
        showMenu();
        executeOption(readOption());
    }

    private static String readOption() {
        Scanner sc = new Scanner(System.in);
        String choose = "0";

//        while (!options.contains(choose)) {
//            LOGGER.info("Please, choose one of: " + options + ".");
//            choose = sc.nextLine();
//        }

        while (!menu.containsKey(choose)) {
            LOGGER.info("Please, choose one of: " + menu.keySet() + ".");
            choose = sc.nextLine();
        }

        return choose;
    }

    private static void showMenu() {
        //LOGGER.info("Menu:\n1). Show cats data.\n2). Add cat to list. \n3). Remove cat from list. \n4). Exit");
        //LOGGER.info("Menu:\n");
        //LOGGER.info(menu.entrySet().toString());
        //variable used in lambda expression should be final or effectively final
        //menu.forEach((key, value) -> LOGGER.info(key.toString() + "). " + value.toString() + "."));

        String menuMsg = "Menu:\n";
        for (Map.Entry<String, String> entry : menu.entrySet()) {
            menuMsg = menuMsg.concat(entry.getKey() + "). " + entry.getValue() + ".\n");
        }
        LOGGER.info(menuMsg);

    }

    private static void executeOption(String choose) {
        switch (choose) {
            case "1":

            case "2":

            case "3":

            case "4":
                exit();
        }
    }

        private static void exit(){
            LOGGER.info("Good Bye");
        }

}
