package oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractTest {
    /*
        << 추상 클래스(abstract class) >>
        * '미완성 설계도' : 미완성 메서드(추상 메서드)를 포함하고 있는 클래스
        * 미완성 설계도로 완성된 제품을 만들 수 없듯이, 추상 클래스로 인스턴스를 생성할 수 없다
        * 추상 클래스를 상속한 자손 클래스에 의해서만 완성될 수 있다
        * 클래스로서의 역할을 다 못하지만, 새로운 클래스를 작성하는 데 있어서 바탕이 되는 조상 클래스로서 중요한 역할을 갖는다

        << 추상 메서드(abstract method) >>
        * 선언부만 존재하고 구현부가 작성되지 않은 메서드
        * 설계만 해 놓고 실제 수행될 내용을 작성하지 않은 미완성 메서드
        * 메서드의 내용은 상속 받는 클래스에 따라 달라질 수 있다
        * 추상 클래스를 상속 받는 자손 클래스는 오버라이딩을 통해 조상인 추상 클래스의 추상 메서드를 모두 구현해야 한다
        * 조상으로부터 상속 받은 추상 메서드 중 하나라도 구현하지 않는다면, 자손 클래스 역시 추상 클래스로 지정해야 한다
     */

    @Test
    @DisplayName("추상 클래스")
    void abstractClass() {
        Unit marine = new Marine();
        Unit siegeTank = new SiegeTank();
        Unit dropship = new Dropship();

        marine.move(100, 200);
        siegeTank.move(100, 190);
        dropship.move(120, 190);

        assertThat(marine).isInstanceOf(Unit.class);
        assertThat(marine.x).isEqualTo(100);
        assertThat(marine.y).isEqualTo(200);
        assertThat(siegeTank).isInstanceOf(Unit.class);
        assertThat(siegeTank.x).isEqualTo(100);
        assertThat(siegeTank.y).isEqualTo(190);
        assertThat(dropship).isInstanceOf(Unit.class);
        assertThat(dropship.x).isEqualTo(120);
        assertThat(dropship.y).isEqualTo(190);
    }
}

abstract class Unit {
    int x, y;
    abstract void move(int x, int y); // 구현부가 없는 추상 메서드
    void stop() { /* 현재 위치에 정지 */ }
}

class Marine extends Unit {
    @Override
    void move(int x, int y) { // 지정된 위치로 이동
        this.x = x;
        this.y = y;
        System.out.println("Marine moves to (" + x + ", " + y + ")");
    }

    void stimpack() { /* 스팀팩을 사용한다 */ }
}

class SiegeTank extends Unit {
    @Override
    void move(int x, int y) { // 지정된 위치로 이동
        this.x = x;
        this.y = y;
        System.out.println("Siege Tank moves to (" + x + ", " + y + ")");
    }

    void changeMode() { /* 전차 모드 or 공성 모드 */ }
}

class Dropship extends Unit {
    @Override
    void move(int x, int y) { // 지정된 위치로 이동
        this.x = x;
        this.y = y;
        System.out.println("Dropship moves to (" + x + ", " + y + ")");
    }

    void load() { /* 선택한 대상을 태운다 */ }
    void unload() { /* 선택한 대상을 내린다 */ }
}
