package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("-n");
    public static final Prefix PREFIX_PHONE = new Prefix("-p");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e");
    public static final Prefix PREFIX_STARTDATE = new Prefix("-d1");
    public static final Prefix PREFIX_ENDDATE = new Prefix("-d2");
    public static final Prefix PREFIX_TAG = new Prefix("-t");
    public static final Prefix PREFIX_SETREMINDER = new Prefix("-r");
    public static final Prefix PREFIX_REMINDERDAYS = new Prefix("-days");

}
