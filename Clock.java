package org.firstinspires.ftc.teamcode.shared;

public interface Clock {
    long getCurrentTime();
    void reset();
    void setTime(long time);
}
