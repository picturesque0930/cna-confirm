package ohcna;

public class ConfirmRequested extends AbstractEvent {

    private Long id;
    private String userId;
    private String status;
    private String confirmDtm;
    private Long bookingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getConfirmDtm() {
        return confirmDtm;
    }

    public void setConfirmDtm(String confirmDtm) {
        this.confirmDtm = confirmDtm;
    }

    // 추가
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}