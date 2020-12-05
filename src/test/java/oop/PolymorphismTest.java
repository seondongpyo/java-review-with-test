package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.assertj.core.api.Assertions.assertThat;

class PolymorphismTest {

    @Test
    @DisplayName("다형성")
    void polymorphism() {
        Tv t = new Tv(); // 조상인 Tv 클래스의 인스턴스
        t.power();
        t.setChannel(101);
        t.setVolume(10);

        assertThat(t.isPower()).isTrue();
        assertThat(t.getChannel()).isEqualTo(101);
        assertThat(t.getVolume()).isEqualTo(10);

        CaptionTv c = new CaptionTv(); // 자손인 CaptionTv 클래스의 인스턴스
        c.power();
        c.captionOn();

        assertThat(c.isPower()).isTrue();
        assertThat(c.isCaption()).isTrue();

        Tv tv = new CaptionTv(); // 조상 클래스 타입의 참조변수로 자손 클래스의 인스턴스를 참조할 수 있다
        tv.power();
//        tv.captionOn(); // 에러
        // => 실제 인스턴스가 자손 클래스의 인스턴스라도, 조상 클래스 타입의 참조변수로 자손 클래스의 멤버들은 사용할 수 없다

        assertThat(tv.isPower()).isTrue();

//        CaptionTv captionTv = new Tv(); // 에러
        // => 자손 클래스 타입의 참조변수로 조상 클래스 타입의 인스턴스는 참조할 수 없다
    }

    @Test
    @DisplayName("참조변수의 형변환")
    void polymorphismCast() {
        /*
            << 참조변수의 형변환 >>
            * 서로 상속 관계에 있는 클래스 사이에서는 자손 클래스 타입의 참조변수를 조상 클래스 타입의 참조변수로,
              조상 클래스 타입의 참조변수를 자손 클래스 타입의 참조변수로 형변환이 가능하다
            * 서로 상속 관계에 있는 클래스 타입 간의 형변환은 양방향으로 자유롭게 수행될 수 있으나,
              참조변수가 가리키는 인스턴스의 자손 클래스 타입으로의 형변환은 허용되지 않는다
              따라서 참조변수가 가리키는 인스턴스의 타입이 무엇인지 먼저 확인하는 것이 중요하다
         */

        CaptionTv captionTv1 = new CaptionTv();
        Tv tv = (Tv) captionTv1; // 조상인 Tv 클래스 타입으로 형변환(생략 가능)
        CaptionTv captionTv2 = (CaptionTv) tv; // 자손인 CaptionTv 클래스 타입으로 형변환(생략 불가능)
//        SmartTv smartTv = (CaptionTv) captionTv2; // 에러 : 상속 관계가 아닌 클래스 간의 형변환은 불가능
    }

    @Test
    @DisplayName("instanceof 연산자")
    void instanceOf() {
        Tv tv = new Tv();
        CaptionTv captionTv = new CaptionTv();
        SmartTv smartTv = new SmartTv();

        assertThat(captionTv instanceof CaptionTv).isTrue();
        assertThat(smartTv instanceof SmartTv).isTrue();

        // instanceof 결과가 true : 참조변수를 검사한 타입으로 형변환 할 수 있다
        assertThat(captionTv instanceof Tv).isTrue();
        assertThat(smartTv instanceof Tv).isTrue();

        assertThat(tv instanceof Tv).isTrue();
        assertThat(tv instanceof CaptionTv).isFalse();
        assertThat(tv instanceof SmartTv).isFalse();
    }

    @Test
    @DisplayName("매개변수의 다형성")
    void polymorphismParameter() {
        Buyer buyer = new Buyer();
        assertThat(buyer.money).isEqualTo(100000);
        assertThat(buyer.bonusPoint).isEqualTo(0);

        Product computer = new Computer(40000, 400); // 컴퓨터 입고
        Product monitor = new Monitor(20000, 200); // 모니터 입고

        buyer.buy(computer); // 고객이 컴퓨터를 구입
        assertThat(buyer.money).isEqualTo(60000);
        assertThat(buyer.bonusPoint).isEqualTo(400);

        buyer.buy(monitor); // 고객이 모니터를 구입
        assertThat(buyer.money).isEqualTo(40000);
        assertThat(buyer.bonusPoint).isEqualTo(600);
    }
}

class Tv {

    private boolean power;
    private int channel;
    private int volume;

    void power() {
        power = !power;
    }

    public boolean isPower() {
        return power;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}

class CaptionTv extends Tv {

    private boolean caption;

    public boolean isCaption() {
        return caption;
    }

    void captionOn() {
        caption = true;
    }

    void captionOff() {
        caption = false;
    }
}

class SmartTv extends Tv {

}

class Product {

    int price;
    int bonusPoint;

    public Product(int price, int bonusPoint) {
        this.price = price;
        this.bonusPoint = bonusPoint;
    }
}

class Computer extends Product {
    public Computer(int price, int bonusPoint) {
        super(price, bonusPoint);
    }
}

class Monitor extends Product {
    public Monitor(int price, int bonusPoint) {
        super(price, bonusPoint);
    }
}

class Buyer {

    int money = 100000;
    int bonusPoint = 0;

    // 다형성을 활용하지 않는 경우, 상품 품목이 늘어날 때마다 새로운 메서드를 계속 추가해야 한다
//        void buy(Computer computer) {
//            money -= computer.price;
//            bonusPoint += computer.bonusPoint;
//        }
//
//        void buy(Monitor monitor) {
//            money -= monitor.price;
//            bonusPoint += monitor.bonusPoint;
//        }

    void buy(Product product) { // 매개변수로 다형성을 활용
        money -= product.price;
        bonusPoint += product.bonusPoint;
    }
}