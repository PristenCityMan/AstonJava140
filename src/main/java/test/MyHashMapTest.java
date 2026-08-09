package test;

import main.ru.aston.hometask01.MyHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyHashMapTest {
    private MyHashMap<Integer, String> map;


    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();

    }

    @Test
    @DisplayName("Добавление и получение элементов")
    void testPutAndGet() {
        map.put(1, "1");
        map.put(2, "2");

        assertEquals(2, map.size());
        assertEquals("1", map.get(1));
        assertEquals("2", map.get(2));
    }

    @Test
    @DisplayName("Обновление по ключу")
    void testPutOverwrite() {
        map.put(10, "10");
        map.put(10, "010");

        assertEquals(1, map.size());
        assertEquals("010", map.get(10));
    }

    @Test
    @DisplayName("null-ключ и null-значение")
    void testNullKeyAndValue() {
        map.put(null, "null");
        map.put(0, null);

        assertEquals("null", map.get(null));
        assertNull(map.get(0));

    }

    @Test
    @DisplayName("Put и get с коллизией")
    void testCollisions() {
        class EqualHashObj {
            private final String value;

            EqualHashObj(String value) {
                this.value = value;
            }

            @Override
            public int hashCode() {
                return 1;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                return value.equals(((EqualHashObj) o).value);
            }
        }

            MyHashMap<EqualHashObj, String> collisionMap = new MyHashMap<>();
            EqualHashObj k1 = new EqualHashObj("1");
            EqualHashObj k2 = new EqualHashObj("2");

            collisionMap.put(k1,"v1");
            collisionMap.put(k2,"v2");

        assertEquals("v1", collisionMap.get(k1));
        assertEquals("v2", collisionMap.get(k2));
        }

    @Test
    @DisplayName("Удаление существующего и несуществующего элемента")
    void testRemove() {
        map.put(1, "1");
        map.put(2, "2");

        String removedValue = map.remove(1);
        assertEquals("1", removedValue);
        assertEquals(1, map.size());
        assertNull(map.get(1));

        assertNull(map.remove(3));
        assertEquals(1, map.size());
    }

    }

