package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_emptyPhoneNumber() {
        Phone emptyPhone = Phone.getDefaultPhone();
        assertNull(emptyPhone.value);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Phone.isValidPhone(null));
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void isPhonePresent() {
        Phone defaultPhone = Phone.getDefaultPhone();
        Phone phone = new Phone("995");
        assertTrue(phone.isPhonePresent());
        assertFalse(defaultPhone.isPhonePresent());
    }

    @Test
    public void equals() {
        Phone phone = new Phone("999");
        Phone defaultPhone = Phone.getDefaultPhone();
        // same values -> returns true
        assertTrue(phone.equals(new Phone("999")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        //two phones with default values -> return true
        assertTrue(defaultPhone.equals(Phone.getDefaultPhone()));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("995")));

        //one default phone, one non default phone -> returns false
        assertFalse(defaultPhone.equals(new Phone("995")));
    }

    @Test
    public void testToString() {
        Phone defaultPhone = Phone.getDefaultPhone();
        assertTrue(defaultPhone.toString().equals("No phone number"));
        Phone phone = new Phone("995");
        assertTrue(phone.toString().equals("995"));
    }
}
