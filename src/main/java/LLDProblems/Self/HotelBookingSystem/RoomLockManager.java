package LLDProblems.Self.HotelBookingSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class RoomLockManager {

    private static RoomLockManager instance = null;

    // roomTypeId → (userId, qty, expiryTime)
    Map<Integer, LockDetail> locks = new HashMap<>();

    public static RoomLockManager getInstance() {
        if (instance == null) instance = new RoomLockManager();
        return instance;
    }

    static class LockDetail {
        int userId;
        int qty;
        LocalDateTime expiry;
    }

    synchronized boolean lockRoomType(int roomTypeId, LocalDate checkIn, LocalDate checkOut,
                                      int qty, int userId) {
        if (locks.containsKey(roomTypeId)) return false;

        LockDetail detail = new LockDetail();
        detail.userId = userId;
        detail.qty = qty;
        detail.expiry = LocalDateTime.now().plusMinutes(15);

        locks.put(roomTypeId, detail);
        return true;
    }

    synchronized void unlockRoomType(int roomTypeId) {
        locks.remove(roomTypeId);
    }

    boolean isLocked(int roomTypeId) {
        return locks.containsKey(roomTypeId);
    }
}

