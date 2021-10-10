package ai;

public class InOut {
    public InOut(){
        System.out.println("외부 메소드 생성자 호출됨");
    }
    // 내부 클래스를 static으로 만들어서 호출
    static class In1{

        public int a;

        public In1(){
            System.out.println("내부 메소드 기본 생성자 호출됨");
        }

        public In1(int b){
            System.out.println("내부 메소드 생성자 호출됨");
            this.a=b;
        }
    }
    // 외부 클래스에서 내부 클래스에 대한 참조 변수 선언
}
