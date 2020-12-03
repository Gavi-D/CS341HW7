public class Timer {
	private long elapsedTime;
	private long startTime;
	private boolean timing;

	public Timer() {
	}
	public long getElapsedTime() {
		if (timing) {
			long endTime = System.currentTimeMillis();
			return elapsedTime + endTime - startTime;
		} else {
			return elapsedTime;
		}
	}

	public void start() {
		if (timing) {
			return;
		}
		timing = true;
		startTime = System.currentTimeMillis();
	}

	public void stop() {
		if (!timing) {
			return;
		}
		timing = false;
		long endTime = System.currentTimeMillis();
		elapsedTime = elapsedTime + endTime - startTime;
	}

	public void reset() {
		elapsedTime = 0;
		timing = false;
	}

}