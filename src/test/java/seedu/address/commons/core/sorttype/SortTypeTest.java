package seedu.address.commons.core.sorttype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class SortTypeTest {

    @Test
    public void testGetSortType() {
        assertEquals(SortType.ALPHANUMERICAL_ASCENDING, SortType.getSortType('a'));
        assertEquals(SortType.STARTDATE_ASCENDING, SortType.getSortType('s'));
        assertEquals(SortType.ENDDATE_ASCENDING, SortType.getSortType('e'));

        // Test invalid preference
        assertNull(SortType.getSortType('x'));
    }

    @Test
    public void testGetPref() {
        assertEquals('a', SortType.ALPHANUMERICAL_ASCENDING.getPref());
        assertEquals('s', SortType.STARTDATE_ASCENDING.getPref());
        assertEquals('e', SortType.ENDDATE_ASCENDING.getPref());
    }

    @Test
    public void testEquality() {
        assertSame(SortType.ALPHANUMERICAL_ASCENDING, SortType.ALPHANUMERICAL_ASCENDING);
        assertSame(SortType.STARTDATE_ASCENDING, SortType.STARTDATE_ASCENDING);
        assertSame(SortType.ENDDATE_ASCENDING, SortType.ENDDATE_ASCENDING);
    }
}
