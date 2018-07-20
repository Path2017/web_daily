package com.e3expo.e3.service.interfaces;

import java.io.IOException;

import com.e3expo.e3.model.form.FileFormat;

public interface IUtil {
	 String fileUpload(FileFormat fileFormat) throws IllegalStateException, IOException;
}
