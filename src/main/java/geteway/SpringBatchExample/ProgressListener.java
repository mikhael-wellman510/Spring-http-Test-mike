package geteway.SpringBatchExample;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class ProgressListener implements ItemWriteListener<Object> {
	private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
	private int counter = 0;
	@Override
	public void beforeWrite(Chunk<?> items) {
//		ItemWriteListener.super.beforeWrite(items);
	}

	@Override
	public void afterWrite(Chunk<?> items) {
		counter += items.size();
		long used = memoryMXBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
		System.out.println("✅ Wrote total: " + counter + " records, Heap: " + used + " MB");
	}

	@Override
	public void onWriteError(Exception exception, Chunk<?> items) {
		System.err.println("❌ Error writing: " + exception.getMessage());
	}
}
