package algorithms.logic;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.gson.Gson;

public class Logic {
	
	
	public boolean doesDo(boolean p, boolean q) {
		return p || q;
	}
	
	@Test
	public void testLogic() {
		
		assertTrue(doesDo(false,false));
		
	}
	
	@Test
	public void testPropositionLogical() {
		
		Predicate<String> lengthIs3 = x -> x.length() == 3;
        Predicate<String> startWithA = x -> x.startsWith("A");

        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        List<String> collect = list.stream()
                .filter(lengthIs3.or(startWithA))
                .collect(Collectors.toList());
        
        List<String> collectNegate = list.stream()
                .filter(startWithA.negate())
                .collect(Collectors.toList());

        System.out.println("Start with A: "+collect);

        System.out.println("No Start with A: "+collectNegate);
        
     // start with "a" or "m"
        boolean result = startWithA.or(x -> x.startsWith("m")).test("mkyong");
        System.out.println(result);     // true

        // !(start with "a" and length is 3)
        boolean result2 = startWithA.and(x -> x.length() == 3).negate().test("abc");
        System.out.println(result2);    // false

	}
	
	@Test
	public void testRules() {
		
		Predicate<String> identity = a -> a.equals(a);
		
		BiPredicate<String, Integer> filter = (x, y) -> {
            return x.length() == y;
        };

        boolean result = filter.test("mkyong", 6);
        System.out.println(result);  // true

        boolean result2 = filter.test("java", 10);
        System.out.println(result2); // false
        
        
        List<Domain> domains = Arrays.asList(new Domain("google.com", 1),
                new Domain("i-am-spammer.com", 10),
                new Domain("mkyong.com", 0),
                new Domain("microsoft.com", 2));

        BiPredicate<String, Integer> bi = (domain, score) -> {
            return (domain.equalsIgnoreCase("google.com") || score == 0);
        };

        // if google or score == 0
        List<Domain> result3 = filterBadDomain(domains, bi);
        System.out.println(result3); // google.com, mkyong.com

        //  if score == 0
        List<Domain> result4 = filterBadDomain(domains, (domain, score) -> score == 0);
        System.out.println(result4); // mkyong.com, microsoft.com

        // if start with i or score > 5
        List<Domain> result5 = filterBadDomain(domains, (domain, score) -> domain.startsWith("i") && score > 5);
        System.out.println(result5); // i-am-spammer.com

        // chaining with or
        List<Domain> result6 = filterBadDomain(domains, bi.or(
                (domain, x) -> domain.equalsIgnoreCase("microsoft.com"))
        );
        System.out.println(result6); // google.com, mkyong.com, microsoft.com
        
        
	}
	
	
	public static <T extends Domain> List<T> filterBadDomain(
            List<T> list, BiPredicate<String, Integer> biPredicate) {

        return list.stream()
                .filter(x -> biPredicate.test(x.getName(), x.getScore()))
                .collect(Collectors.toList());

    }
	
	

}

class StringProcessor {
    static List<String> filter(List<String> list, Predicate<String> predicate) {
        return list.stream().filter(predicate::test).collect(Collectors.toList());
    }
}


class Domain {

    String name;
    Integer score;

    public Domain(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
    
	public String toString() {
		return new Gson().toJson(this);
	}
}
