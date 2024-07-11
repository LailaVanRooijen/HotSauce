package com.lvr.HotSauce.configuration;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

public class CustomBanner implements Banner {
    private static final String mantis = "\u001B[38;2;125;206;130m";
    private static final String blush = "\u001B[38;2;227;101;136m";
    private static final String jordyBlue = "\u001B[38;2;154;196;248m";

    private static final String BANNER =
            jordyBlue
                    + "\nSuper duper amazing development by\n\n"
                    + blush
                    + "██╗      █████╗ ██╗   ██╗██╗      ██████╗ ██╗    ██╗\n"
                    + "██║     ██╔══██╗╚██╗ ██╔╝██║     ██╔═══██╗██║    ██║\n"
                    + "██║     ███████║ ╚████╔╝ ██║     ██║   ██║██║ █╗ ██║\n"
                    + "██║     ██╔══██║  ╚██╔╝  ██║     ██║   ██║██║███╗██║\n"
                    + "███████╗██║  ██║   ██║   ███████╗╚██████╔╝╚███╔███╔╝\n"
                    + "╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝ ╚═════╝  ╚══╝╚══╝\n"
                    + jordyBlue
                    + "Don't forget to have fun!       Spring version 3.2.5\n\n"
                    + mantis;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, java.io.PrintStream out) {
        out.println(BANNER);
    }
}
