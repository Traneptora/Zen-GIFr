package com.thebombzen.zengifr.util.flow;

public interface Task extends Runnable, Comparable<Task> {

	public int getPriority();

	@Override
	public default int compareTo(Task o) {
		return Integer.compare(getPriority(), o.getPriority());
	}

}
