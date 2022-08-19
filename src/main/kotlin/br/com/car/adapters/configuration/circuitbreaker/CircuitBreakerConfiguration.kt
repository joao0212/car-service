package br.com.car.adapters.configuration.circuitbreaker

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CircuitBreakerConfiguration {

    fun getConfiguration() =
        CircuitBreakerConfig
            .custom()
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(10)
            .slowCallRateThreshold(70.0f)
            .slowCallDurationThreshold(Duration.ofSeconds(2))
            .waitDurationInOpenState(Duration.ofSeconds(5000))
            .permittedNumberOfCallsInHalfOpenState(10)
            .writableStackTraceEnabled(false)
            .build()

    fun getCircuitBreaker() =
        CircuitBreakerRegistry.of(getConfiguration())
            .circuitBreaker("CIRCUIT-BREAKER")
}
