package smart.mirror;

public class CheckSwitches {

    Setting mMainActivity;
    public CheckSwitches(Setting activity) {
        mMainActivity = activity;

    }


    public boolean checkSwitch(boolean s) {


        if(s==false){

            return false;
        }
        return true;
    }
    public boolean checkSwitchf(boolean s) {


        if(s==true){

            return true;
        }
        return false;
    }
}
