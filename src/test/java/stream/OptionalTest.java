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

    @Test
    @DisplayName("Optional<T> 객체의 값 가져오기 - get")
    void optional_get() {
        Optional<String> hello = Optional.of("hello");

        assertThat(hello.get()).isEqualTo("hello");
    }

    @Test
    @DisplayName("Optional<T> 객체의 값 가져오기 - orElse")
    void optional_orElse() {
        // orElse(String other) - 참조변수에 저장된 값이 null일 경우 대체할 값을 지정할 수 있다
        String hello = getOptionalOrElse("hello");
        String orElse = getOptionalOrElse(null);

        assertThat(hello).isEqualTo("hello");
        assertThat(orElse).isEqualTo("This is null");
    }

    @Test
    @DisplayName("Optional<T> 객체의 값 가져오기 - orElseGet")
    void optional_orElseGet() {
        // orElseGet(Supplier<? extends T> other) - null을 대체할 값을 반환하는 람다식을 지정할 수 있다
        String hello = getOptionalOrElseGet("hello");
        String orElseGet = getOptionalOrElseGet(null);

        assertThat(hello).isEqualTo("hello");
        assertThat(orElseGet).isEqualTo("");
    }

    @Test
    @DisplayName("Optional<T> 객체의 값 가져오기 - orElseThrow")
    void optional_orElseThrow() {
        // orElseThrow(Supplier<? extends X> exceptionSupplier) - null일 때 지정된 예외를 발생시킨다
        String hello = getOptionalOrElseThrow("hello");
        assertThat(hello).isEqualTo("hello");

        assertThrows(NullPointerException.class, () -> {
            getOptionalOrElseThrow(null);
        });
    }

    @Test
    @DisplayName("Optional<T> 객체의 값 가져오기 - isPresent")
    void optional_isPresent() {
        // isPresent() - Optional<T> 객체의 값이 null이면 false를, 값이 있으면 true를 반환한다
        assertThat(getOptionalIsPresent("hello")).isTrue();
        assertThat(getOptionalIsPresent(null)).isFalse();
    }

    public Optional<String> getOptionalOf(String value) {
        return Optional.of(value);
    }

    public Optional<String> getOptionalOfNullable(String value) {
        return Optional.ofNullable(value);
    }

    public String getOptionalOrElse(String value) {
        return Optional.ofNullable(value).orElse("This is null");
    }

    public String getOptionalOrElseGet(String value) {
        return Optional.ofNullable(value).orElseGet(String::new);
    }

    public String getOptionalOrElseThrow(String value) {
        return Optional.ofNullable(value).orElseThrow(NullPointerException::new);
    }

    public boolean getOptionalIsPresent(String value) {
        return Optional.ofNullable(value).isPresent();
    }
}
