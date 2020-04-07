package exam.day03.view.selectview.view.adapter;

public class ActorItem {
    int Img;
    String name;
    String date;
    String msg;

    public ActorItem(int img, String name, String date, String msg) {
        this.Img = img;
        this.name = name;
        this.date = date;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ActorItem{" +
                "Img=" + Img +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
