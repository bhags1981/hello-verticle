package tutorial.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.logging.Logger;
/**
 * Created by bhags on 15/11/07.
 */
public class HelloVerticle extends AbstractVerticle {
    private final static Logger log = LoggerFactory.getLogger(HelloVerticle.class);
    @Override
    public void start(Future<Void> fut) {
        log.info("START CREATE VERTICLE");
        vertx
                .createHttpServer()
                .requestHandler(r -> {
                    r.response().end(
                            "<h1>Hello from my first " +
                            "Vert.x 3 application</h1>");
                })
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }
}
