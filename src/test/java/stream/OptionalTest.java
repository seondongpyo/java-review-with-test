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
        // Optional.ofNullable() - 매개변수가 null일 가능성이 있으면 of() 대신 ofNullable()을 사용해야 한다
        Optional<String> optionalOfNullable = getOptionalOfNullable(null);
        assertThat(optionalOfNullable).isNotNull();
    }

    public Optional<String> getOptionalOf(String value) {
        return Optional.of(value);
    }

    public Optional<String> getOptionalOfNullable(String value) {
        return Optional.ofNullable(value);
    }
}
