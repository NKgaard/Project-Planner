package dtu.student.pp.interval;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

//Using generic class D extends IntervalAble instead of Interval with D as satellite data.
public class IntervalSet<D extends IntervalAble> {
	private final NavigableSet<D> intervalTree = new TreeSet<D>();
	private Calendar first, last;
	
	public IntervalSet(Set<D> from) {
		addAll(from);
	}
	
	public Set<D> getValues() {
		return Collections.unmodifiableSet(intervalTree);
	}
	
	public Set<D> getIntersection(D start, D end) {
		//Test this method. For dates 1, 2, 3, 4, 5. If projects starts in 2 and 4: 2 3 4 should be returned.
		//If input is flipped. Just switch start and end.
		return Collections.unmodifiableSet(intervalTree.subSet(start, true, end, true));
	}
	
	public boolean add(D intervalable) {
		//The bookkeeping (Update last and first time)
		if(intervalable.getStart()!=null) {
			Calendar start = intervalable.getStart();
			if(first==null)
				first = start;
			else if(start.before(first))
				first = start;
			if(last==null)
				last = start;
			else if(start.after(last))
				last = start;
		}
		if(intervalable.getEnd()!=null) {
			Calendar end = intervalable.getEnd();
			if(first==null)
				first = end;
			else if(end.before(first))
				first = end;
			if(last==null)
				last = end;
			else if(end.after(last))
				last = end;
		}
		
		return intervalTree.add(intervalable);
	}

	public void addAll(Set<D> toAdd) {
		for(D intervalable:toAdd) {
			add(intervalable);
		}
	}
	
}

