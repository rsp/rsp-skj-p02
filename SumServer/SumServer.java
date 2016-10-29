// SumServer by Rafał Pocztarski - https://pocztarski.com/

import java.io.*;
import java.net.*;

public class SumServer {
  public void listen(int port) {
    ServerSocket server = null;
    Socket client = null;
    try {
      server = new ServerSocket(port);
    }
    catch (IOException e) {
      System.out.println("SumServer cannot listen on port " + port);
      System.exit(-1);
    }
    System.out.println("SumServer is listening on port " + server.getLocalPort());

    while(true) {
      try {
        client = server.accept();
      }
      catch (IOException e) {
        System.out.println("Accept failed");
        System.exit(-1);
      }
      (new SumServerThread(client)).start();
    }

  }

  public static void main(String[] args) {
    new SumServer().listen(args.length > 0 ? Integer.parseInt(args[0]) : 12345);
  }
}
