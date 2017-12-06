package com.thebombzen.zengifr.util.flow;

import java.util.function.Consumer;

public class NullExceptionHandler implements Consumer<Throwable> {

	public static final NullExceptionHandler INSTANCE = new NullExceptionHandler();
	
	@Override
	public void accept(Throwable t) {
		
	}

}
