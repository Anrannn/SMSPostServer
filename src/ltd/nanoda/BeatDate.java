package ltd.nanoda;

public class BeatDate {
    static long lastLoginTime =0;

    static public long getLastLoginTime() {
        return lastLoginTime;
    }

    static public void setLastLoginTime(long lastLoginTime) {
        BeatDate.lastLoginTime = lastLoginTime;
    }
}
