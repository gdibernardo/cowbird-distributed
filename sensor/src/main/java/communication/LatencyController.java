package communication;

import java.util.concurrent.locks.ReentrantLock;

public class LatencyController {

    private static LatencyController latencyControllerInstance = new LatencyController();

    private long outTimestamp;
    private int outId;

    private long inTimestamp;
    private int inId;

    private ReentrantLock lock = new ReentrantLock();

    public static LatencyController sharedInstance() {
        return latencyControllerInstance;
    }

    public void setIncomingId(int inId) {
        lock.lock();
        try {
            this.inId = inId;
        } finally {
            lock.unlock();
        }

    }

    public int getIncomingId() {
        return inId;
    }

    public int getOutgoingId() {
        return outId;
    }

    public void setOutgoingId(int outId) {
        lock.lock();
        try {
            this.outId = outId;
        } finally {
            lock.unlock();
        }
    }

    public void setIncomingTimestamp(long inTimestamp) {
        lock.lock();
        try {
            this.inTimestamp = inTimestamp;
        } finally {
            lock.unlock();
        }
    }

    public void setOutgoingTimestamp(long outTimestamp) {
        lock.lock();
        try {
            this.outTimestamp = outTimestamp;
        } finally {
            lock.unlock();
        }
    }


    public long getLatency() {
        lock.lock();
        try {
            if(inId == outId) {
                return (inTimestamp - outTimestamp)/2;
            } else {
                throw new RuntimeException("Something wrong when registering id");
            }
        } finally {
            lock.unlock();
        }

    }
}
