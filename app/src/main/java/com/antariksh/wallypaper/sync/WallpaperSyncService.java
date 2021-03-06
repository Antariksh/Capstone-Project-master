package com.antariksh.wallypaper.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WallpaperSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static WallypaperSyncAdapter sSunshineSyncAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sSunshineSyncAdapter == null) {
                sSunshineSyncAdapter = new WallypaperSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSunshineSyncAdapter.getSyncAdapterBinder();
    }
}