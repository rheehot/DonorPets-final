package kr.hs.emirim.sookhee.donerpets_final;


public class Story {

    public String title;
    public String shelterName;
    public String img1;
    public String date;


    public Story() {
    }

    public Story(String title, String shelterName, String img1, String date) {
        this.title = title;
        this.shelterName = shelterName;
        this.img1 = img1;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}