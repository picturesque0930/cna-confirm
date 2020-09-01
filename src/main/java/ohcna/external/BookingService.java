
package ohcna.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

// feign client 로 booking method 호출
// URL 은 application.yml 정의함(api.url.booking)
//@FeignClient(name="booking", url="http://booking:8080")
@FeignClient(name="booking", url="${api.url.booking}")
public interface BookingService {

    // Booking Cancel 을 위한 삭제 mapping
    @DeleteMapping(value = "/bookings/{id}")
    public void bookingCancel(@PathVariable long id);
    
    // @RequestMapping(method= RequestMethod.POST, path="/bookings")
    // public void bookingCancel(@RequestBody Booking booking);

}