package domain;

import java.util.List;

public class ExtendedIteratorEvent implements ExtendedIterator<Event>{
	List<Event> a;
	int pos;
	public ExtendedIteratorEvent(List<Event> list) {
		a = list;
		pos = 0;
	}
	
	@Override
	public boolean hasNext() {
		return pos < a.size();
	}

	@Override
	public Event next() {
		Event ev = a.get(pos);
		pos = pos + 1;
		return ev;
	}

	@Override
	public Event previous() {
		Event ev = a.get(pos);
		pos = pos - 1;
		return ev;
	}

	@Override
	public boolean hasPrevious() {
		return !(pos < 0 || a.get(pos) == null);
	}

	@Override
	public void goFirst() {
		pos = 0;		
	}

	@Override
	public void goLast() {
		pos = a.size() -  1;
	}

}
