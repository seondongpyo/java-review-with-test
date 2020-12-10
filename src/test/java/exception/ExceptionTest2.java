package exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionTest2 {

    @Test
    @DisplayName("Exception 예제")
    void exceptionExercise() {
        assertThrows(ArithmeticException.class, () -> {
            int divideByZero = 100 / 0;
        });
    }

    @Test
    @DisplayName("강제로 예외 발생 시키기")
    void throwException() {
        Exception exception = new RuntimeException("고의로 예외 발생시킴");

        assertThrows(RuntimeException.class, () -> { throw exception; });
        assertThat(exception.getMessage()).isEqualTo("고의로 예외 발생시킴");
    }

    @Test
    @DisplayName("예외 처리하기 : try - catch")
    void tryCatch() {
        assertThat(divide(20, 2)).isEqualTo(10); // 0으로 나누지 않으므로 예외가 발생하지 않음
        assertDoesNotThrow(() -> { divide(10, 0); }); // 0으로 나누므로 예외가 발생하더라도 예외가 처리됨
        assertThat(divide(20, 0)).isEqualTo(0); // 예외 발생 시 0을 반환하도록 처리함
    }

    @Test
    @DisplayName("예외 메시지 출력하기 : e.getMessage()")
    void getMessage() {
        assertThat(getExceptionMessage(10, 2)).isEqualTo("Exception is not caught");
        assertThat(getExceptionMessage(10, 0)).isEqualTo("/ by zero"); // ArithmeticException 메시지 출력
    }

    @Test
    @DisplayName("멀티 catch 블록")
    void multiCatchBlock() {
        assertThat(divideMultiCatch(10, 0)).isEqualTo("/ by zero"); // 0으로 나눌 경우, ArithmeticException 발생
        assertThat(divideMultiCatch(20, 10)).isEqualTo("Index 2 out of bounds for length 2"); // 정상적으로 연산될 경우, ArrayIndexOutOfBoundsException 발생
    }

    @Test
    @DisplayName("checked 예외와 unchecked 예외")
    void checkedException() {
//        throw new Exception(); // 컴파일 에러
        // -> Exception 클래스와 자손들(= checked 예외)이 발생할 가능성이 있는 문장들은
        //    예외 처리를 하지 않을 경우 컴파일조차 되지 않는다

//        throw new RuntimeException(); // 컴파일 에러가 발생하지 않음
        // -> RuntimeException 클래스와 자손들(= unchecked 예외)에 해당하는 예외는
        //    프로그래머가 실수로 발생하는 것들이므로 예외 처리를 강제하지 않는다

        assertThrows(Exception.class, () -> { throw new Exception(); });
        assertThrows(RuntimeException.class, () -> { throw new RuntimeException(); });
    }

    int divide(int a, int b) {
        int result;

        // 예외 처리(exception handling)
        //  : 프로그램의 비정상적인 종료를 막고 정상적인 실행 상태를 유지하기 위함
        try {
            result = a / b;
        } catch (ArithmeticException e) {
            return 0;
        }

        return result;
    }

    String getExceptionMessage(int a, int b) {
        try {
            int result = a / b;
        } catch (ArithmeticException e) { // 예외 발생 시 해당 클래스의 인스턴스가 생성됨
            return e.getMessage(); // 인스턴스에 저장된 메시지 반환
        }

        return "Exception is not caught";
    }

    String divideMultiCatch(int a, int b) {
        try {
            int result = a / b; // 0으로 나눌 경우, ArithmeticException 발생
            int[] array = new int[2];
            array[2] = 10; // 0으로 나누지 않을 경우, 강제로 ArrayIndexOutOfBoundsException 발생

        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            // 멀티 catch 블록 : 하나의 catch 블록으로 여러 예외를 처리
            return e.getMessage();
        }

        return "None of exceptions is caught";
    }
}
