package stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionalTest {

    @Test
    @DisplayName("Optional.of")
    void optional_of() {
        String str = "hello";
        
        // Optional<T> - T타입의 객체를 감싸는 래퍼 클래스, null 체크를 위한 if문 없이도 NullPointerException이 발생하지 않는다
        Optional<String> optionalString = Optional.of(str);
        assertThat(optionalString).isNotNull();
        assertThat(optionalString.get()).isEqualTo("hello");

        // Optional.of() - 매개변수의 값이 null이면 NullPointerException이 발생한다
        assertThrows(NullPointerException.class, () -> {
            Optional<String> optionalOf = getOptionalOf(null);
            System.out.println("optionalOf = " + optionalOf);
        });
    }

    @Test
    @DisplayName("Optional.ofNullable")
    void optional_ofNullable() {
        // Optional.ofNullable() - 참조변수의 값이 null일 가능성이 있으면 of() 대신 ofNullable()을 사용해야 한다
        Optional<String> optionalOfNullable = getOptionalOfNullable(null);
        assertThat(optionalOfNullable).isNotNull();
    }

    @Test
    @DisplayName("Optional.empty")
    void optional_empty() {
        // Optional.empty() - Optional<T> 타입의 참조변수를 기본값으로 초기화할 때 사용, null로 초기화하는 것보다 바람직함
        Optional<String> emptyOptional = Optional.empty();

        assertThat(emptyOptional).isEmpty();
    }

    public Optional<String> getOptionalOf(String value) {
        return Optional.of(value);
    }

    public Optional<String> getOptionalOfNullable(String value) {
        return Optional.ofNullable(value);
    }
}
