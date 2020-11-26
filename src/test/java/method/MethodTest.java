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
