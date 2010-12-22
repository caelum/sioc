package br.com.caelum.sioc;

public interface Container {

	void useFactory(Object factory);
	void useFactory(Class factory);
	void register(Object something);
	void register(Class something);

	<T> T provide(Class<T> type);

	boolean supports(Class type);

}
