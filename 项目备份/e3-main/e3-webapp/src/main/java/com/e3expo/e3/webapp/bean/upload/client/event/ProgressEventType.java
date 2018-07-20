package com.e3expo.e3.webapp.bean.upload.client.event;

public enum ProgressEventType {

    /**
     * Used to indicate the number of bytes to be sent to OSS.
     */
    REQUEST_BYTE_TRANSFER_EVENT,

    /**
     * Transfer events.
     */
    TRANSFER_STARTED_EVENT,
    TRANSFER_COMPLETED_EVENT,
    TRANSFER_FAILED_EVENT,

}
