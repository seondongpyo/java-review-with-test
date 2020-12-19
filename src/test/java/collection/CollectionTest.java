package collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionTest {

    @Test
    @DisplayName("컬렉션 프레임워크")
    void collectionFramework() {
        /*
            << 컬렉션 프레임워크 (collection framework) >>
            - 데이터 군(그룹)을 저장하는 클래스들을 표준화한 설계
            - 다수의 데이터를 다루는 데 필요한 다양하고 풍부한 클래스들을 제공한다
            - 컬렉션 데이터 그룹을 크게 3가지 타입이 존재한다고 인식하고 
              각 컬렉션을 다루는 데 필요한 기능을 가진 3개의 인터페이스(List, Set, Map)를 정의하였다
            - 3개의 인터페이스 중 List와 Set의 공통된 부분을 다시 뽑아서 새로운 인터페이스인 Collection을 추가로 정의하였다
         */

        // List와 Set 인터페이스를 구현한 클래스들은 서로 많은 공통 부분이 있어서, 공통된 부분을 뽑아 Collection 인터페이스를 정의함
        assertThat(new ArrayList<>()).isInstanceOf(List.class).isInstanceOf(Collection.class);
        assertThat(new HashSet<>()).isInstanceOf(Set.class).isInstanceOf(Collection.class);
        
        // Map 인터페이스는 List, Set과는 전혀 다른 형태로 컬렉션을 다루기 때문에 같은 상속계층도에 포함되지 못함
        assertThat(new HashMap<>()).isInstanceOf(Map.class).isNotInstanceOf(Collection.class);
    }

    @Test
    @DisplayName("컬렉션 프레임워크 - List")
    void list() {
        /*
            << 컬렉션 프레임워크 - List >>
            - 순서가 있는 데이터의 집합
            - 데이터의 중복을 허용함
            - 구현 클래스 : ArrayList, LinkedList, Stack, Vector 등
         */

        List<String> memberList = new ArrayList<>();
        memberList.add("홍길동");
        boolean isHongGilDongAdded = memberList.add("홍길동"); // 데이터를 추가할 때 성공하면 true, 실패하면 false 반환

        assertThat(new ArrayList<>()).isInstanceOf(List.class);
        assertThat(new LinkedList<>()).isInstanceOf(List.class);
        assertThat(new Stack<>()).isInstanceOf(List.class);
        assertThat(new Vector<>()).isInstanceOf(List.class);
        assertThat(memberList).hasSize(2);
        assertThat(isHongGilDongAdded).isTrue(); // 데이터의 중복을 허용함
        assertThat(memberList.get(0)).isEqualTo("홍길동");
        assertThat(memberList.get(1)).isEqualTo("홍길동");
    }
    
    @Test
    @DisplayName("컬렉션 프레임워크 - Set")
    void set() {
        /*
            << 컬렉션 프레임워크 - Set >>
            - 순서를 유지하지 않는 데이터의 집합
            - 데이터의 중복을 허용하지 않음
            - 구현 클래스 : HashSet, TreeSet 등
         */

        Set<String> memberSet = new HashSet<>();
        memberSet.add("홍길동");
        memberSet.add("고길동");
        boolean isHongGilDongAdded = memberSet.add("홍길동"); // 데이터를 추가할 때 성공하면 true, 실패하면 false 반환

        assertThat(new HashSet<>()).isInstanceOf(Set.class);
        assertThat(new TreeSet<>()).isInstanceOf(Set.class);
        assertThat(memberSet).hasSize(2);
        assertThat(isHongGilDongAdded).isFalse(); // 데이터의 중복을 허용하지 않음
    }
    
    @Test
    @DisplayName("컬렉션 프레임워크 - Map")
    void map() {
        /*
            << 컬렉션 프레임워크 - Map >>
            - 키(key)와 값(value)의 쌍(pair)으로 이루어진 데이터의 집합
            - 순서를 유지하지 않음
            - 키는 중복을 허용하지 않음
            - 값은 중복을 허용함
            - 구현 클래스 : HashMap, TreeMap, Hashtable, Properties 등
         */

        Map<String, String> memberMap = new HashMap<>();
        memberMap.put("집주인", "고길동");
        memberMap.put("조카", "박희동");
        memberMap.put("옆집사람", "마이콜");
        memberMap.put("세입자", "도우너");
        String existingValue = memberMap.put("세입자", "둘리"); // map.put(key, value) : 동일한 키로 데이터('둘리')를 추가할 경우, 기존 값 데이터('도우너')를 반환
        memberMap.put("아기공룡", "둘리"); // 값은 중복을 허용함

        assertThat(new HashMap<>()).isInstanceOf(Map.class);
        assertThat(new TreeMap<>()).isInstanceOf(Map.class);
        assertThat(new Hashtable<>()).isInstanceOf(Map.class);
        assertThat(new Properties()).isInstanceOf(Map.class);
        assertThat(memberMap.get("세입자")).isEqualTo("둘리").isNotEqualTo("도우너"); // 키는 중복을 허용하지 않음, 값이 '도우너'에서 '둘리'가 됨
        assertThat(existingValue).isEqualTo("도우너");
        assertThat(memberMap.get("아기공룡")).isEqualTo("둘리");
    }
}
