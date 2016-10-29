// PortMapperThread by Rafał Pocztarski - https://pocztarski.com/

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class PortMapperThread extends Thread {

  private Socket socket;
  private static PortMap map = new PortMap();

  public PortMapperThread(Socket socket) {
    super();
    this.socket = socket;
  }

  String register(String name, String address, int port) {
    map.add(name, address, port);
    return "OK";
  }

  String get(String name) {
    Host host = map.get(name);
    return host == null ? "unknown service" : host.string();
  }

  String call(String name, String[] args) {
    Host host = map.get(name);
    if (host == null) {
      return "unknown service";
    } else {
      return request(host, args);
    }
  }

  String request(Host host, String[] args) {
    String remote = "O " + host.host() + " ";
    System.out.println(remote + ":: connecting");
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    try {
      socket = new Socket(host.address(), host.port());
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String req = String.join(" ", args);
      System.out.println(remote + "<< " + req);
      out.println(req);
      String res = in.readLine();
      System.out.println(remote + ">> " + res);
      return res;
    } catch (UnknownHostException e) {
      System.out.println(remote + "!! unknown host");
      return "unknown host";
    } catch (IOException e) {
      System.out.println(remote + "!! i/o exception");
      return "i/o exception";
    }
  }

  String handler(String req) {
    String[] args = req.trim().split("\\s+");
    if (args[0].equals("REGISTER")) {
      return register(args[1], args[2], Integer.parseInt(args[3]));
    } else if (args[0].equals("GET")) {
      return get(args[1]);
    } else if (args[0].equals("CALL")) {
      return call(args[1], Arrays.copyOfRange(args, 2, args.length));
    } else {
      return args[0] + " command not supported";
    }
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
