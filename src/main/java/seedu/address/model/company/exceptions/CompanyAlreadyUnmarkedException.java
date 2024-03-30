package seedu.address.model.company.exceptions;

/**
 * Signals that the operation will result in unmarking a company that is already unmarked.
 */
public class CompanyAlreadyUnmarkedException extends RuntimeException {
    public CompanyAlreadyUnmarkedException() {
        super("Operation would result in unmarking a company that is already unmarked");
    }
}
