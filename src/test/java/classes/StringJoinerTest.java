package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;

class StringJoinerTest {

    @Test
    @DisplayName("StringJoiner : 문자열 결합 (JDK 1.8~)")
    void stringJoiner() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        String[] array = {"dog", "cat", "bear"};

        for (String animal : array) {
            joiner.add(animal); // '['과 ']' 사이에 문자열을 ','를 구분자로 하여 결합
        }

        assertThat(joiner.toString()).isEqualTo("[dog,cat,bear]");
    }
}
