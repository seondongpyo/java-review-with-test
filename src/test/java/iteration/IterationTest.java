package iteration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class IterationTest {

    @Test
    @DisplayName("반복문 - for")
    void iteration_for() {
        int initialValue = 0;
        char initialChar = 'A';
        StringBuilder initialBuilder = new StringBuilder();

        for (int i = 0; // 초기식      ① 시작과 함께 최초에 1번 실행
             i < 10;    // 조건식      ② 조건식 검사
             i++) {     // 증감식      ④ 증감식 실행 (→ ② 조건식 검사 → ③ 루프 수행 → ④ 증감식 실행 ...)

            // ③ 루프 수행
            initialValue += 1;
            initialChar += 1;
            initialBuilder.append(i);
        }

        assertThat(initialValue).isEqualTo(10);
        assertThat(initialChar).isEqualTo('K');
        assertThat(initialBuilder.toString()).isEqualTo("0123456789");
    }

    @Test
    @DisplayName("반복문 - 중첩 for")
    void iteration_nested_for() {
        StringBuilder star = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= i; j++) {
                star.append("*");
            }
            star.append("\n");
        }

        assertThat(star).containsSequence("*****").doesNotContain("******");
    }

    @Test
    @DisplayName("반복문 - Enhanced for")
    void iteration_enhanced_for() {
        Map<String, Integer> foods = new HashMap<>();
        foods.put("김밥", 2000);
        foods.put("파스타", 8000);
        foods.put("떡볶이", 4000);
        foods.put("햄버거", 6000);
        foods.put("라면", 3000);

        List<String> lunchCandidates = new ArrayList<>();
        for (String food : foods.keySet()) {
            int foodPrice = foods.get(food);

            // 오늘 점심은 돈이 모자라서 5천원 미만으로 해결해야 하는데...
            if (foodPrice < 5000) {
                lunchCandidates.add(food);
            }
        }

        assertThat(lunchCandidates).hasSize(3);
        assertThat(lunchCandidates).contains("김밥").contains("라면").contains("떡볶이")
                                    .doesNotContain("파스타").doesNotContain("햄버거");
    }

    @Test
    @DisplayName("반복문 - while")
    void iteration_while() {
        int sum = 0;
        int count = 0;

        while (count < 10) { // ① 조건식 검사
            sum += 2;   // ② 루프 실행
            count++;    // ③ 증감식 실행 (→ ① 조건식 검사 → ② 루프 수행 → ③ 증감식 실행 ...)
        }

        assertThat(sum).isEqualTo(20);
    }

    @Test
    @DisplayName("반복문 - do / while")
    void iteration_do_while() {
        int sum = 0;

        do {
            sum += 1; // 먼저 루프를 한 번 실행한 후에 조건식을 검사한다. => 조건식의 결과와 상관없이 무조건 한 번은 루프가 실행된다.

        } while (sum < 1);

        assertThat(sum).isEqualTo(1).isNotEqualTo(0); // 조건식 때문에 0일 것 같지만 결과는 1
    }

    @Test
    @DisplayName("반복문 - continue 키워드")
    void iteration_continue_keyword() {
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue; // 뒷 부분을 건너뛰고 다음 조건식의 판단으로 넘어감
            }

            sum += i;
        }

        assertThat(sum).isEqualTo(25);
    }

    @Test
    @DisplayName("반복문 - break 키워드")
    void iteration_break_keyword() {
        int num = 1;
        int sum = 0;

        while (true) {
            sum += num;

            if (num == 100) {
                break;  // 호출되는 시점에 반복을 종료 후 반복문 다음 명령문을 실행함
            }

            num++;
        }

        sum *= 10;

        assertThat(sum).isEqualTo(50500);
    }

    @Test
    @DisplayName("반복문에 이름을 붙일 수 있다")
    void iteration_naming() {
        Loop1:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5) {
                    break Loop1;
                }
            }
        }
    }
}
