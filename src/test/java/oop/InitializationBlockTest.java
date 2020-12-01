package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitializationBlockTest {

    @Test
    @DisplayName("클래스 초기화 블록")
    void classInitializationBlock() {
        assertThat(StaticBlockTest.array.length).isEqualTo(10);
        assertThat(StaticBlockTest.array).contains(0).doesNotContain(10);
    }

    @Test
    @DisplayName("인스턴스 초기화 블록")
    void instanceInitializationBlock() {
        InstanceBlockTest test = new InstanceBlockTest();

        assertThat(test.number).isEqualTo(200); // 인스턴스 초기화 블록에서 100으로 값이 초기화됐지만, 이후 생성자가 호출되며 200으로 바뀜
    }

    @Test
    @DisplayName("클래스 변수와 인스턴스 변수의 초기화 순서")
    void initializationOrderTest() {
        assertThat(InitializationOrderTest.classVariant).isEqualTo(20);
        assertThat(new InitializationOrderTest().instanceVariant).isEqualTo(30);
    }
}

class StaticBlockTest {
    static int[] array = new int[10]; // 명시적 초기화 (각각의 array 배열 요소들의 현재 값은 기본값인 0)

    static {
        /*
            1) 클래스 초기화 블록
                - 생성자나 인스턴스 초기화 블록으로는 수행할 수 없는 클래스 변수의 복잡한 초기화에 사용됨
                - 클래스가 처음으로 메모리에 로딩될 때 단 한 번만 실행됨
         */
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
    }
}

class InstanceBlockTest {
    int number;

    {
        /*
            2) 인스턴스 초기화 블록
                - 생성자와 거의 차이가 없으므로 잘 사용되지 않음
                - 특히 여러 개의 생성자에서 공통적으로 수행되는 작업을 블록에 포함시켜 코드의 중복을 막을 때 사용함
                - 인스턴스가 생성될 때마다 '생성자보다 먼저' 실행된다
         */
        number = 100;
    }

    InstanceBlockTest() {
        number = 200;
    }
}

class InitializationOrderTest {
    /*
        << 필드의 초기화 순서 >>
        1) 클래스 변수 : ①기본값 → ②명시적 초기화 → ③클래스 초기화 블록
        2) 인스턴스 변수 : ⑴기본값 → ⑵명시적 초기화 → ⑶인스턴스 초기화 블록 → ⑷생성자
     */

    static int classVariant = 10; // ①기본값(0) → ②클래스 변수의 명시적 초기화(10)
    int instanceVariant = 10; // ⑴기본값(0) → ⑵인스턴스 변수의 명시적 초기화(10)

    static {
        classVariant = 20; // ③클래스 초기화 블록을 이용한 초기화(20)
    }

    {
        instanceVariant = 20; // ⑶인스턴스 초기화 블록을 이용한 초기화(20)
    }

    public InitializationOrderTest() {
        instanceVariant = 30; // ⑷생성자를 이용한 초기화(30)
    }
}