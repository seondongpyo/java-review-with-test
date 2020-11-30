package method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MethodTest {

    private int intValue = 10;
    private String stringValue = "string";
    private float floatValue = 3.14f;

    @Test
    @DisplayName("메서드 정의 - 반환타입이 있고, 매개변수도 있는 경우")
    void method_has_return_type_and_parameter() {
        int addValue = add(10, 20);

        assertThat(addValue).isEqualTo(30);
    }

    @Test
    @DisplayName("메서드 정의 - 반환타입이 있고, 매개변수가 없는 경우")
    void method_has_return_type_but_no_parameter() {
        String stringValue = getStringValue();

        assertThat(stringValue).isEqualTo("string");
    }

    @Test
    @DisplayName("메서드 정의 - 반환타입이 없고, 매개변수가 있는 경우")
    void method_has_no_return_type_but_parameter() {
        setIntValue(20);

        assertThat(intValue).isEqualTo(20);
    }

    @Test
    @DisplayName("메서드 정의 - 반환타입이 없고, 매개변수도 없는 경우")
    void method_has_no_return_type_and_parameter() {
        setFloatValueToZero();

        assertThat(floatValue).isEqualTo(0.0f);
    }

    @Test
    @DisplayName("메서드 오버로딩 : 이름이 같으나, 시그니처(매개변수의 갯수 또는 타입)가 다른 메서드 (반환 타입과는 관계 X)")
    void method_overloading() {
        int addIntValue = add(10, 20);
        double addDoubleValue = add(10.0, 20.0);

        assertThat(addIntValue).isEqualTo(30);
        assertThat(addDoubleValue).isEqualTo(30.0);
    }

    @Test
    @DisplayName("클래스 메서드와 인스턴스 메서드")
    void classMethodAndInstanceMethod() {
        /*
            << 클래스 메서드 >>
            - 객체의 생성 없이 '클래스이름.메서드이름()'으로 호출 가능
            - 인스턴스 변수나 인스턴스 메서드와 관련 없는 작업을 하는 메서드
            - 메서드 내에서 인스턴스 변수는 사용할 수 없음
            - 메서드 내에서 인스턴스 변수를 사용하지 않는다면 static 키워드를 붙이는 것을 고려한다
                => 메서드 호출 시간이 짧아지기 때문에 효율이 높아짐
                   인스턴스 메서드는 호출 시 메서드를 찾는 과정이 추가적으로 필요하기 때문에 시간이 더 걸림
         */
        int addByClassMethod = Calculator.add(20, 30);
        assertThat(addByClassMethod).isEqualTo(50);

        /*
            << 인스턴스 메서드 >>
            - 인스턴스를 먼저 생성 후, '참조변수.메서드이름()'으로 호출 가능
            - 인스턴스 변수나 인스턴스 메서드와 관련된 작업을 하는 메서드
            - 메서드 내에서 인스턴스 변수 사용 가능
         */
        Calculator calc = new Calculator();
        int addByInstanceMethod = calc.add();
        assertThat(addByInstanceMethod).isEqualTo(30);

        // Q. 인스턴스를 생성한 후 클래스 메서드를 호출하게 되면?
        //  => 인스턴스가 생성될 때 메모리의 힙 영역에 저장되는데, 인스턴스를 생성하려면 클래스를 먼저 로드해야 되고
        //     이 때 클래스 멤버들은 메서드 영역에 저장되어 있기 때문에 추가적인 메모리 사용이 불필요하기 때문
        //     또한 다른 사용자가 코드를 봤을 때 클래스 메서드가 아닌 인스턴스 메서드로 오해할 수 있음
        int add = calc.add(10, 20); // 정상적으로 실행은 되나, 권장하지 않음
        assertThat(add).isEqualTo(30);
    }

    private int add(int a, int b) {
        return a + b;
    }

    private double add(double a, double b) {
        return a + b;
    }

    private String getStringValue() {
        return stringValue;
    }

    private void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    private void setFloatValueToZero() {
        floatValue = 0.0f;
    }
}

class Calculator {
    int a = 10; // 인스턴스 변수
    int b = 20; // 인스턴스 변수

    // 인스턴스 메서드
    int add() {
        return a + b;
    }

    // 클래스 메서드
    static int add(int x, int y) {
        return x + y;
    }
}

class TestClass {

    int localVariable;
    static int classVariable;

    void instanceMethod1() {}
    static void staticMethod1() {}

    void instanceMethod2() {
        classVariable = 20; // 클래스 변수 사용 가능
        staticMethod1(); // static 메서드 호출 가능

        localVariable = 10; // 인스턴스 변수 사용 가능
        instanceMethod1(); // 인스턴스 메서드 호출 가능
    }

    static void staticMethod2() {
        classVariable = 30; // 클래스 변수 사용 가능
        staticMethod1(); // static 메서드 호출 가능

//        localVariable = 10; // 에러
//        instanceMethod2(); // 에러
        // => 클래스 멤버들은 인스턴스를 생성하지 않고도 사용할 수 있는데,
        //    클래스 메서드를 호출하는 경우, 호출 시점에 인스턴스가 생성되어 있을 수도, 또는 없을 수도 있다
        //    따라서, 인스턴스를 생성해야만 사용할 수 있는 인스턴스 멤버들은 사용할 수 없다
        //    반대로 인스턴스 메서드에서 클래스 멤버를 사용하고자 하는 경우,
        //    인스턴스 멤버가 존재하는 시점에 이미 클래스 멤버가 메모리에 존재하기 때문에 사용할 수 있다
    }
}
