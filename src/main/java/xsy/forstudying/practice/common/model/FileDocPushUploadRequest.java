package xsy.forstudying.practice.common.model;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-14 14:48
 **/

public class FileDocPushUploadRequest {

    private String token;


    private String recordId;


    private byte[] fileData;


    private String fileName;


    private Integer mode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "FileDocPushUploadRequest{" +
                "token='" + token + '\'' +
                ", recordId='" + recordId + '\'' +
                ", fileData=" + fileData +
                ", fileName='" + fileName + '\'' +
                ", mode=" + mode +
                '}';
    }
}
