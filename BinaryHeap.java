import java.util.*;

public class BinaryHeap {
	private int[] array;
	private int current;
	private int size;
	private int count;

	public BinaryHeap() {
		this.current = 0;
		this.size = 10;
		this.array = new int[size];
		this.count = 0;
	}
	

	public void growArray() {
		int new_size = this.size * 2;
		int [] newArray = new int[new_size];
		for(int i = 0; i < this.size; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
		this.size = new_size;
	}
	

	public void add(int integer) {
		if(this.count + 1 >= this.size) {
			growArray();
		}
		array[count] = integer;
		int position = count;
		count++;
		while(array[position] < array[(position - 1)/2]) {
			swap(position, (position - 1)/2);
			position = (position-1)/2;
		}
	}

	public int remove() {
		try {
			if(this.size == 0) {
				throw new Exception("The heap is empty.");
			} else {
				swap(0, count);
				count--;
				if(count != 0) {
					shiftDown(0);
				}
				return array[count+1];
			}	
		} catch (Exception e) {
			return -1;
		}
	}

	public void swap(int valueF, int valueS) {
		int temp = array[valueF];
		array[valueF] = array[valueS];
		array[valueS] = temp;
	}

	public void shiftDown(int index) {
		int leftChild = (index * 2) + 1;
		int rightChild = (index * 2) + 2;
		
		if(leftChild >= count) {
			return;
		}

		if(array[rightChild] < array[leftChild]) {
			leftChild = rightChild;
		}

		if(array[leftChild] < array[index]) {
			swap(leftChild, index);
			shiftDown(leftChild);
		}
	}
}