# ratelimiting
this project has ApiGateWay with resilience4j @CircuitBreaker,@Retry,@RateLimiter,@Bulkhead


Eureka UI – see registered services
👉 http://localhost:8761

Gateway endpoints (API)
👉 http://localhost:8080/orders/hello

RedisInsight UI – see Redis keys & rate-limiter counters
👉 http://localhost:8001
Add connection to redis:6379 or localhost:6379.

Prometheus UI – query metrics
👉 http://localhost:9090
Example query: http_server_requests_seconds_count

Grafana UI – nice dashboards
👉 http://localhost:3000
Login: admin / admin (from env config).
