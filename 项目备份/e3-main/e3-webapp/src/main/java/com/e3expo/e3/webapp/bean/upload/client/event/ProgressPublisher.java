package com.e3expo.e3.webapp.bean.upload.client.event;

public class ProgressPublisher {
    public static void publishProgress(ProgressListener listener, ProgressEventType type, long bytes) {
        listener.progressChanged(new ProgressEvent(type, bytes));
    }

    public static void publishProgress(ProgressListener listener, ProgressEventType type) {
        listener.progressChanged(new ProgressEvent(type));
    }
}
