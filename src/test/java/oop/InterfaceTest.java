package oop;

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
                - 모든 멤버 변수는 public static final 이어야 하며, 이를 생략할 수 있다
                - 모든 메서드는 public abstract 이어야 하며, 이를 생략할 수 있다
            * 인스턴스를 생성할 수 없고, 클래스 작성에 도움을 줄 목적으로 사용된다
            * 미리 정해진 규칙에 맞게 구현하도록 표준을 제시하는 데 사용된다
            * 의무적으로 구현해야 하는 메서드의 목록을 인터페이스로 작성하여 자손 클래스들이 강제로 구현하도록 한다

            <<
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

    @Test
    @DisplayName("인터페이스의 장점")
    void interfaceAdvantages() {
        /*
            << 인터페이스의 장점 >>
            * 개발 시간을 단축시킬 수 있다.
                - 일단 인터페이스가 작성되면, 이를 사용해서 프로그램을 작성하는 것이 가능하다.
                  메서드를 호출하는 쪽에서는 선언부만 알면 되기 때문이다.
                - 동시에 다른 한 쪽에서는 인터페이스를 구현하는 클래스를 작성하도록 하여,
                  인터페이스를 구현하는 클래스가 작성될 때까지 기다리지 않고 양쪽에서 동시에 개발을 진행할 수 있다.

            * 표준화가 가능하다.
                - 프로젝트에 사용되는 기본 틀을 인터페이스로 작성한 다음,
                  개발자들에게 인터페이스를 구현하여 프로그램을 작성하도록 함으로써
                  보다 일관되고 정형화된 프로그램의 개발이 가능하다.

            * 서로 관계 없는 클래스들에게 관계를 맺어줄 수 있다.
                - 서로 상속 관계에 있지도 않고, 같은 조상 클래스를 가지고 있지 않은
                  서로 아무런 관계도 없는 클래스들에게 하나의 인터페이스를 공통적으로 구현하도록 함으로써 관계를 맺어줄 수 있다.

            * 독립적인 프로그래밍이 가능하다.
                - 인터페이스를 이용하면 클래스의 선언과 구현을 분리시킬 수 있기 때문에, 실제 구현에 독립적인 프로그램을 작성하는 것이 가능하다.
                - 클래스와 클래스간의 직접적인 관계를 인터페이스를 이용해서 간접적인 관계로 변경하면,
                  한 클래스의 변경이 관련된 다른 클래스에 영향을 미치지 않는 독립적인 프로그래밍이 가능하다.
         */

        SCV scv = new SCV();
        Marine marine = new Marine();
        SiegeTank siegeTank = new SiegeTank();
        Wraith wraith = new Wraith();

//        scv.repair(marine); // 해병은 생체 유닛이라 수리 불가능
        scv.repair(siegeTank);
        scv.repair(wraith);

        assertThat(scv.hitPoint).isEqualTo(60);
        assertThat(scv.build()).isEqualTo("건물을 건설합니다");
        assertThat(marine.hitPoint).isEqualTo(40);
        assertThat(marine.stimpack()).isEqualTo("스팀팩을 사용합니다");
        assertThat(siegeTank.hitPoint).isEqualTo(150);
        assertThat(siegeTank.changeMode()).isEqualTo("모드를 변경합니다");
        assertThat(wraith.hitPoint).isEqualTo(120);
        assertThat(wraith.cloak()).isEqualTo("은폐합니다");
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

    interface Repairable {}

    class Unit {

        int hitPoint;
        final int MAX_HP;

        public Unit(int hp) {
            MAX_HP = hp;
        }
    }

    class GroundUnit extends Unit {
        public GroundUnit(int hp) {
            super(hp);
        }
    }

    class AirUnit extends Unit {
        public AirUnit(int hp) {
            super(hp);
        }
    }

    class SCV extends GroundUnit implements Repairable {
        public SCV() {
            super(60);
            hitPoint = MAX_HP;
        }

        void repair(Repairable repairableUnit) {
            if (repairableUnit instanceof Unit) {
                Unit unit = (Unit) repairableUnit;

                while (unit.hitPoint != unit.MAX_HP) {
                    unit.hitPoint++;    // 유닛을 수리
                }
            }
        }

        String build() {
            return "건물을 건설합니다";
        }
    }

    class Marine extends GroundUnit {
        public Marine() {
            super(40);
            hitPoint = MAX_HP;
        }

        String stimpack() {
            return "스팀팩을 사용합니다";
        }
    }

    class SiegeTank extends GroundUnit implements Repairable {
        public SiegeTank() {
            super(150);
            hitPoint = MAX_HP;
        }

        String changeMode() {
            return "모드를 변경합니다";
        }
    }

    class Wraith extends AirUnit implements Repairable {
        public Wraith() {
            super(120);
            hitPoint = MAX_HP;
        }

        String cloak() {
            return "은폐합니다";
        }
    }
}