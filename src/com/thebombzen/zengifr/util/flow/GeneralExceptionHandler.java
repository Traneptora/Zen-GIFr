package com.thebombzen.zengifr.util.flow;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class GeneralExceptionHandler implements Consumer<Throwable> {

	private Map<Class<? extends Throwable>, Consumer<?>> table = new HashMap<>();
	
	public static GeneralExceptionHandler create() {
		return new GeneralExceptionHandler();
	}
	
	public static <T extends Throwable> GeneralExceptionHandler create(Class<T> clazz, Consumer<? super T> handler) {
		return new GeneralExceptionHandler().registerHandler(clazz, handler);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void accept(Throwable t) {
		Class<?> clazz = t.getClass();
		while (!Object.class.equals(clazz) && !table.containsKey(clazz)) {
			clazz = clazz.getSuperclass();
		}
		if (table.containsKey(clazz)) {
			((Consumer<Throwable>)table.get(clazz)).accept(t);
		} else {
			ConcurrenceManager.sneakyThrow(t);
		}
	}
	
	public <T extends Throwable> GeneralExceptionHandler registerHandler(Class<T> clazz, Consumer<? super T> handler) {
		table.put(clazz, handler);
		return this;
	}
	
}
