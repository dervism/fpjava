package examples.example_6_monads.functors;

import monads.types.Functor;

import java.util.function.Function;

public record Measurements<A>(
        int numBedrooms,
        A totalSize,
        A masterBedroomSize,
        A livingRoomSize) implements Functor<A, Measurements<?>> {

    @Override
    public <B> Measurements<B> fmap(Function<? super A, ? extends B> mapper) {
        return new Measurements<>(
                numBedrooms,
                mapper.apply(totalSize),
                mapper.apply(masterBedroomSize),
                mapper.apply(livingRoomSize)
        );
    }
}
