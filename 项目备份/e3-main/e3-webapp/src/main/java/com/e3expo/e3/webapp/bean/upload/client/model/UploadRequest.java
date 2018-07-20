package com.e3expo.e3.webapp.bean.upload.client.model;

import com.e3expo.e3.webapp.bean.upload.client.event.ProgressListener;

import java.io.InputStream;
import java.io.OutputStream;

public class UploadRequest {
    private InputStream in;
    private OutputStream out;
    private ProgressListener listener;

    public UploadRequest(InputStream in, OutputStream fileOutputStream) {
        assert in != null;
        assert out != null;
        this.in = in;
        this.out = fileOutputStream;
    }

    public UploadRequest withProgressListener(ProgressListener progressListener) {
        this.listener = progressListener;
        return this;
    }

    public InputStream getIn() {
        return in;
    }

    public OutputStream getOut() {
        return out;
    }

    public ProgressListener getListener() {
        return listener;
    }
}
