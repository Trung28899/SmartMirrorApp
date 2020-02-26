package smart.mirror;

public class UserNameCheck {

    loginScreenActivity mMainActivity;
    public UserNameCheck(loginScreenActivity activity) {
        mMainActivity = activity;
    }

    public boolean checkusername(String username) {
        //String username = mMainActivity.getUserName();

        if(username==null){

            return true;
        }
        return false;
    }

    public boolean checkUserIsEmpty( String username) {
        //String username = mMainActivity.getUserName();

        if(username.length()==0){

            return true;
        }
        return false;
    }
    public boolean checkUserIsnumber( String username) {
        //String username = mMainActivity.getUserName();

        if(username.matches(".*\\d.*"))
        {

            return true;
        }
        return false;
    }






}
