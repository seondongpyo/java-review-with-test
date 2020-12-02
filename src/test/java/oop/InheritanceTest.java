package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InheritanceTest {

    /*
        << 상속 >>
        * 'class 자손 클래스명 extends 조상 클래스명'
        * 기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것
        * 두 클래스를 조상(상위)과 자손(하위)으로 관계를 맺어주는 것
        * 자손은 조상의 모든 멤버를 상속 받는다 (단, 생성자와 초기화 블록은 제외)
        * 자손의 멤버의 갯수는 조상보다 적을 수 없다 (같거나 많다)
        * 하나의 클래스만 상속 가능, 여러 클래스를 동시에 상속 받을 수 없다
        * 상속의 장점
            - 보다 적은 양의 코드로 새로운 클래스를 작성할 수 있음
            - 코드를 공통적으로 관리할 수 있기 때문에, 코드의 중복은 제거하고 재사용성을 높임

        << 클래스간의 관계 - 상속 관계 >>
        * 공통 부분은 조상에서 관리하고, 개별 부분은 자손에서 관리한다
        * 조상의 변경은 자손에 영향을 미치지만, 자손의 변경은 조상에 아무런 영향을 미치지 않는다

        << 클래스간의 관계 - 포함 관계 >>
        * 포함(composite) : 한 클래스의 멤버 변수로 다른 클래스를 선언하는 것
        * 작은 단위의 클래스를 먼저 만들고, 이들을 조합하여 하나의 커다란 클래스를 만든다

        << 클래스간의 관계 결정하기 - 상속 vs. 포함 >>
        * 가능한 한 많은 관계를 맺어서 재사용성을 높이고 관리하기 쉽도록 한다
        * 'is-a'와 'has-a'로 문장을 만들어 본다
        * ex) Circle 클래스와 Shape, Point 클래스와의 관계
            - 상속관계 : 'A는 B이다' (A is a B) → Circle is a Shape
            - 포함관계 : 'A는 B를 가지고 있다' (A has a B) → Circle has a Point

        << 단일 상속 (single inheritance) >>
        * Java는 단일 상속만을 허용한다
        * 다중 상속의 문제점
            - 여러 클래스로부터 상속을 받으므로 클래스 정의가 쉬워지지만 상속 계층도가 너무 복잡해져 클래스간의 관계를 관리하기 어렵다
            - 서로 다른 조상으로부터 같은 이름의 멤버를 상속 받는 경우 충돌이 발생한다
        * 클래스 정의 시 여러 클래스가 필요한 경우 비중이 높은 클래스 하나만 상속 관계로, 나머지는 포함 관계로 한다
     */

    @Test
    @DisplayName("상속")
    void inheritance() {
        Child child = new Child();
        assertThat(child.parentMoney).isEqualTo(50000); // 자식 클래스는 부모 클래스의 멤버들을 물려 받아 사용할 수 있음
        assertThat(child.childMoney).isEqualTo(0); // 현재 자식의 재산은 0원

        child.spendMoney(20000); // 자식이 부모로부터 물려 받은 재산에서 20000원을 사용
        child.saveMoney(10000); // 자식이 본인 재산에 10000원을 저축

        assertThat(child.childMoney).isEqualTo(10000);
        assertThat(child.parentMoney).isEqualTo(30000);
    }
}

class Parent {

    int parentMoney = 50000;

    void saveMoney(int amount) {
        parentMoney += amount;
    }
}

class Child extends Parent {

    int childMoney = 0;

    void saveMoney(int amount) {
        childMoney += amount;
    }

    void spendMoney(int amount) {
        parentMoney -= amount;
    }
}

class Shape {

    void draw() {
    }
}

class Circle extends Shape {

    Point p = new Point();
    int radius;

    void draw() {
        System.out.println("draw a circle");
    }
}

class Point {

    int coordX;
    int coordY;
}