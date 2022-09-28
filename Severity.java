public enum Severity {
  LOW, MEDIUM, HIGH;

  @Override
  public String toString()
  {
     return String.format("%C%s",
                          name().toString().charAt(0),
                          name().toString().toLowerCase().substring(1));
  }
}
