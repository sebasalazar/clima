package cl.sebastian.utem.climate.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorVO implements Serializable {

    private static final long serialVersionUID = 6314335557508817920L;

    private boolean ok = false;
    private String message = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date date = null;

    public ErrorVO() {
    }

    public ErrorVO(String message) {
        this.ok = false;
        this.message = StringUtils.trimToEmpty(message);
        this.date = new Date();
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
