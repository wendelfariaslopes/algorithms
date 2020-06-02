package programs.io;

import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BiConsumerTest {

	public static void main(String[] args) {
		String CLASS_PATH = System.getProperty("java.class.path");

		// System.out.println(CLASS_PATH);

		/**
		 * BiConsumer is a java 8 functional interface. BiConsumer does not return
		 * value. It accepts two parameter as an argument. BiConsumer functional method
		 * is accept(Object, Object). This methods performs the operation defined by
		 * BiConsumer.
		 */
		BiConsumer<String, String> display = (k, v) -> System.out.println(k + " : " + v);

		//System.getenv().forEach(display);
		
		Properties prop = System.getProperties();

		prop.forEach((k, v) -> System.out.println(k + " : " + v));

		/**
		 * BiFunction is a functional interface. BiFunction accepts two arguments and
		 * returns a value. While declaring BiFunction we need to tell what type of
		 * argument will be passed and what will be return type. We can apply our
		 * business logic with those two values and return the result. BiFunction has
		 * function method as apply(T t, U u) which accepts two argument.
		 */
		BiFunction<Integer, Integer, String> biFunction = (num1, num2) -> "Result:" + (num1 + num2);
		//System.out.println(biFunction.apply(20, 25));

		/**
		 * BiPredicate is a functional interface which accepts two argument and returns
		 * Boolean value. Apply business logic for the values passed as an argument and
		 * return the boolean value. BiPredicate functional method is test(Object,
		 * Object). Find the simple example for how to use BiPredicate.
		 */
		BiPredicate<Integer, String> condition = (i, s) -> i > 20 && s.startsWith("R");
		//System.out.println(condition.test(10, "Ram"));
		//System.out.println(condition.test(30, "Shyam"));
		//System.out.println(condition.test(30, "Ram"));

	}

}
