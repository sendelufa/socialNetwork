package ru.skillbox.socialnetwork.api.response;

public class FileUploadResponseApi extends AbstractResponse{

  private String id;
  private int ownerId;
  private String fileName;
  private String relativeFilePath;
  private String rawFileURL;
  private String fileFormat;
  private int bytes;
  private fileTypes fileType;
  private int createdAt;

  public enum fileTypes {IMAGE}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getRelativeFilePath() {
    return relativeFilePath;
  }

  public void setRelativeFilePath(String relativeFilePath) {
    this.relativeFilePath = relativeFilePath;
  }

  public String getRawFileURL() {
    return rawFileURL;
  }

  public void setRawFileURL(String rawFileURL) {
    this.rawFileURL = rawFileURL;
  }

  public String getFileFormat() {
    return fileFormat;
  }

  public void setFileFormat(String fileFormat) {
    this.fileFormat = fileFormat;
  }

  public int getBytes() {
    return bytes;
  }

  public void setBytes(int bytes) {
    this.bytes = bytes;
  }

  public fileTypes getFileType() {
    return fileType;
  }

  public void setFileType(fileTypes fileType) {
    this.fileType = fileType;
  }

  public int getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(int createdAt) {
    this.createdAt = createdAt;
  }
}
