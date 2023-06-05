package edu.lwtech.csd297.tap4tap.commands;

public class PermissionDeniedException extends Exception {
    public PermissionDeniedException(String message) {
        super(message);
    }
}
