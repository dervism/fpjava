package monads.types;

import java.util.function.Function;

public record Measurements<A>(
        A totalSize,
        int numBedrooms,
        A masterBedroomSize,
        A livingRoomSize) implements Functor<A> {

    @Override
    public <B> Functor<B> fmap(Function<? super A, ? extends B> mapper) {
        return new Measurements<>(
                mapper.apply(totalSize),
                numBedrooms,
                mapper.apply(masterBedroomSize),
                mapper.apply(livingRoomSize)
        );
    }
}
