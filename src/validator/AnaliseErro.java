/**
 * 
 */
package validator;

/**
 * @author Wendel F. Lopes
 *
 */
public interface AnaliseErro<T extends Exception> {
	
	void tratarErro(T e);
	
}
