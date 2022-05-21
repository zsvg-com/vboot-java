package vboot.bi.etl.log;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class BiEtlLog {

    @Id
    private Integer ID_BATCH;

    private String CHANNEL_ID;

    private String TRANSNAME;

    @Column(length = 15)
    private String STATUS;

    private Long LINES_READ;

    private Long LINES_WRITTEN;

    private Long LINES_UPDATED;

    private Long LINES_INPUT;

    private Long LINES_OUTPUT;

    private Long LINES_REJECTED;

    private Long ERRORS;

    private Date STARTDATE;

    private Date ENDDATE;

    private Date LOGDATE;

    private Date DEPDATE;

    private Date REPLAYDATE;

    @Column(length = 5000)
    private String LOG_FIELD;
}
