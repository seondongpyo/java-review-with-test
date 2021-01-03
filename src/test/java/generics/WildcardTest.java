package generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WildcardTest {

    @Test
    @DisplayName("와일드카드 - 지네릭 타입에 다형성 적용")
    void wildcard() {
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        fruitBox.add(new Banana());
        fruitBox.add(new Orange());

        List<Juice> juices = JuiceMaker.makeJuice(fruitBox);

        assertThat(juices.get(0).name).isEqualTo("Banana Juice");
        assertThat(juices.get(1).name).isEqualTo("Orange Juice");
    }

    static class JuiceMaker {
        static List<Juice> makeJuice(FruitBox<? extends Fruit> fruitBox) {
            List<Juice> juices = new ArrayList<>();

            for (Fruit fruit : fruitBox.getList()) {
                juices.add(new Juice(fruit));
            }

            return juices;
        }
    }

    static class Juice {
        String name;

        public Juice(Fruit fruit) {
            name = fruit.name + " Juice";
        }
    }

    static class FruitBox<T extends Fruit> {
        ArrayList<T> list = new ArrayList<>();

        void add(T item) {
            list.add(item);
        }

        T get(int index) {
            return list.get(index);
        }

        ArrayList<T> getList() {
            return list;
        }
    }

    static class Fruit {
        String name;
    }

    static class Orange extends Fruit {
        public Orange() {
            name = "Orange";
        }
    }

    static class Banana extends Fruit {
        public Banana() {
            name = "Banana";
        }
    }
}