// SumServerThread by Rafał Pocztarski - https://pocztarski.com/

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class SumServerThread extends Thread {

  private Socket socket;

  public SumServerThread(Socket socket) {
    super();
    this.socket = socket;
  }

  String handler(String req) {
    String[] args = req.trim().split("\\s+");
    int a = args.length > 0 ? Integer.parseInt(args[0]) : 0;
    int b = args.length > 1 ? Integer.parseInt(args[1]) : 0;
    return "" + (a + b);
  }

  public void run() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      String address = socket.getInetAddress().getHostAddress();
      int port = socket.getPort();
      String remote = "I " + address + ":" + port;
      System.out.println(remote + " : connected");
      String req = in.readLine();
      System.out.println(remote + " > " + req);
      String res = handler(req);
      System.out.println(remote + " < " + res);
      out.println(res);
      socket.close();
    } catch (IOException e) {
      System.out.println("IOException!");
    }
  }

}
