package exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionTest3 {

    @Test
    @DisplayName("예외 회피하기")
    void throwsException() {
        /*
            << 예외 회피하기(넘기기) >>
            - 해당 메서드를 사용할 때 발생할 수 있는 예외를 미리 명시할 수 있기 때문에,
              발생 가능한 예외를 사용자가 충분히 인지할 수 있으며 그에 대한 처리까지도 강제할 수 있다
         */
        try {
            method(); // 메서드 내부에서 예외를 처리하지 않고 회피했으므로 메서드를 직접 호출하는 곳에서 예외를 처리해야 한다
        } catch (ArrayIndexOutOfBoundsException e) {
            // 예외 처리
        }

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> { method(); });
        assertThrows(ArrayIndexOutOfBoundsException.class, this::method);
        // 또는
        assertThatThrownBy(() -> method()).isInstanceOf(ArrayIndexOutOfBoundsException.class);
        assertThatThrownBy(this::method).isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("finally 블록 : 예외 발생 여부와 관계 없이 항상 수행된다")
    void finallyBlock() {
        assertThat(finallyTest(10, 2)).isEqualTo(6);
        assertThat(finallyTest(1, 0)).isEqualTo(2);
    }

    @Test
    @DisplayName("사용자 정의 예외 만들기")
    void customException() {
        assertThrows(MyException.class, this::throwCustomException);
        assertThat(getExceptionErrorCode()).isEqualTo(100);
    }

    void method() throws ArrayIndexOutOfBoundsException { // 예외 회피하기(던지기)
        int[] array = new int[1];
        array[1] = 10;
    }

    int finallyTest(int a, int b) {
        int result = 0;

        try {
            result = a / b; // a / b의 결과값을 변수에 저장
        } catch (ArithmeticException e) {
            result = a; // 예외 발생 시 첫 번째 매개변수 값을 변수에 저장
        } finally {
            result += 1; // 예외 발생 여부와 상관 없이 변수 값에 1을 더하기
        }

        return result;
    }

    void throwCustomException() throws MyException {
        throw new MyException("사용자 정의 예외 발생");
    }

    int getExceptionErrorCode() {
        try {
            throwCustomException();
        } catch (MyException e) {
            return e.ERROR_CODE;
        }

        return 0;
    }

    // 사용자 정의 예외 클래스
    class MyException extends Exception {
        private final int ERROR_CODE; // 에러 코드 값

        public MyException(String message) {
            super(message); // 조상 클래스인 Exception 클래스의 생성자 호출
            ERROR_CODE = 100;
        }

        public int getErrorCode() {
            return ERROR_CODE;
        }
    }
}
