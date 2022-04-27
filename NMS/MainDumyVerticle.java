package vertex.eventbus_example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MainDumyVerticle extends AbstractVerticle {

  public static void main(String[] args) {

    Vertx vertex = Vertx.vertx();

    vertex.deployVerticle(new httpVerticle());

    for(int i=0;i<4;i++){
    vertex.deployVerticle(new discovery(),new DeploymentOptions().setWorker(true));}
  }

}
