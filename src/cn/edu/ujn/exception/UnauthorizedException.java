package cn.edu.ujn.exception;

import cn.edu.ujn.exception.BaseException;

public class UnauthorizedException
    extends Exception
    implements BaseException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode = 0;

    /**
     * ”√¥ÌŒÛ¬Î≥ı ºªØ
     * @param errorCode ¥ÌŒÛ¬Î
     */
    public UnauthorizedException(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @see com.cnjsp.cnjbb.ForumException#getErrorCode()
     */
    public int getErrorCode() {
        return this.errorCode;
    }
}