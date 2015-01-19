package com.tracebucket.benchmark.reactor.service;

import reactor.event.Event;

/**
 * Created by ffl on 19-01-2015.
 */
public interface BenchmarkService {

    public void benchmark(Event<Integer> seedEvent);
}
