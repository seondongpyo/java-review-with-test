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
