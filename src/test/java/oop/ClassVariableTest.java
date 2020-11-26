package oop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ClassVariableTest {

    @Test
    @DisplayName("선언위치에 따른 변수의 종류")
    void classVariables() {
        Variables variables1 = new Variables();
        Variables variables2 = new Variables();

        /*
            1) 인스턴스 변수 (여기서는 int instanceVariable)
            - 클래스 영역에 선언, 인스턴스를 생성할 때 만들어진다 (값을 사용하려면 인스턴스를 먼저 생성해야 함)
            - 인스턴스마다 별도의 저장공간을 가지므로 서로 다른 값을 가질 수 있다
            - 인스턴스마다 고유한 상태를 유지해야 하는 속성의 경우, 인스턴스 변수로 선언
            - '참조변수'.'인스턴스 변수명' 으로 사용 가능
         */
        variables1.instanceVariable = 10;
        variables2.instanceVariable = 20;
        assertThat(variables1.instanceVariable).isEqualTo(10);
        assertThat(variables2.instanceVariable).isEqualTo(20);

        /*
            2) 클래스 변수 (여기서는 static int staticVariable)
            - 클래스 영역에 선언, 인스턴스 변수 앞에 static 키워드를 붙이면 됨
            - 모든 인스턴스가 공통된 저장공간(변수)을 공유하게 됨
            - 한 클래스의 모든 인스턴스들이 공통적인 값을 유지해야 하는 속성의 경우, 클래스 변수로 선언
            - '클래스 이름'.'클래스 변수명' 으로 사용 가능
         */
        assertThat(Variables.staticVariable).isEqualTo(100);
        assertThat(variables1.staticVariable).isEqualTo(100);
        assertThat(variables2.staticVariable).isEqualTo(100);

        /*
            3) 지역 변수 (여기서는 int localVariable)
            - 메서드 영역 안에 선언되어 메서드 내에서만 사용 가능
            - 메서드가 종료되면 소멸되어 사용할 수 없음
            - 반복문(for, while) 블럭 내에 선언된 지역 변수는,
              지역 변수가 선언된 블럭 내에서만 사용 가능하며 블럭을 벗어나면 소멸하여 사용할 수 없음
         */
    }

}

class Variables {
    int instanceVariable;   // 인스턴스 변수
    static int staticVariable = 100; // 클래스 변수 (static 변수 또는 공유변수)

    void method() {
        int localVariable = 10; // 지역 변수
    }
}
