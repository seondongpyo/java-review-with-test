package oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecursiveCallTest {

    @Test
    @DisplayName("재귀 호출 - 팩토리얼")
    void recursiveCall() {
        long factorial = factorial(10);

        assertThat(factorial).isEqualTo(3628800);
    }

    private long factorial(int n) {
        long result;

        if (n == 1) {
            result = 1;
        } else {
            result = n * factorial(n - 1);
        }

        return result;
    }
}
