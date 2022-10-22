public enum Status {
  OPEN, RESOLVEDANDCLOSED, UNRESOLVEDANDCLOSED, ARCHIVED;

  @Override
  public String toString()
  {
     return String.format("%C%s",
                          name().toString().charAt(0),
                          (name().toString().toLowerCase().substring(1)));
  }
  

}
