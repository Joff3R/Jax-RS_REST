package resources.beans;

import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageFilterBean {

    private @QueryParam("year") int year;
}
