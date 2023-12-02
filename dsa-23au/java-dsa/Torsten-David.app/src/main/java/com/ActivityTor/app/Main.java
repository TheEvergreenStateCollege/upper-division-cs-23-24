package com.ActivityTor.app;

public class Main {
    public static void main( String[] args )
    {
        AppleWatchDataApp app = new AppleWatchDataApp();
        app.loadData();
        Runtime.Run(app);
    }
}
