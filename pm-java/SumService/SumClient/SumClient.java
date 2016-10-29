import java.io.*;
import java.net.*;

class SumClient {

  public static void main(String[] args) {

    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    int a = args.length > 0 ? Integer.parseInt(args[0]) : 0;
    int b = args.length > 1 ? Integer.parseInt(args[1]) : 0;
    String address = args.length > 2 ? args[2] : "localhost";
    int port = args.length > 3 ? Integer.parseInt(args[3]) : 12345;
    String remote = "O " + address + ":" + port;
    System.out.println(remote + " : connecting");
    try {
      socket = new Socket(address, port);
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String req = a + " " + b;
      System.out.println(remote + " < " + req);
      out.println(req);
      String res = in.readLine();
      System.out.println(remote + " > " + res);
    } catch (UnknownHostException e) {
      System.out.println(remote + " ! unknown host");
    } catch (IOException e) {
      System.out.println(remote + " ! i/o exception");
    }

  }

}
