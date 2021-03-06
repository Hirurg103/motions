package types;

public class Range<T extends Number> {
	private T from;
	private T to;
	
	public Range(T from, T to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean contains(T value) {
		return from.doubleValue() <= value.doubleValue() && value.doubleValue() <= to.doubleValue();
	}
	
	public boolean onLeftSizeOf(T value) {
		return to.doubleValue() < value.doubleValue();
	}
	
	public boolean onRightSizeOf(T value) {
		return value.doubleValue() < from.doubleValue();
	}
	
	public T from() { return this.from; }
	
	public T to() { return this.to; }
}
