package com.cats.exception;

/**
 * Thrown to indicate that a file has inappropriate format.
 * (IllegalArgumentException)
 */
public class IllegalFileFormat extends Exception{
    private static final long serialVersionUID = -1613927519576219886L;

    /**
     * Constructs an <code>IllegalFileFormat</code> with no
     * detail message.
     */
    public IllegalFileFormat() {
        super();
    }

    /**
     * Constructs an <code>IllegalFileFormat</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public IllegalFileFormat(String s) {
        super(s);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * <p>Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link Throwable#getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method).  (A {@code null} value
     *         is permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public IllegalFileFormat(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code (cause==null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public IllegalFileFormat(Throwable cause) {
        super(cause);
    }

}