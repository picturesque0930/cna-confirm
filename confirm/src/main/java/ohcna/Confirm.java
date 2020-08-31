package ohcna;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Confirm_table")
public class Confirm {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userId;
    private String status;
    private String confirmDtm;

    @PostPersist
    public void onPostPersist(){
        ConfirmRequested confirmRequested = new ConfirmRequested();
        BeanUtils.copyProperties(this, confirmRequested);
        confirmRequested.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        ConfirmCompleted confirmCompleted = new ConfirmCompleted();
        BeanUtils.copyProperties(this, confirmCompleted);
        confirmCompleted.publishAfterCommit();


        ConfirmDenied confirmDenied = new ConfirmDenied();
        BeanUtils.copyProperties(this, confirmDenied);
        confirmDenied.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        ohcna.external.Booking booking = new ohcna.external.Booking();
        // mappings goes here
        ConfirmApplication.applicationContext.getBean(ohcna.external.BookingService.class)
            .bookingCancel(booking);


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




}
