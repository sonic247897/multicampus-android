package exam.day03.view.selectview.view.adapter;
// row에 출력할 데이터 정보를 담는 객체
public class User {
    int myImg; // 리소스는 모두 int타입으로 관리된다.
    String name;
    String telNum;

    public User(int myImg, String name, String telNum) {
        this.myImg = myImg;
        this.name = name;
        this.telNum = telNum;
    }
    // 개발자용
    @Override
    public String toString() {
        return "User{" +
                "myImg=" + myImg +
                ", name='" + name + '\'' +
                ", telNum='" + telNum + '\'' +
                '}';
    }
    // 안드로이드 앱의 용량을 줄이기 위해서 getter, setter 안 쓰고 변수에 직접 접근
    // 웹 - 웹서버(Oracle,..)를 open해서 우회, 해킹 등 문제점 있지만
    // 앱은 내 폰(local)에 설치되기 때문에 데이터도 local의 내장DB에 저장되어 있는 경우가 많다.
    // (문자, 이미지,...)
    // (예외 - 카톡은 채팅이라서 데이터가 서버에 저장된다-상대방 있음)
}
