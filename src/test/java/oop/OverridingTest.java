package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OverridingTest {

    /*
        << 오버라이딩 (overriding) >>
        * 조상 클래스로부터 상속 받은 메서드의 내용을 상속 받는 클래스에 맞게 변경하는 것
        * 오버라이딩의 조건
            - 선언부(이름, 매개변수, 리턴타입)가 같아야 한다
            - 접근제어자를 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다
            - 조상 클래스의 메서드보다 많은 수의 예외를 선언할 수 없다
     */

    @Test
    @DisplayName("오버라이딩")
    void overriding() {
        Point point = new Point();
        Point3d point3d = new Point3d();

        // 자손 클래스는 조상 클래스의 메서드를 그대로 사용할 수 있다
        assertThat(point.getX()).isEqualTo(point.x);
        assertThat(point.getY()).isEqualTo(point.y);
        assertThat(point3d.getX()).isEqualTo(point3d.x);
        assertThat(point3d.getY()).isEqualTo(point3d.y);

        // 단, 자손 클래스의 메서드는 조상 클래스에서 호출할 수 없다
//        assertThat(point.getZ()); // 컴파일 에러
        assertThat(point3d.getZ()).isEqualTo(point3d.z);

        // 자손 클래스에 맞게 변경(재정의)해야 하는 경우, 조상 클래스의 메서드를 오버라이딩한다
        assertThat(point.getLocation()).isEqualTo("x : " + point.x + ", y : " + point.y);
        assertThat(point3d.getLocation()).isEqualTo("x : " + point3d.x + ", y : " + point3d.y + ", z : " + point3d.z);
    }

    @Test
    @DisplayName("오버라이딩 vs. 오버로딩")
    void differenceBetweenOverridingAndOverloading() {
        /*
            << 오버라이딩 vs. 오버로딩 >>
            * 오버라이딩 (overriding) : 조상으로부터 상속 받은 메서드의 내용을 변경하는 것 (change, modify)
            * 오버로딩 (overloading) : 한 클래스 내에 같은 이름의 메서드를 여러 개 정의하는 것 (new)
         */

        Point point = new Point();
        Point3d point3d = new Point3d();

        assertThat(point.getLocation()).isEqualTo("x : " + point.x + ", y : " + point.y);
        assertThat(point3d.getLocation()).isEqualTo("x : " + point3d.x + ", y : " + point3d.y + ", z : " + point3d.z);  // 오버라이딩
        assertThat(point3d.getLocation(1, 2, 3)).isEqualTo("x : 1, y : 2, z : 3"); // 오버로딩
    }

    class Point {

        int x;
        int y;

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        String getLocation() {
            return "x : " + x + ", y : " + y;
        }
    }

    class Point3d extends Point {

        int z;

        int getZ() { // 자손 클래스만의 메서드 (조상 클래스에서 호출 불가능)
            return z;
        }

        // 오버라이딩 메서드
        String getLocation() { // 조상 메서드의 선언부와 일치해야 한다
            return "x : " + x + ", y : " + y + ", z : " + z;
        }

        // 오버로딩 메서드
        String getLocation(int x, int y, int z) { // 메서드의 이름이 같고 매개변수의 갯수, 타입이 다르다
            return "x : " + x + ", y : " + y + ", z : " + z;
        }

//        String getLocation() { // 메서드 중복 선언
//            return "(x, y, z) : (" + x + ", " + y + ", " + z + ")";
//        }
    }
}
