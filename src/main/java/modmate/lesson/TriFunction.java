package modmate.lesson;

@FunctionalInterface
interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}
