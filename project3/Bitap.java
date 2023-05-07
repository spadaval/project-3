package project3;

public class Bitap {

  int CHAR_MAX = 256;

  public int match(String text, String pattern) {
    int m = pattern.length();
    long pattern_masks[] = new long[CHAR_MAX + 1];

    if (pattern.length() == 0)
      return 0;

    // init
    for (int i = 0; i <= CHAR_MAX; ++i)
      // all ones
      pattern_masks[i] = ~0;
    
      for (int i = 0; i < m; ++i)
      pattern_masks[pattern.charAt(i)] &= ~(1L << i);

    // 11111...1110
    long R = ~1;
    for (int i = 0; i < text.length(); ++i) {
      R |= pattern_masks[text.charAt(i)];
      R <<= 1;

      if (0 == (R & (1L << m)))
        return i - m + 1;
    }

    return -1;
  }

}
