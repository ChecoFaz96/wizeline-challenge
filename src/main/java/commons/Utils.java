package commons;

public class Utils {
	
	public float[] sortMethod(float [] value) {
		for(int arrayIterator = 0; arrayIterator < value.length; arrayIterator++ ) {
			for(int j = arrayIterator+1; j < value.length; j++) {
				//if(value[arrayIterator].compareTo(value[j])>0)
				if(Float.compare(value[arrayIterator], value[j])>0) {
					float temp = value[arrayIterator];
					value[arrayIterator] = value[j];
					value[j] = temp;
				}
			}
		}
		return value;
	}
}
