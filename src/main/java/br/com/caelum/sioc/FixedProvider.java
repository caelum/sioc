package br.com.caelum.sioc;

public class FixedProvider<T> implements Provider<T> {

	private final T instance;

	public FixedProvider(T what) {
		this.instance =what;
	}

	@Override
	public T provide(Class type) {
		return instance;
	}

	@Override
	public boolean supports(Class<T> type) {
		return type.isAssignableFrom(instance.getClass());
	}

}
