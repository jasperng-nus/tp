package seedu.address.commons.core.sorttype;

/**
 * Enumeration of the different sort types for Company objects
 */
public enum SortType {
    ALPHANUMERICAL_ASCENDING('a'),
    STARTDATE_ASCENDING('s'),
    ENDDATE_ASCENDING('e');

    private final char pref;

    SortType(char pref) {
        this.pref = pref;
    }

    public char getPref() {
        return pref;
    }

    public static SortType getSortType(char pref) {
        for (SortType sortType : SortType.values()) {
            if (sortType.getPref() == pref) {
                return sortType;
            }
        }
        return null;
    }
}
