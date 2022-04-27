package vertex.eventbus_example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.io.*;
import java.util.ArrayList;

import java.util.Base64;
import java.util.List;


public class discovery extends AbstractVerticle {


  @Override
  public void start(Promise<Void> startPromise) throws Exception {


  vertx.executeBlocking(event -> {

    vertx.eventBus().consumer("discovery",message->{

      JsonObject jsonObject = (JsonObject) message.body();

      String ip = jsonObject.getString("host");

      if(fping(ip)){

        System.out.println("up ip");

        try {
          if(sshdiscovery(jsonObject))
          {
            System.out.println("discovery up");
          }
          else
            System.out.println("discovery down");

        } catch (IOException e) {
          throw new RuntimeException(e);
        }


      }
      else System.out.println("down ip");

    });


  },result->{
        //one device discovery over
  });


    startPromise.complete();
  }

  private Boolean sshdiscovery(JsonObject jsonObject) throws IOException {

    String encodedJsonStringARG1 = Base64.getEncoder().encodeToString(jsonObject.toString().getBytes());

    ProcessBuilder processBuilder = new ProcessBuilder().command("./discovery.exe",encodedJsonStringARG1);

    try {
      Process process = processBuilder.start();

      InputStreamReader inputStreamReader = new InputStreamReader(process.getErrorStream()); //read the output

      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      String output1 = bufferedReader.readLine();

      process.waitFor();

      String[] ss = output1.split(" ");

      return ss.length == 3 && ss[2].equals("success");

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  Boolean fping(String ip){
    List<String> list = new ArrayList<>();          // list bcoz in future multiple ip's can be passed

    list.add("fping");
    list.add("-q");
    list.add("-c");
    list.add("3");
    list.add("-t");
    list.add("3000");
    list.add(ip);

    ProcessBuilder processBuilder = new ProcessBuilder(list);

    try {

      Process process = processBuilder.start();

      BufferedReader bf = new BufferedReader(new InputStreamReader(process.getErrorStream()));

      String output = bf.readLine();

      String[] parts = output.split(":");

      String[] parts1 = parts[1].split(" ");

      if (parts1.length == 7)
      {

        String[] finalParts = parts1[3].split("/");

        return finalParts[0].equals(finalParts[1]);

      }
      else
      {
        return false;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return false;
  }
}
