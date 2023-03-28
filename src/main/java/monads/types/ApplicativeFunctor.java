package monads.types;

import java.util.function.Function;

public interface ApplicativeFunctor<A> extends Functor<A> {

    <B> ApplicativeFunctor<B> apply(Function<? super A, ? extends B> mapper);

}
