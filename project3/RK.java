package project3;

class RollingHash {

  int a = 11;
  public String string;
  public int hash;
  int patternLength;

  public RollingHash(String string, int hash) {
    this.string = string;
    this.hash = hash;
    this.patternLength = string.length();
  }

  public RollingHash(String string) {
    this.string = string;
    this.patternLength = string.length();

    int pow = patternLength - 1;

    int hash = 0;
    for (int i = 0; i < string.toCharArray().length; i++) {
      hash += (int) string.charAt(i) * (int) Math.pow(a, pow--);
    }
    this.hash = hash;
  }

  RollingHash accept(char c) {
    String newString = string.substring(1) + c;

    int hash = this.hash * a
        - (int) string.charAt(0) * (int) Math.pow(a, patternLength)
        + (int) c;

    return new RollingHash(newString, hash);
  }
}

public class RK {

  public int match(String text, String pattern) {
    // Rabin-karp algorithm
    int patternLength = pattern.length();

    RollingHash patternHash = new RollingHash(pattern);
    RollingHash textHash = new RollingHash(text.substring(0, patternLength));
    for (int i = patternLength; i < text.length(); i++) {
      if (patternHash.hash == textHash.hash && pattern.equals(text.substring(i - patternLength, i))) {
        return i - patternLength;
      }
      textHash = textHash.accept(text.charAt(i));
    }
    return -1;
  }
}