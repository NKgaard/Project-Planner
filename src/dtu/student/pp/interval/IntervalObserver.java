package dtu.student.pp.interval;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public abstract class IntervalObserver<D extends IntervalAble> implements Observer, Serializable {
	
	public abstract void intervalChanged(IntervalAble o);
	
	@Override
	public final void update(Observable o, Object arg) {
		//Can maybe add more methods later.
		if(o instanceof IntervalAble)
			intervalChanged((IntervalAble) o);
	}
	
}
