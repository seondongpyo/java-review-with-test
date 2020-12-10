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

    void method() throws ArrayIndexOutOfBoundsException { // 예외 회피하기(던지기)
        int[] array = new int[1];
        array[1] = 10;
    }
}
