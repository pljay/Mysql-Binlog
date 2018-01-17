package or.model;

public class BinlogInfo {
	private String binlogName;
    private Long fileSize;
	public String getBinlogName() {
		return binlogName;
	}
	public void setBinlogName(String binlogName) {
		this.binlogName = binlogName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public BinlogInfo(String binlogName, Long fileSize) {
		super();
		this.binlogName = binlogName;
		this.fileSize = fileSize;
	}
}
