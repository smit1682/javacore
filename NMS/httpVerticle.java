package vertex.eventbus_example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class httpVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Router router = Router.router(vertx);

    router.route().handler(BodyHandler.create());

    router.post("/discovery").method(HttpMethod.POST).handler(context->{

      JsonObject jsonObject = context.getBodyAsJson();

      vertx.eventBus().request("discovery",jsonObject);

      context.response().end("done");

    });

    vertx.createHttpServer().requestHandler(router).listen(8888, http -> {

      if (http.succeeded()) {

        startPromise.complete();

        System.out.println("HTTP server started on port 8888");

      } else {

        startPromise.fail(http.cause());

      }
    });




  }
}
