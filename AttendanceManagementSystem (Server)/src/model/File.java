package model;

public class File
{
  private String fileName, extension;
  public File(String fileName, String extension){
    this.fileName = fileName;
    this.extension = extension;
  }
  public boolean isPDF(){
    return extension.equals("pdf")||extension.equals("PDF");
  }
  @Override
  public String toString(){
    return String.format("%s.%s",fileName,extension);
  }
}
