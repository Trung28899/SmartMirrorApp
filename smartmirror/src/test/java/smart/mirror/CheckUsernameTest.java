package smart.mirror;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CheckUsernameTest {

        @Mock
        loginScreenActivity mMockMainActivity;

        @Test
        public void isUsernameNull() {

            UserNameCheck numberAdder = new UserNameCheck(mMockMainActivity);
            assert(numberAdder.checkusername(null));

        }


    @Test
    public void isUserNameEmpty() {
        //setup

        //test
        UserNameCheck numberAdder = new UserNameCheck(mMockMainActivity);
        assertTrue(numberAdder.checkUserIsEmpty(""));

    }

    @Test
    public void isUserNameIsNumber() {
        //setup

        //test
        UserNameCheck numberAdder = new UserNameCheck(mMockMainActivity);
        assertTrue(numberAdder.checkUserIsnumber("haider123"));

    }

}
