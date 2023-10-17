package pages.interfaces;

/*
Each Screen which has unexpected popup can implement this interface according to its requirement
 */
public interface PopupHandling {
    long waitTimeForPopup = 1;
    void handleUnexpectedPopup();
}
