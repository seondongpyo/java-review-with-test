package oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterfaceTest {

    @Test
    @DisplayName("인터페이스")
    void interfaceTest() {
        /*
            << 인터페이스 >>
            * 일종의 추상 클래스이며, 추상 클래스보다 추상화의 정도가 높다
            * 실제 구현된 것이 전혀 없는 기본 설계도(알맹이 없는 껍데기)
            * 추상 메서드와 상수만을 멤버로 가질 수 있다
            * 인스턴스를 생성할 수 없고, 클래스 작성에 도움을 줄 목적으로 사용된다
            * 미리 정해진 규칙에 맞게 구현하도록 표준을 제시하는 데 사용된다
            * 의무적으로 구현해야 하는 메서드의 목록을 인터페이스로 작성하여 자손 클래스들이 강제로 구현하도록 한다
         */

        Animal cat = new Cat();
        Animal dog = new Dog();
        Animal cow = new Cow();

        assertThat(cat.getSpecies()).isEqualTo("고양이");
        assertThat(cat.sound()).isEqualTo("야옹");
        assertThat(dog.getSpecies()).isEqualTo("개");
        assertThat(dog.sound()).isEqualTo("멍멍");
        assertThat(cow.getSpecies()).isEqualTo("소");
        assertThat(cow.sound()).isEqualTo("음메");
    }
}

interface Animal {

    public abstract String sound(); // 'public', 'abstract'는 생략 가능
    String getSpecies();
}

class Cat implements Animal {

    @Override
    public String sound() {
        return "야옹";
    }

    @Override
    public String getSpecies() {
        return "고양이";
    }
}

class Dog implements Animal {

    @Override
    public String sound() {
        return "멍멍";
    }

    @Override
    public String getSpecies() {
        return "개";
    }
}

class Cow implements Animal {

    @Override
    public String sound() {
        return "음메";
    }

    @Override
    public String getSpecies() {
        return "소";
    }
}