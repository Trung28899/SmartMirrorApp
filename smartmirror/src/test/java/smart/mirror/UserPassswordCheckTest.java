package smart.mirror;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserPassswordCheckTest {


    loginScreenActivity mMockMainActivity;
    @Test
    public void checkPassword() {
        UserPassswordCheck  password = new UserPassswordCheck(mMockMainActivity);
        assertTrue(password.checkPassword(null));

    }

    @Test
    public void checkPasswordIsEmpty() {
        UserPassswordCheck  password = new UserPassswordCheck(mMockMainActivity);
        assertTrue(password.checkPasswordIsEmpty(""));


    }
}