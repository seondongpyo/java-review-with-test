package exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.DataFormatException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExceptionTest1 {

    /*
        << 프로그램 오류 >>
        - 컴파일 에러 : 컴파일 시에 발생하는 에러
        - 런타임 에러 : 실행 시에 발생하는 에러
        - 논리적 에러 : 실행은 되지만, 의도와 다르게 동작하는 것

        << 런타임 에러의 종류 >>
        - 에러(error) : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
        - 예외(exception) : 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류

        << Exception 클래스와 RuntimeException 클래스 >>
        - Exception 클래스 : 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
        - RuntimeException 클래스 : 프로그래머의 실수로 발생하는 예외
     */

    @Test
    @DisplayName("예외(exception)와 에러(error)")
    void exceptionAndError() {
       // 예외(exception)와 에러(error) 클래스는 Throwable 인터페이스를 구현한 클래스이다
       assertThatThrownBy(() -> { throw new Error(); }).isInstanceOf(Throwable.class);
       assertThatThrownBy(() -> { throw new Exception(); }).isInstanceOf(Throwable.class);
    }

    @Test
    @DisplayName("모든 예외의 최고 조상은 Exception 클래스이다")
    void exceptionParent() {
        assertThatThrownBy(() -> { throw new IOException(); }).isInstanceOf(Exception.class);
        assertThatThrownBy(() -> { throw new ClassNotFoundException(); }).isInstanceOf(Exception.class);
        assertThatThrownBy(() -> { throw new RuntimeException(); }).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Exception 클래스")
    void exception() {
        assertThatThrownBy(() -> { throw new FileNotFoundException(); }).isInstanceOf(Exception.class);
        assertThatThrownBy(() -> { throw new ClassNotFoundException(); }).isInstanceOf(Exception.class);
        assertThatThrownBy(() -> { throw new DataFormatException(); }).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("RuntimeException 클래스")
    void runtimeException() {
        assertThatThrownBy(() -> { throw new ArithmeticException(); }).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> { throw new ClassCastException(); }).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> { throw new NullPointerException(); }).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> { throw new IndexOutOfBoundsException(); }).isInstanceOf(RuntimeException.class);
    }
}
