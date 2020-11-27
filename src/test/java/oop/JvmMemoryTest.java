package oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JvmMemoryTest { // 1) JvmMemoryTest 클래스가 메서드 영역에 로딩된다

    public static void main(String[] args) { // 2) main 메서드가 호출된다 -> 호출 스택 영역에 저장
        // 3) 인스턴스가 생성되기 전에 먼저 Book 클래스가 메서드 영역에 로딩되며, 클래스 변수(width, height)가 만들어지고 각각 25, 35로 초기화된다
        assertThat(Book.width).isEqualTo(25);
        assertThat(Book.height).isEqualTo(35);

        // 4) new 연산자에 의해 Book 인스턴스가 힙 영역에서 생성되고 인스턴스 변수가 생성되어 각각 기본값인 null, 0으로 초기화된다
        //    그리고 생성된 Book 인스턴스의 주소가 참조변수 book1에 저장된다
        Book book1 = new Book();
        Book book2 = new Book("Test", 20000); // 생성자를 통해 Book 인스턴스를 생성하면서 인스턴스 변수를 생성 및 초기화 후 주소를 참조변수 book2에 저장

        assertThat(book1.title).isNull();
        assertThat(book1.price).isZero();
        assertThat(book2.title).isEqualTo("Test");
        assertThat(book2.price).isEqualTo(20000);
    }

    @Test
    void jvmMemoryTest() {
        assertThat(Book.width).isEqualTo(25);
        assertThat(Book.height).isEqualTo(35);

        Book book1 = new Book();
        Book book2 = new Book("Test Book", 15000);

        assertThat(book1.title).isNull();
        assertThat(book1.price).isZero();
        assertThat(book2.title).isEqualTo("Test Book");
        assertThat(book2.price).isEqualTo(15000);
    }
}

class Book {

    String title;
    int price;

    public Book() {
    }

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    static int width = 25;
    static int height = 35;
}
