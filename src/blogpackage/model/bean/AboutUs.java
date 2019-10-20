package blogpackage.model.bean;

public class AboutUs {

    protected int aboutUsID;
    protected String aboutUsContent;

    // constructor
    public AboutUs(int aboutUsID, String aboutUsContent) {
        this.aboutUsID = aboutUsID;
        this.aboutUsContent = aboutUsContent;
    }

    // getters and setters
    public int getAboutUsID() {
        return aboutUsID;
    }

    public void setAboutUsID(int aboutUsID) {
        this.aboutUsID = aboutUsID;
    }

    public String getAboutUsContent() {
        return aboutUsContent;
    }

    public void setAboutUsContent(String aboutUsContent) {
        this.aboutUsContent = aboutUsContent;
    }
}
