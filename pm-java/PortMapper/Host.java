class Host {

  String a;
  int p;

  Host(String a, int p) {
    this.a = a;
    this.p = p;
  }

  String address() {
    return a;
  }

  int port() {
    return p;
  }

  String string() {
    return a + " " + p;
  }

  String host() {
    return a + ":" + p;
  }

}
