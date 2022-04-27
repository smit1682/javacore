package vertex.eventbus_example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database extends AbstractVerticle {

  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/NMS_Dummy","root","smit1682");

  public database() throws SQLException {
  }


  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    vertx.eventBus().consumer("credantials",message->{
      JsonObject jsonObject = (JsonObject) message.body();
        String query = "INSERT INTO credentials VALUES ("+ jsonObject.getString("port")+" ,"+ jsonObject.getString("host")+" ,"+ jsonObject.getString("username") + " ," + jsonObject.getString("password") + " ,"+jsonObject.getString("matricType")+" )";

    });

    vertx.eventBus().consumer("discovery",message->{

    });
  }
}
