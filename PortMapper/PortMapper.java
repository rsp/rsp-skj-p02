import java.io.*;
import java.net.*;

public class PortMapper {

  public void listen(int port) {
    ServerSocket server = null;
    Socket client = null;
    try {
      server = new ServerSocket(port);
    }
    catch (IOException e) {
      System.out.println("PortMapper cannot listen on port " + port);
      System.exit(-1);
    }
    System.out.println("PortMapper is listening on port " + server.getLocalPort());
    while(true) {
      try {
        client = server.accept();
      }
      catch (IOException e) {
        System.out.println("Accept failed");
        System.exit(-1);
      }
      (new PortMapperThread(client)).start();
    }

  }

  public static void main(String[] args) {
    PortMapper pm = new PortMapper();
    pm.listen(args.length > 0 ? Integer.parseInt(args[0]) : 12345);
  }

}
