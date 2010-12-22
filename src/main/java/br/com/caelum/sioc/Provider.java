package br.com.caelum.sioc;

public interface Provider<T> {

	Object provide(Class type);
	
	boolean supports(Class<T> type);

}
