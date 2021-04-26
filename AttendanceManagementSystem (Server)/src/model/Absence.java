package model;

public class Absence
{
  private String reason;
  private boolean wasAbsent;

  public Absence(boolean wasAbsent, String reason) {
    this.wasAbsent = wasAbsent;
    this.reason = reason;
  }

  public Absence(boolean wasAbsent){
    this.wasAbsent = wasAbsent;
    this.reason = null;
  }

  public Absence copy(){
    return new Absence(this.wasAbsent,this.reason);
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public boolean isWasAbsent() {
    return wasAbsent;
  }

  public void setWasAbsent(boolean wasAbsent) {
    this.wasAbsent = wasAbsent;
  }


}
