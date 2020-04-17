package multi.android.material_design_pro2.cardview.exam;

public class CardItem {
    String txt;
    int img;

    public CardItem(int img, String txt) {
        this.img = img;
        this.txt = txt;
    }

    public int getImg() {
        return img;
    }

    public String getTxt(){
        return txt;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "txt='" + txt + '\'' +
                ", img=" + img +
                '}';
    }
}
