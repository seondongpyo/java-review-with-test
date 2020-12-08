package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultMethodTest {

    @Test
    @DisplayName("인터페이스의 디폴트 메서드")
    void interfaceDefaultMethod() {
        /*
            << 디폴트 메서드 (default method, JDK 1.8~) >>
            - 인터페이스에 새로운 메서드를 추가하면, 이 인터페이스를 구현한 기존의 모든 클래스가 이 메서드를 구현해야 함
            - 이 문제를 해결하기 위해 '디폴트 메서드'가 고안됨
            - 디폴트 메서드는 추상 메서드의 기본 구현을 제공하기 때문에 몸통({})을 가지고 있으며,
              앞에 'default'를 붙이고 항상 public(생략 가능)이다

            1) 여러 인터페이스의 디폴트 메서드 간의 충돌
                : 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩해야 한다

            2) 디폴트 메서드와 조상 클래스의 메서드 간의 충돌
                : 조상 클래스의 메서드가 상속되고, 디폴트 메서드는 무시된다
         */

        NewInterface1 class1 = new NewClass1();
        assertThat(class1.method()).isEqualTo("class1 method");
        assertThat(class1.newMethod()).isEqualTo("interface1 default method");

        NewInterface1 class2 = new NewClass2();
        assertThat(class2.method()).isEqualTo("class2 method");
        assertThat(class2.newMethod()).isEqualTo("overriding interface1 default method");

        NewInterface2 class3 = new NewClass3();
        assertThat(class3.newMethod()).isEqualTo("overriding interface2 default method");

        NewClass4 class4 = new NewClass4();
        assertThat(class4.method()).isEqualTo("class4 method");
        assertThat(class4.newMethod()).isEqualTo("overriding default method");

        NewClass5 class5 = new NewClass5();
        assertThat(class5.method()).isEqualTo("class5 method");
        assertThat(class5.newMethod()).isEqualTo("parent method");
    }

    interface NewInterface1 {
        String method(); // 인터페이스를 구현한 클래스에서 반드시 구현해야 하는 메서드

        default String newMethod() { // 디폴트 메서드 : 인터페이스를 구현한 클래스에서 구현할 필요가 없음
            return "interface1 default method";
        }
    }

    interface NewInterface2 {
        default String newMethod() { // Q. 여러 인터페이스를 구현한 클래스에서 디폴트 메서드 간의 충돌이 발생한다면? -> 오버라이딩
            return "interface2 default method";
        }
    }

    class ParentClass {
        public String newMethod() {
            return "parent method";
        }
    }


    class NewClass1 implements NewInterface1 {
        @Override
        public String method() {
            return "class1 method";
        }
    }

    class NewClass2 implements NewInterface1 {
        @Override
        public String method() {
            return "class2 method";
        }

        @Override
        public String newMethod() {
            return "overriding interface1 default method";
        }
    }

    class NewClass3 implements NewInterface2 {

        @Override
        public String newMethod() {
            return "overriding interface2 default method";
        }
    }

    class NewClass4 implements NewInterface1, NewInterface2 {
        @Override
        public String method() {
            return "class4 method";
        }

        // Q. 여러 인터페이스를 구현하면서 생기는 디폴트 메서드 간의 충돌 발생 시? → 디폴트 메서드 오버라이딩
        @Override
        public String newMethod() {
            return "overriding default method";
        }
    }

    class NewClass5 extends ParentClass implements NewInterface1 {
        @Override
        public String method() {
            return "class5 method";
        }

        // Q. 조상 클래스의 메서드와 인터페이스의 디폴트 메서드가 충돌한다면? → 디폴트 메서드 대신 조상 클래스의 메서드가 상속됨
//        @Override
//        public String newMethod() {
//
//        }
    }

}
