package com.thebombzen.zengifr.util.flow;

import java.util.function.Consumer;

public class RethrowExceptionHandler implements Consumer<Throwable> {
	
	public static final RethrowExceptionHandler INSTANCE = new RethrowExceptionHandler();
	
	@Override
	public void accept(Throwable t) {
		ConcurrenceManager.sneakyThrow(t);
	}
}
