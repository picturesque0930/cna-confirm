package ohcna;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name = "Confirm_table")
public class Confirm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String status;
    private String confirmDtm;
    // 추가
    private Long bookingId;

    @PostPersist
    public void onPostPersist() {

        // 이벤트 인스턴스 생성
        ConfirmRequested confirmRequested = new ConfirmRequested();
        // status setting 보류 board 에서 처리하자.
        // confirmRequested.setStatus("BOOKED");

        // 속성값 할당
        BeanUtils.copyProperties(this, confirmRequested);
        confirmRequested.publishAfterCommit();

        // ConfirmRequested confirmRequested = new ConfirmRequested();
        // BeanUtils.copyProperties(this, confirmRequested);
        // confirmRequested.publishAfterCommit();

    }



    @PostUpdate
    public void onPostUpdate(){

        // 이벤트 인스턴스 생성
        // BookingChanged bookingChanged = new BookingChanged();

        // Confirmed
        if(this.getStatus().equals("CONFIRMED"))
        {
            ConfirmCompleted confirmCompleted = new ConfirmCompleted();
            BeanUtils.copyProperties(this, confirmCompleted);
             // 속성값 할당
            confirmCompleted.publishAfterCommit();
        }
        
        // Denied
        else if(this.getStatus().equals("DENIED"))
        {
            // 이벤트 인스턴스 생성
            ConfirmDenied confirmDenied = new ConfirmDenied();

            // 속성값 할당
            BeanUtils.copyProperties(this, confirmDenied);
            confirmDenied.publishAfterCommit();

            ohcna.external.Booking booking = new ohcna.external.Booking();
            // mappings goes here
            ConfirmApplication.applicationContext.getBean(ohcna.external.BookingService.class)
                .bookingCancel(booking);
        }

       

        // ConfirmCompleted confirmCompleted = new ConfirmCompleted();
        // BeanUtils.copyProperties(this, confirmCompleted);
        // confirmCompleted.publishAfterCommit();

        
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommende     
 
    }


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
