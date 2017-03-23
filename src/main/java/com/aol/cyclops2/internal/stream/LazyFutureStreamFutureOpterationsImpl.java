package com.aol.cyclops2.internal.stream;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

import com.aol.cyclops2.internal.react.stream.traits.future.operators.LazyFutureStreamUtils;
import cyclops.stream.FutureStream;
import com.aol.cyclops2.types.stream.reactive.ReactiveStreamsTerminalFutureOperations;
import com.aol.cyclops2.types.stream.reactive.ReactiveTask;

public class LazyFutureStreamFutureOpterationsImpl<T> implements ReactiveStreamsTerminalFutureOperations<T> {
    private final Executor exec;
    private final FutureStream<T> stream;

    public LazyFutureStreamFutureOpterationsImpl(final Executor exec, final FutureStream<T> stream) {
        this.exec = exec;
        this.stream = stream;

    }

   

    @Override
    public <X extends Throwable> ReactiveTask forEachX(final long numberOfElements, final Consumer<? super T> consumer) {
        return new ReactiveTask(
                                exec, LazyFutureStreamUtils.forEachX(stream, numberOfElements, consumer)
                                                                .map2(r -> CompletableFuture.runAsync(r, exec)));
    }

    @Override
    public <X extends Throwable> ReactiveTask forEachXWithError(final long numberOfElements, final Consumer<? super T> consumer,
            final Consumer<? super Throwable> consumerError) {
        return new ReactiveTask(
                                exec, LazyFutureStreamUtils.forEachXWithError(stream, numberOfElements, consumer, consumerError)
                                                                .map2(r -> CompletableFuture.runAsync(r, exec)));

    }

    @Override
    public <X extends Throwable> ReactiveTask forEachXEvents(final long numberOfElements, final Consumer<? super T> consumer,
            final Consumer<? super Throwable> consumerError, final Runnable onComplete) {
        return new ReactiveTask(
                                exec, LazyFutureStreamUtils.forEachXEvents(stream, numberOfElements, consumer, consumerError, onComplete)
                                                                .map2(r -> CompletableFuture.runAsync(r, exec)));

    }

    @Override
    public <X extends Throwable> ReactiveTask forEachWithError(final Consumer<? super T> consumerElement,
            final Consumer<? super Throwable> consumerError) {

        return new ReactiveTask(
                                exec, LazyFutureStreamUtils.forEachWithError(stream, consumerElement, consumerError)
                                                                .map2(r -> CompletableFuture.runAsync(r, exec)));
    }

    @Override
    public <X extends Throwable> ReactiveTask forEachEvent(final Consumer<? super T> consumerElement, final Consumer<? super Throwable> consumerError,
            final Runnable onComplete) {
        return new ReactiveTask(
                                exec, LazyFutureStreamUtils.forEachEvent(stream, consumerElement, consumerError, onComplete)
                                                                .map2(r -> CompletableFuture.runAsync(r, exec)));
    }
}