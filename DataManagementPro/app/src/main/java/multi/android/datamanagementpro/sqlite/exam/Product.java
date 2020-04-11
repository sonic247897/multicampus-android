package multi.android.datamanagementpro.sqlite.exam;

import android.os.Parcel;
import android.os.Parcelable;

class Product implements Parcelable {
    int _id;
    String name;
    int price;
    int su;
    int totPrice;

    public Product(int _id, String name, int price, int su, int totPrice) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.su = su;
        this.totPrice = totPrice;
    }

    protected Product(Parcel in) {
        _id = in.readInt();
        name = in.readString();
        price = in.readInt();
        su = in.readInt();
        totPrice = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", su=" + su +
                ", totPrice=" + totPrice +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(su);
        dest.writeInt(totPrice);
    }
}
