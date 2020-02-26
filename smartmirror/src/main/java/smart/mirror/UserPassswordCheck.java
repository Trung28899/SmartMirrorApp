package smart.mirror;

public class UserPassswordCheck {

    loginScreenActivity mMainActivity;
    public UserPassswordCheck(loginScreenActivity activity) {
        mMainActivity = activity;
    }

    public boolean checkPassword(String username) {
        //String username = mMainActivity.getUserName();

        if(username==null){

            return true;
        }
        return false;
    }

    public boolean checkPasswordIsEmpty( String username) {
        //String username = mMainActivity.getUserName();

        if(username.length()==0){

            return true;
        }
        return false;
    }

}
