package edu.lwtech.csd297.tap4tap.commands;

import javax.servlet.http.*;

public interface CommandHandler<T> {
    String handle(HttpServletRequest request, T servlet);
}
