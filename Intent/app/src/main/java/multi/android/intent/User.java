package multi.android.intent;

import android.os.Parcel;
import android.os.Parcelable;

// 안드로이드에서 인텐트에 객체를 공유하고 싶은 경우 Parcelable 타입으로 정의
// => Parcelable을 implements하고 메소드를 적절하게 오버라이딩
public class User implements Parcelable {
    String name;
    String telNum;



    protected User(Parcel in) {
        name = in.readString();
        telNum = in.readString();
    }

    // 안드로이드 OS가 객체를 복원할 때(User를 꾸깃하게 intent에 집어넣은 다음에 User의 형태로 꺼냄)
    // cf. 네트워크에서 패킷 단위로 쪼개고 합치는 작업과 비슷
    // Creator타입의 변수 CREATOR를 찾아서 CREATOR의 createFromParcel를 호출한 후
    // 반환되는 값을 이용해서 사용
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            // User생성자(Parcle타입 매개변수에서 꺼내서 담음)
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // 객체를 인텐트에 담을때 자동으로 호출되는 메소드
    //  - Parcelable에 데이터 저장
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(telNum);
    }

    // 안드로이드는 메모리가 제한적이므로 보통 기본생성자와 setter, getter 만들지 않는다.
    // 즉 변수를 private으로 만들지 않는다.
    public User(){

    }

    public User(String name, String telNum) {
        this.name = name;
        this.telNum = telNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
