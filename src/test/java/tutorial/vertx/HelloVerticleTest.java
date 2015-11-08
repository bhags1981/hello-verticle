/**
 * Created by bhags on 15/11/08.
 */

package tutorial.vertx;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class HelloVerticleTest {

    private Vertx vertx;
    private final static Logger log = LoggerFactory.getLogger(HelloVerticleTest.class);
    @Before
    public void setUp(TestContext context) {
        log.info("SETUP");
        vertx = Vertx.vertx();
        vertx.deployVerticle(HelloVerticle.class.getName(),
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        log.info("TEAR DOWN");
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testMyApplication(TestContext context) {
        log.info("TEST MY APPLICATION");
        final Async async = context.async();

        vertx.createHttpClient().getNow(8080, "localhost", "/",
                response -> {
                    response.handler(body -> {
                        context.assertTrue(body.toString().contains("Hello"));
                        async.complete();
                    });
                });
    }
}