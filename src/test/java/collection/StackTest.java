package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StackTest {

    private Stack<Integer> stack;

    @BeforeEach
    void initStack() {
        stack = new Stack<>();
    }

    @Test
    @DisplayName("Stack 클래스 empty() - Stack이 비어있는지 확인")
    void empty() {
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack).hasSize(0).isEmpty();
    }

    @Test
    @DisplayName("Stack 클래스 push() - Stack에 객체(item)를 저장")
    void push() {
        stack.push(1); // Pushes an item onto the top of this stack.
        stack.push(2);
        stack.push(3);

        assertThat(stack).hasSize(3);
        assertThat(stack).contains(1, 2, 3);
    }

    @Test
    @DisplayName("Stack 클래스 peek() - Stack의 맨 위에 저장된 객체를 반환 (꺼내진 않음)")
    void peek() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.peek()).isEqualTo(3); // Looks at the object at the top of this stack without removing it from the stack.
        assertThat(stack.peek()).isEqualTo(3).isNotEqualTo(2); // Stack에서 객체를 꺼내진 않음
        assertThrows(EmptyStackException.class, () -> {
            new Stack<Integer>().peek(); // Stack이 비었을 경우 EmptyStackException 예외 발생
        });
    }

    @Test
    @DisplayName("Stack 클래스 pop() - Stack의 맨 위에 저장된 객체를 꺼낸다")
    void pop() {
        stack.push(1);
        stack.push(2);

        assertThat(stack.pop()).isEqualTo(2); // Removes the object at the top of this stack and returns that object as the value of this function.
        assertThat(stack.pop()).isEqualTo(1);
        assertThrows(EmptyStackException.class, () -> {
            stack.pop(); // Stack이 비었을 경우 EmptyStackException 예외 발생
        });
    }

    @Test
    @DisplayName("Stack 클래스 search() - 주어진 객체의 위치를 반환 (배열과 달리 1부터 시작, 못 찾으면 -1 반환)")
    void search() {
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        stack.push(9);

        /*
            << 현재 스택 상황 >>
               객체  위치
                9    1
                7    2
                5    3
                3    4
                1    5
         */

        assertThat(stack.search(3)).isEqualTo(4);
        assertThat(stack.search(2)).isEqualTo(-1);
    }
}
