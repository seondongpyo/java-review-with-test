package array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayComparisonTest {

    @Test
    @DisplayName("배열의 비교 - Object.equals()")
    void array_Object_equals() {
        int[] intArray1 = {1, 2, 3};
        int[] intArray2 = {1, 2, 3};
        int[] intArray3 = intArray1;

        String[] strArray1 = {"a", "b", "c"};
        String[] strArray2 = {"a", "b", "c"};

        Member member1 = new Member("member1", 20, "서울");
        Member member2 = new Member("member2", 30, "경기");
        Member[] memberArray1 = new Member[]{member1, member2};
        Member[] memberArray2 = new Member[]{member1, member2};

        // Object.equals()를 이용한 비교
        //  => 두 배열이 같은 배열 요소들을 같은 순서로 포함하고 있어도, 주소 값이 다르면 두 배열은 다른 배열이다
        assertThat(intArray1.equals(intArray2)).isFalse();
        assertThat(intArray1.equals(intArray3)).isTrue();
        assertThat(strArray1.equals(strArray2)).isFalse();
        assertThat(memberArray1.equals(memberArray2)).isFalse();
    }

    @Test
    @DisplayName("배열의 비교 - Arrays.equals()")
    void array_Arrays_equals() {
        int[] intArray1 = {1, 2, 3};
        int[] intArray2 = {1, 2, 3};
        int[] intArray3 = intArray1;
        int[] intArray4 = {2, 1, 3};
        int[] intArray5 = {1, 2, 3, 0};

        String a1 = new String("a");
        String a2 = new String("a");
        String[] strArray1 = {a1, "b", "c"};
        String[] strArray2 = {a2, "b", "c"};

        Member member1_1 = new Member("member1", 20, "서울");
        Member member1_2 = member1_1;
        Member member1_3 = new Member("member1", 20, "서울");
        Member member2 = new Member("member2", 30, "경기");
        Member[] memberArray1 = new Member[]{member1_1, member2};
        Member[] memberArray2 = new Member[]{member1_2, member2};
        Member[] memberArray3 = new Member[]{member1_3, member2};

        // (2) Arrays.equals()를 이용한 비교
        /*
            Two arrays are considered equal if both arrays contain the same number of elements,
            and all corresponding pairs of elements in the two arrays are equal.
            In other words, two arrays are equal if they contain the same elements in the same order.
             => 두 배열이 같은 배열 요소들을 같은 순서로 포함하고 있으면 두 배열은 동일하다
         */
        assertThat(Arrays.equals(intArray1, intArray2)).isTrue(); // 배열 요소의 갯수와 순서가 동일
        assertThat(Arrays.equals(intArray1, intArray4)).isFalse(); // 배열 요소의 갯수와 값들은 동일하나, 순서가 다름
        assertThat(Arrays.equals(intArray1, intArray5)).isFalse(); // 두 배열 모두 1, 2, 3을 포함하지만, 배열 요소의 갯수가 다름
        assertThat(Arrays.equals(strArray1, strArray2)).isTrue();
        assertThat(member1_1 == member1_2).isTrue();
        assertThat(Arrays.equals(memberArray1, memberArray2)).isTrue(); // member1_1 == member1_2 이므로 true

        // Arrays.equals()는 내부적으로 equals() 비교를 수행한다
        //  => 객체 비교 시, equals() 오버라이딩 여부에 따라 결과가 다르게 나온다
        //  => 두 객체의 특정 필드의 값이 같은지를 확인하려면 Object.equals()를 적절히 오버라이딩해야 한다

        // 1) equals() 오버라이딩 X : 두 객체의 equals() 비교 시 false (Object.equals()의 == 비교와 동일)
//        assertThat(member1_1.equals(member1_3)).isFalse();
//        assertThat(Arrays.equals(memberArray1, memberArray3)).isFalse();

        //  2) equals() 오버라이딩 O : 두 객체의 equals() 비교 시 true
        assertThat(member1_1.equals(member1_3)).isTrue();
        assertThat(Arrays.equals(memberArray1, memberArray3)).isTrue();

        /*
            public static boolean equals(Object[] a, Object[] a2) {
                if (a==a2)
                    return true;                                    // 같은 주소를 바라보면 true
                if (a==null || a2==null)
                    return false;                                   // 두 배열 중 하나라도 null이면 false

                int length = a.length;
                if (a2.length != length)
                    return false;                                   // 두 배열의 길이가 다르면 false

                for (int i=0; i<length; i++) {
                    Object o1 = a[i];
                    Object o2 = a2[i];
                    if (!(o1==null ? o2==null : o1.equals(o2)))
                        return false;                               // 두 배열의 배열 요소가 다르면 false
                }

                return true;
            }
         */
    }

    static class Member {

        private String name;
        private int age;
        private String address;

        public Member(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Member member = (Member) o;

            if (getAge() != member.getAge()) return false;
            if (getName() != null ? !getName().equals(member.getName()) : member.getName() != null) return false;
            return getAddress() != null ? getAddress().equals(member.getAddress()) : member.getAddress() == null;
        }

        @Override
        public int hashCode() {
            int result = getName() != null ? getName().hashCode() : 0;
            result = 31 * result + getAge();
            result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
            return result;
        }
    }
}
