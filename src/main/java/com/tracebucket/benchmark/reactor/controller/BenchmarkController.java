package com.tracebucket.benchmark.reactor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.Reactor;
import reactor.event.Event;

/**
 * Created by ffl on 19-01-2015.
 */
@Controller
public class BenchmarkController {
    private static final Logger log = LoggerFactory.getLogger(BenchmarkController.class);

    @Autowired
    private Reactor eventBus;

    @RequestMapping(value = "/benchmark", method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        for(int i = 0; i < 50; i++){
            eventBus.notify("benchmark", Event.wrap(new Integer(i)));
            log.info("Published event " + i);
        }

        return new ResponseEntity<String>("Started", HttpStatus.OK);
    }
}
