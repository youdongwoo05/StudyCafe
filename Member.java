package management;

import java.time.LocalDateTime;


public class Member {
	private String id;
    private String password;
    private LocalDateTime startTime;
    private int fee;

    public Member(String id, String password, LocalDateTime startTime, int fee) {
        this.id = id;
        this.password = password;
        this.startTime = startTime;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getFee() {
        return fee;
    }
}
