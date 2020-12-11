package MinBinHeap_A3;

public class EntryPair implements EntryPair_Interface {

	public String value;
	public int priority;
	
	public EntryPair(String aValue, int aPriority) {
		value = aValue;
		priority = aPriority;
	}
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getPriority() {
		return priority;
	}

}
