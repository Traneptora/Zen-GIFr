package com.thebombzen.zengifr.util.io;

import static com.thebombzen.zengifr.ZenGIFr.log;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import com.thebombzen.zengifr.util.flow.ConcurrenceManager;

/**
 * This stream behaves like a FilterOutputStream for several output stream. It
 * forks the output into several output streams.
 */

public class TeeOutputStream extends OutputStream {

	protected List<? extends OutputStream> outs;

	public TeeOutputStream(OutputStream... outs) {
		this.outs = Arrays.asList(outs);
	}

	public TeeOutputStream(Collection<? extends OutputStream> outs) {
		this.outs = new ArrayList<>(outs);
	}

	@Override
	public void write(int b) throws IOException {
		for (OutputStream out : outs) {
			out.write(b);
		}
	}

	@Override
	public void write(byte[] buf, int off, int len) throws IOException {
		for (OutputStream out : outs) {
			out.write(buf, off, len);
		}
	}

	@Override
	public void write(byte[] buf) throws IOException {
		for (OutputStream out : outs) {
			out.write(buf);
		}
	}

	@Override
	public void flush() throws IOException {
		for (OutputStream out : outs) {
			out.flush();
		}
	}

	@Override
	public void close() throws IOException {
		List<IOException> ioes = new ArrayList<>();
		List<Throwable> exs = new ArrayList<>();
		for (OutputStream out : outs) {
			try {
				out.close();
			} catch (IOException ioe) {
				ioes.add(ioe);
			} catch (Throwable ex) {
				exs.add(ex);
			}
		}
		Throwable toThrow = null;
		for (IOException ioe : ioes) {
			log(ioe);
			if (toThrow == null)
				toThrow = ioe;
		}
		for (Throwable ex : exs) {
			log(ex);
			if (toThrow == null)
				toThrow = ex;
		}
		if (toThrow != null) {
			ConcurrenceManager.sneakyThrow(toThrow);
		}
	}
}
