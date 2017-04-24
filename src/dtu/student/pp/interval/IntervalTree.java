package dtu.student.pp.interval;

import java.io.Serializable;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

//Using generic class D extends IntervalAble instead of Interval with D as satellite data.
public class IntervalTree<D extends IntervalAble> extends IntervalObserver<D> implements Serializable {
	private NavigableSet<D> intervalTree = new TreeSet<D>();
	private D firstStart, lastStart, lastEnd;
	
	public IntervalTree() {
		
	}
	
	public IntervalTree(Set<D> from) {
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
		//The bookkeeping
		if(intervalable.getStart()!=null)
			if(firstStart==null) {
				firstStart = lastStart = intervalable;
			} else {
				//Returns 1 if firstStart is later than intervalable
				if(firstStart.compareTo(intervalable) == 1);
					firstStart = intervalable;
				if(lastStart.compareTo(intervalable) == -1)
					lastStart = intervalable;
			}
		if(intervalable.getEnd()!=null)
			if(lastEnd==null) {
				lastEnd = intervalable;
			} else {
				if(lastEnd.isEndBefore(intervalable))
					lastEnd = intervalable;
			}
		
		//The adding
		intervalable.addObserver(this);
		return intervalTree.add(intervalable);
	}
	
	public void remove(D intervalable) {
		intervalTree.remove(intervalable);
		if(firstStart!=null && firstStart.equals(intervalable))
			firstStart = intervalTree.first();
		if(lastStart!=null && lastStart.equals(intervalable))
			lastStart = intervalTree.headSet(lastStart).last();
		
		//Need to fix lastEnd if it is changed. O(n)
		if(lastEnd!=null)
			if(lastEnd.equals(intervalable))
				for(D interval:intervalTree)
					if(interval!= null && lastEnd.isEndBefore(intervalable))
						lastEnd = interval;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void intervalChanged(IntervalAble o) {
		//Remove the old value and add the new.
		
		//What could go wrong?
		this.remove((D)o);
		this.add((D)o);
	}

	public void addAll(Set<D> toAdd) {
		for(D intervalable:toAdd)
			this.add(intervalable);
	}
	
}

