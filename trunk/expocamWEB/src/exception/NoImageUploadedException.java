package exception;

public class NoImageUploadedException extends Exception {

	private static final long serialVersionUID = -7035219481243497292L;

	public NoImageUploadedException() {
		super();
	}
	
	public NoImageUploadedException(Throwable e) {
		super(e);
	}

}
