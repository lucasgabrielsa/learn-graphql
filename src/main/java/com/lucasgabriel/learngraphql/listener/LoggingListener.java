package com.lucasgabriel.learngraphql.listener;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@AllArgsConstructor
public class LoggingListener implements GraphQLServletListener {

    private final Clock clock;

    @Override
    public GraphQLServletListener.RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        var startTime = Instant.now(clock);
        log.info("Received graphql request");
        return new RequestCallback() {
            @Override
            public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
                // no-op
            }

            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
                //no-op
            }

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                log.info("Completed Request. Time Taken: {}", Duration.between(startTime, Instant.now(clock)));
            }
        };
    }
}
