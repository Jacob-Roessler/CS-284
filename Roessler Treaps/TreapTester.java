import org.junit.Test;
import static org.junit.Assert.*;

public class TreapTester {
	
	@Test
    public void test1() {
		Treap<Integer> testTree = new Treap<>();
		assertNotNull(testTree);
		testTree.add(10);
		assertTrue(testTree.delete(10));
		assertEquals(testTree.toString(),"null\n");
    }
	
	@Test
    public void test2() {
		Treap<Integer> testTree = new Treap<>(1);
		testTree.add(10);
		
		assertEquals(testTree.toString(),
				"(key=10, priority=-1155869325)\n" + 
				" null\n" + 
				" null\n");
		
		assertTrue(testTree.add(1, 50));
		
		assertEquals(testTree.toString(),
				"(key=1, priority=50)\n" + 
				" null\n" + 
				" (key=10, priority=-1155869325)\n" + 
				"  null\n" + 
				"  null\n");
		
		assertTrue(testTree.find(1) && testTree.find(10));
    }

	@Test
    public void test3() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19); 
		testTree.add(2,31);
		testTree.add(6,70); 
		testTree.add(1,84);
		testTree.add(3,12); 
		testTree.add(5,83); 
		testTree.add(7,26); 
		
		assertEquals(testTree.toString(), 
				"(key=1, priority=84)\n" + 
				" null\n" + 
				" (key=5, priority=83)\n" + 
				"  (key=2, priority=31)\n" + 
				"   null\n" + 
				"   (key=4, priority=19)\n" + 
				"    (key=3, priority=12)\n" + 
				"     null\n" + 
				"     null\n" + 
				"    null\n" + 
				"  (key=6, priority=70)\n" + 
				"   null\n" + 
				"   (key=7, priority=26)\n" + 
				"    null\n" + 
				"    null\n");
		
		assertTrue(testTree.delete(6));
		
		assertEquals(testTree.toString(),
				"(key=1, priority=84)\n" + 
				" null\n" + 
				" (key=5, priority=83)\n" + 
				"  (key=2, priority=31)\n" + 
				"   null\n" + 
				"   (key=4, priority=19)\n" + 
				"    (key=3, priority=12)\n" + 
				"     null\n" + 
				"     null\n" + 
				"    null\n" + 
				"  (key=7, priority=26)\n" + 
				"   null\n" + 
				"   null\n");
		
		assertFalse(testTree.delete(50));
		
		
	}
	
	@Test
    public void test4() {
		Treap<Character> testTree = new Treap<Character>();
		testTree.add('p',99); 
		testTree.add('g',80);
		testTree.add('u',75); 
		testTree.add('a',60);
		testTree.add('j',65); 
		testTree.add('r',40); 
		testTree.add('z',47); 
		testTree.add('w',32); 
		testTree.add('v',21);
		testTree.add('x',25); 
		
		assertEquals(testTree.toString(),
				"(key=p, priority=99)\n" + 
				" (key=g, priority=80)\n" + 
				"  (key=a, priority=60)\n" + 
				"   null\n" + 
				"   null\n" + 
				"  (key=j, priority=65)\n" + 
				"   null\n" + 
				"   null\n" + 
				" (key=u, priority=75)\n" + 
				"  (key=r, priority=40)\n" + 
				"   null\n" + 
				"   null\n" + 
				"  (key=z, priority=47)\n" + 
				"   (key=w, priority=32)\n" + 
				"    (key=v, priority=21)\n" + 
				"     null\n" + 
				"     null\n" + 
				"    (key=x, priority=25)\n" + 
				"     null\n" + 
				"     null\n" + 
				"   null\n");
		assertTrue(testTree.delete('z'));

		assertEquals(testTree.toString(),
				"(key=p, priority=99)\n" + 
				" (key=g, priority=80)\n" + 
				"  (key=a, priority=60)\n" + 
				"   null\n" + 
				"   null\n" + 
				"  (key=j, priority=65)\n" + 
				"   null\n" + 
				"   null\n" + 
				" (key=u, priority=75)\n" + 
				"  (key=r, priority=40)\n" + 
				"   null\n" + 
				"   null\n" + 
				"  (key=w, priority=32)\n" + 
				"   (key=v, priority=21)\n" + 
				"    null\n" + 
				"    null\n" + 
				"   (key=x, priority=25)\n" + 
				"    null\n" + 
				"    null\n");
		
		assertTrue(testTree.find('x'));
		assertFalse(testTree.find('z'));
		
	}
	
	@Test
	public void test5() {
		Treap<Integer> testTree = new Treap<>(5);
		for(int i = 0; i < 10; i++)
			testTree.add(i);
		
		assertEquals(testTree.toString(),
				"(key=4, priority=2099829013)\n" + 
				" (key=1, priority=758500184)\n" + 
				"  (key=0, priority=-1157408321)\n" + 
				"   null\n" + 
				"   null\n" + 
				"  (key=2, priority=379066948)\n" + 
				"   null\n" + 
				"   (key=3, priority=-1667228448)\n" + 
				"    null\n" + 
				"    null\n" + 
				" (key=6, priority=1983575708)\n" + 
				"  (key=5, priority=-236332086)\n" + 
				"   null\n" + 
				"   null\n" + 
				"  (key=8, priority=1926715444)\n" + 
				"   (key=7, priority=-745993913)\n" + 
				"    null\n" + 
				"    null\n" + 
				"   (key=9, priority=1836354642)\n" + 
				"    null\n" + 
				"    null\n");
	}
	
}
