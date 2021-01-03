package enumtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnumTest {

    @Test
    @DisplayName("열거형(enum)의 정의와 사용")
    void enumType() {
        Unit unit = new Unit();
        unit.faceEast();

        assertThat(unit.direction == Direction.EAST).isTrue(); // 열거형은 '==' 비교 가능
    }

    @Test
    @DisplayName("열거형 메서드 name() - 열거형 상수의 이름을 문자열로 반환")
    void name() {
        String east = Direction.EAST.name();
        String west = Direction.WEST.name();
        String south = Direction.SOUTH.name();
        String north = Direction.NORTH.name();

        assertThat(east).isEqualTo("EAST");
        assertThat(west).isEqualTo("WEST");
        assertThat(south).isEqualTo("SOUTH");
        assertThat(north).isEqualTo("NORTH");
    }

    @Test
    @DisplayName("열거형 메서드 ordinal() - 열거형 상수가 정의된 순서를 반환 (0부터 시작)")
    void ordinal() {
        int east = Direction.EAST.ordinal();
        int west = Direction.WEST.ordinal();
        int south = Direction.SOUTH.ordinal();
        int north = Direction.NORTH.ordinal();

        assertThat(east).isEqualTo(0);
        assertThat(west).isEqualTo(1);
        assertThat(south).isEqualTo(2);
        assertThat(north).isEqualTo(3);
    }
    
    @Test
    @DisplayName("열거형 메서드 valueOf() - 지정된 열거형에서 name과 일치하는 열거형 상수를 반환")
    void valueOf() {
        Direction east = Enum.valueOf(Direction.class, "EAST");
        Direction west = Enum.valueOf(Direction.class, "WEST");
        Direction south = Enum.valueOf(Direction.class, "SOUTH");
        Direction north = Enum.valueOf(Direction.class, "NORTH");

        assertThat(east).isEqualTo(Direction.EAST);
        assertThat(west).isEqualTo(Direction.WEST);
        assertThat(south).isEqualTo(Direction.SOUTH);
        assertThat(north).isEqualTo(Direction.NORTH);
    }
    
    @Test
    @DisplayName("열거형 메서드 values() - 열거형에 정의된 모든 상수를 정의된 순서대로 반환")
    void values() {
        Direction[] directions = Direction.values();

        assertThat(directions[0]).isEqualTo(Direction.EAST);
        assertThat(directions[1]).isEqualTo(Direction.WEST);
        assertThat(directions[2]).isEqualTo(Direction.SOUTH);
        assertThat(directions[3]).isEqualTo(Direction.NORTH);
    }

    @Test
    @DisplayName("열거형에 멤버 추가하기")
    void addEnumMember() {
        assertThat(Direction.EAST.arrow).isEqualTo("→");
        assertThat(Direction.WEST.arrow).isEqualTo("←");
        assertThat(Direction.SOUTH.arrow).isEqualTo("↓");
        assertThat(Direction.NORTH.arrow).isEqualTo("↑");
    }

    static class Unit {
        Direction direction;

        void faceEast() {
            direction = Direction.EAST;
        }
    }

    enum Direction {
        // 1) 열거형 상수의 값이 불규칙한 경우, 열거형 상수의 이름 옆에 원하는 값을 괄호와 함께 적어준다
        EAST("→"), WEST("←"), SOUTH("↓"), NORTH("↑"); // 끝에 ';' 추가

        // 2) 지정된 값을 저장할 수 있는 인스턴스 변수와 생성자를 새로 추가한다
        private final String arrow; // 정수를 저장할 필드 추가, 반드시 final이어야 하는 제약은 없음

        Direction(String arrow) { // 생성자 추가
            this.arrow = arrow;
        }

        public String getArrow() {
            return arrow;
        }
    }
}
