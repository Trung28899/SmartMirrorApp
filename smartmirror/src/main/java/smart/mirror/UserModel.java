package smart.mirror;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
public class UserModel {

    boolean isSelected;
    String imageDescription;
    int imagename;

    public UserModel(boolean isSelected, String imageDescription, int imagename) {
        this.isSelected = isSelected;
        this.imagename = imagename;
        this.imageDescription = imageDescription;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public int isimagename() {
        return imagename;
    }

    public void setImagename(boolean selected) {
        this.imagename = imagename;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setToppingName(String imageDescription) {
        this.imageDescription = imageDescription;
    }
}

