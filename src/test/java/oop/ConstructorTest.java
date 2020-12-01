package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConstructorTest {

    /*
        << 생성자 >>
        - 인스턴스가 생성될 때마다 호출되는 인스턴스 초기화 메서드
        - 인스턴스 변수의 초기화 또는 인스턴스 생성 시 수행할 작업에 사용
        - 모든 클래스에는 반드시 하나 이상의 생성자가 있어야 한다

        << 생성자의 조건 >>
        - 기본적으로 메서드와 동일하나, 생성자의 이름은 클래스의 이름과 같아야 한다
        - 생성자는 반환값이 없는 메서드지만 void 키워드를 사용하진 않는다
     */

    @Test
    @DisplayName("매개변수가 없는 기본 생성자")
    void defaultConstructor() {
        Car car = new Car(); // 매개변수가 없는 기본 생성자 호출

        assertThat(car.name).isNull();
        assertThat(car.price).isZero();
    }

    @Test
    @DisplayName("this()를 이용하여 다른 생성자 호출하기")
    void callConstructorByThis() {
        Book book = new Book(); // 기본 생성자 호출 시, 다른 생성자가 호출되어 인스턴스 변수들이 초기화됨

        assertThat(book.author).isEqualTo("허균");
        assertThat(book.title).isEqualTo("홍길동전");
        assertThat(book.price).isEqualTo(10000);
    }

    @Test
    @DisplayName("매개변수가 있는 생성자")
    void constructorWithParameters() {
        Car car = new Car("Mercedes", 200000000); // 매개변수가 있는 생성자 호출

        assertThat(car.name).isEqualTo("Mercedes");
        assertThat(car.price).isEqualTo(200000000);
    }

    @Test
    @DisplayName("생성자를 통한 인스턴스의 복사")
    void copyInstanceWithConstructor() {
        Car car1 = new Car("Lamborghini", 500000000);
        Car car2 = new Car(car1); // 생성자를 통한 인스턴스의 복사

        assertThat(car1.name).isEqualTo(car2.name).isEqualTo("Lamborghini");
        assertThat(car1.price).isEqualTo(car2.price).isEqualTo(500000000);
    }

    private class Book {
        String title;
        String author;
        int price;

        public Book() {
            this("홍길동전", "허균", 10000); // this()를 이용하여 생성자에서 다른 생성자 호출하기
        }

        public Book(String title, String author, int price) {
            this.title = title;
            this.author = author;
            this.price = price;
        }
    }

    private class Car {

        String name;
        int price;

        public Car() { // 매개변수가 없는 기본 생성자
            /*
                << 기본 생성자 >>
                - 클래스에 생성자가 하나도 없다면 컴파일러가 기본 생성자를 추가하지만,
                  사용자가 정의한 생성자가 하나라도 존재하면 컴파일러는 기본 생성자를 추가하지 않는다
                - 모든 클래스에는 반드시 하나 이상의 생성자가 잇어야 한다
             */

            method1(); // 인스턴스 생성 시 필요한 작업을 수행할 수 있다
        }

        public Car(String name, int price) { // 매개변수가 있는 생성자
            /*
                << 참조변수 this >>
                - 인스턴스 자신을 가리키는 참조변수이며, 인스턴스의 주소가 저장되어 있음
                - 모든 인스턴스 메서드에 지역 변수로 숨겨진 채로 존재함
                - 보통 인스턴스 변수와 지역 변수를 구별하기 위해 사용
             */
            this.name = name;
            this.price = price;
        }

        public Car(Car car) {
            this(car.name, car.price);  // 생성자를 통한 인스턴스의 복사 (인스턴스의 주소를 복사하는 게 아닌 인스턴스 변수들의 값을 복사함)
        }

        private void method1() {
            System.out.println("Car.method1");
        }
    }
}
