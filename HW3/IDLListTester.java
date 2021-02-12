import org.junit.Test;

import static org.junit.Assert.*;

public class IDLListTester {

    @Test
    public void test1() {
		IDLList<Integer> l = new IDLList<>();
		assertNotNull(l);
		assertEquals(l.size(), 0);
		assertEquals(l.toString(), "[]");

    }
    
    @Test
    public void test2() {
		IDLList<Integer> l = new IDLList<>();
		l.add(1);
		assertEquals(l.size(), 1);
		assertEquals(l.get(0), (Integer)1);
		assertEquals(l.getHead(), l.getLast());
		assertEquals(l.remove(), (Integer)1);
		assertEquals(l.size(), 0);
		}
    
    @Test 
    public void test3() {
		IDLList<Integer> l = new IDLList<>();
		for(int i = 0; i <= 10; i++) {
			l.add(i, i);
		}
		
		assertEquals(l.size(), 11);
		assertEquals(l.getHead(), (Integer)0);
		assertEquals(l.getLast(), (Integer)10);
		l.removeAt(5);
		assertEquals(l.size(), 10);
		assertEquals(l.get(6), (Integer)7);
		assertEquals(l.get(5), (Integer)6);
		assertEquals(l.get(4), (Integer)4);
		assertEquals(l.toString(), "[0,1,2,3,4,6,7,8,9,10]");
		l.append(11);
		assertEquals(l.size(), 11);
		assertEquals(l.getHead(), (Integer)0);
		assertEquals(l.getLast(), (Integer)11);
		
    	}
    
    @Test
    public void test4() {
		IDLList<Integer> l = new IDLList<>();

    	for(int i = 0; i <= 5; i++) {
			l.add(i, i);
    	}
    	l.remove(2);
    	assertEquals(l.toString(),"[0,1,3,4,5]");
    	assertFalse(l.remove(2));
    }
    
    @Test
    public void test5() {
		IDLList<Integer> l = new IDLList<>();

    	for(int i = 0; i <= 5; i++) {
			l.add(i, i);
    	}
    	
    	l.remove();
    	l.removeLast();
    	assertEquals(l.toString(), "[1,2,3,4]");
    	assertEquals(l.getHead(), (Integer)1);
    	assertEquals(l.getLast(), (Integer)4);

    }
}