package com.thebombzen.zengifr.util.flow;

import java.util.function.Consumer;
import com.thebombzen.zengifr.ZenGIFr;

public class LogExceptionHandler implements Consumer<Throwable> {
	
	public static final LogExceptionHandler INSTANCE = new LogExceptionHandler();
	
	@Override
	public void accept(Throwable t) {
		ZenGIFr.log(t);
	}

}
