package seedu.address.model.company.exceptions;

/**
 * Signals that the operation will result in marking or unmarking a company that is already marked or unmarked.
 */
public class CompanyAlreadyMarkedOrUnmarked extends RuntimeException {
    public CompanyAlreadyMarkedOrUnmarked() {
        super("Operation would result in marking or unmarking a company that is already marked or unmarked");
    }
}
