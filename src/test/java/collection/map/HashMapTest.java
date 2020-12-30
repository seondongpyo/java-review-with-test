package collection.map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapTest {

    /*
        << HashMap >>
        - Map 인터페이스를 구현한 클래스로, 키(key)와 값(value)을 묶어서 하나의 데이터(entry)로 저장한다
        - 해싱(hashing)을 사용하기 때문에, 많은 양의 데이터를 검색하는 데 있어서 성능이 뛰어나다
     */

    @Test
    @DisplayName("HashMap 클래스 put() - 지정된 키와 값을 저장")
    void put() {
        HashMap<Integer, String> users = new HashMap<>();
        users.put(1, "김길동");
        users.put(2, "이길동");
        users.put(3, "최길동");
        users.put(4, "홍길동");
        users.put(4, "박길동"); // 키는 중복을 허용하지 않음. 기존 키(4)에 대한 값이 '홍길동'에서 '박길동'으로 변경됨
        users.put(5, "박길동"); // 값은 중복을 허용함

        assertThat(users).hasSize(5);
        assertThat(users).containsValues("김길동", "이길동", "최길동", "박길동", "박길동");
        assertThat(users).doesNotContainValue("홍길동");
    }
    
    @Test
    @DisplayName("HashMap 클래스 putAll() - 전달한 Map에 저장된 모든 요소를 저장")
    void putAll() {
        HashMap<Integer, String> users = new HashMap<>();
        users.put(1, "김길동");
        users.put(2, "이길동");
        users.put(3, "최길동");
        users.put(4, "홍길동");

        HashMap<Integer, String> newUsers = new HashMap<>();
        newUsers.putAll(users); // users에 저장된 모든 요소를 newUsers에 저장, 'new HashMap<>(users)'와 동일함
        newUsers.put(5, "박길동");

        assertThat(newUsers).hasSize(5);
        assertThat(newUsers).containsValues("김길동", "이길동", "최길동", "홍길동", "박길동");
    }

    @Test
    @DisplayName("HashMap 클래스 getOrDefault() - 지정된 키의 값을 반환하되, 키를 못 찾으면 기본값으로 지정된 객체를 반환")
    void getOrDefault() {
        HashMap<Integer, String> users = new HashMap<>();
        users.put(1, "김길동");
        users.put(2, "이길동");
        users.put(3, "최길동");
        users.put(4, "홍길동");

        String findUser1 = users.getOrDefault(4, "비회원");
        String findUser2 = users.getOrDefault(5, "비회원");

        assertThat(findUser1).isEqualTo("홍길동");
        assertThat(findUser2).isEqualTo("비회원");
    }

    @Test
    @DisplayName("HashMap 클래스 replace() - 지정된 키의 값을 지정된 객체(값)로 대체")
    void replace() {
        HashMap<Integer, String> users = new HashMap<>();
        users.put(1, "김길동");
        users.put(2, "이길동");
        users.put(3, "최길동");
        users.put(4, "홍길동");

        String oldValue = users.replace(4, "박길동"); // 지정된 키의 값을 성공적으로 바꿨으면 기존 값을 반환
        boolean isReplaced1 = users.replace(1, "김길동", "염길동"); // 지정된 키와 객체(oldValue)가 모두 일치하는 경우에만 새 객체(newValue)로 대체
        boolean isReplaced2 = users.replace(2, "김길동", "염길동"); // 키(2)에 대한 객체는 '김길동'이 아닌 '이길동'이므로 대체하지 않음

        assertThat(oldValue).isEqualTo("홍길동");
        assertThat(users.get(4)).isEqualTo("박길동");
        assertThat(isReplaced1).isTrue();
        assertThat(isReplaced2).isFalse();
    }
}
