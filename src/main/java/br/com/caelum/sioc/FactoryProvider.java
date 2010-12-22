package br.com.caelum.sioc;

import java.lang.reflect.Method;

public class FactoryProvider implements Provider {

	private final Class factory;
	private final Method m;
	private final Container container;

	public FactoryProvider(Container container,
			Class factory, Method m) {
		this.container = container;
		this.factory = factory;
		this.m = m;
	}

	@Override
	public Object provide(Class type) {
		try {
			return m.invoke(container.provide(factory));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean supports(Class type) {
		return type.isAssignableFrom(m.getReturnType());
	}

}
