package smart.mirror;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckSwitchesTest {


    Setting mMockMainActivity;
    @Test
    public void checkSwitchFalse() {


            CheckSwitches numberAdder = new CheckSwitches(mMockMainActivity);
            assertFalse(numberAdder.checkSwitch(false));

    }
    @Test
    public void checkSwitchTrue() {


        CheckSwitches numberAdder = new CheckSwitches(mMockMainActivity);
        assertTrue(numberAdder.checkSwitchf(true));

    }
}