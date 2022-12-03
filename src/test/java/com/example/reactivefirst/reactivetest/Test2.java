package com.example.reactivefirst.reactivetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class Test2 {
    @Test
    @DisplayName("assertj같은 확장을 통해서 함께 검증 가능하다.")
    void assertThatTest() {
        TestClazz clazz1 = new TestClazz(1, "2");
        TestClazz clazz2 = new TestClazz(3, "4");

        StepVerifier.create(Flux.just(clazz1, clazz2))
            .expectSubscription()
            .assertNext(clazz -> assertTestClazz(clazz, 1, "2"))
            .assertNext(clazz -> assertTestClazz(clazz, 3, "4"))
            .verifyComplete();
    }

    private static void assertTestClazz(TestClazz clazz, int num, String s) {
        assertThat(clazz.num1).isEqualTo(num);
        assertThat(clazz.s).isEqualTo(s);
    }

    @Test
    @DisplayName("StepVerifier에도 비슷한 기능이 존재한다.")
    void similarFunctionsInStepVerifier() {
        TestClazz clazz1 = new TestClazz(1, "2");
        TestClazz clazz2 = new TestClazz(3, "4");

        StepVerifier.create(Flux.just(clazz1, clazz2))
            .expectSubscription()
            .expectNextMatches(testClazz -> testClazz.equals(new TestClazz(1,"2")))
            .expectNextMatches(testClazz -> testClazz.equals(new TestClazz(3,"4")))
            .verifyComplete();
    }

    public static final class TestClazz {
        private final int num1;
        private final String s;

        public TestClazz(int num1, String s) {
            this.num1 = num1;
            this.s = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TestClazz testClazz = (TestClazz) o;
            return num1 == testClazz.num1 && Objects.equals(s, testClazz.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(num1, s);
        }
    }
}
