package net.elemix.proportionaljpanel;

public class PanelCanNotBeSizedException extends Exception {
    public PanelCanNotBeSizedException() {
    }

    public PanelCanNotBeSizedException(String message) {
        super(message);
    }

    public PanelCanNotBeSizedException(Throwable cause) {
        super(cause);
    }

    public PanelCanNotBeSizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
