// PortMapper/PortMap by Rafał Pocztarski - https://pocztarski.com/

import java.util.HashMap;

class PortMap {

  HashMap<String, Host> map = new HashMap<>();

  synchronized void add(String n, String a, int p) {
    map.put(n, new Host(a, p));
  }

  synchronized Host get(String n) {
    return map.get(n);
  }

}
