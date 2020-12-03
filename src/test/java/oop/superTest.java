package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class superTest {

    @Test
    @DisplayName("super 키워드")
    void superKeyword() {
        /*
            << super - 참조변수 >>
            * this : 인스턴스 자신을 가리키는 참조변수로, 인스턴스의 주소가 저장되어 있음
                     모든 인스턴스 메서드에 지역변수로 숨겨진 채로 존재함
              super : this와 같으며, 조상의 멤버와 자신의 멤버를 구별하는 데 사용함
         */

        Child1 child1 = new Child1();
        Child2 child2 = new Child2();

        assertThat(child1.x).isEqualTo(20); // Child1의 x
        assertThat(child1.getX_this()).isEqualTo(20); // Child1의 x
        assertThat(child1.getX_super()).isEqualTo(10); // Parent의 x
        assertThat(child1.getSumOfIntValues()).isEqualTo(50); // Child1의 메서드
        assertThat(child1.getSumOfIntValues_super()).isEqualTo(10); // Parent1의 메서드

        // Child2 클래스에 멤버 변수 x가 존재하지 않으므로, Child2는 조상으로부터 물려 받은 x를 그대로 사용한다
        assertThat(child2.x).isEqualTo(10); // Parent의 x
        assertThat(child2.getX_this()).isEqualTo(10); // Parent의 x
        assertThat(child2.getX_super()).isEqualTo(10); // Parent의 x
        assertThat(child2.getSumOfIntValues()).isEqualTo(30); // Child2의 메서드
        assertThat(child2.getSumOfIntValues_super()).isEqualTo(10); // Parent1의 메서드
    }

    class Parent {

        int x = 10;

        int getSumOfIntValues() {
            return x;
        }
    }

    class Child1 extends Parent {

        int x = 20;
        int y = 30;

        int getX_this() {
            return this.x;
        }

        int getX_super() {
            return super.x;
        }

        @Override
        int getSumOfIntValues() {
            return x + y;
        }

        int getSumOfIntValues_super() {
            return super.getSumOfIntValues(); // 조상의 메서드 호출
        }
    }

    class Child2 extends Parent {

        int z = 30;

        int getX_this() {
            return this.x;
        }

        int getX_super() {
            return super.x;
        }

        @Override
        int getSumOfIntValues() {
            return z;
        }

        int getSumOfIntValues_super() {
            return super.getSumOfIntValues(); // 조상의 메서드 호출
        }
    }
}
