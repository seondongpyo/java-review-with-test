package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassTest {

    @Test
    @DisplayName("객체의 생성과 사용")
    void createNewInstance() {
        Tv tv;  // 메모리에 참조변수 tv를 위한 공간이 마련된다

        tv = new Tv();
        // 1. new 연산자에 의해 Tv 클래스의 인스턴스가 메모리의 빈 공간에 생성된다
        // 2. 멤버변수가 각 자료형의 기본값으로 초기화된다
        assertThat(tv.model).isNull();
        assertThat(tv.isPowerOn).isFalse();
        assertThat(tv.volume).isEqualTo(0);

        // 3. 대입 연산자에 의해서 생성된 객체의 주소값이 참조변수 tv에 저장된다
        // 4. 참조변수를 통해 생성된 Tv 인스턴스에 접근할 수 있다
        tv.model = "SMART TV";
        tv.power(); // 전원 켜기 / 끄기
        tv.volumeUp(); // 볼륨 키우기
        tv.volumeUp();
        tv.volumeDown(); // 볼륨 낮추기

        assertThat(tv.isPowerOn).isTrue();
        assertThat(tv.volume).isEqualTo(1);
    }

    // 클래스는 속성과 기능으로 이루어져 있다
    private class Tv {
        // 속성 : 변수
        String model;
        boolean isPowerOn;
        int volume;

        // 기능 : 메서드
        void power() {
            isPowerOn = !isPowerOn;
        }

        void volumeUp() {
            volume++;
        }

        void volumeDown() {
            volume--;
        }
    }
}
