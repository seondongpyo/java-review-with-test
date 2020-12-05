package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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