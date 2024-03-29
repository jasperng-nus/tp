package seedu.address.model.company.exceptions;

/**
 * Signals that the operation will result in marking a company that is already marked.
 */
public class CompanyAlreadyMarkedException extends RuntimeException {
    public CompanyAlreadyMarkedException() {
        super("Operation would result in marking a company that is already marked");
    }
}
