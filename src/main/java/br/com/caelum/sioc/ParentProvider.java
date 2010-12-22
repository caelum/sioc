package br.com.caelum.sioc;

public class ParentProvider implements Provider {

	private final Container container;

	public ParentProvider(Container container) {
		this.container = container;
	}

	@Override
	public Object provide(Class type) {
		return container.provide(type);
	}

	@Override
	public boolean supports(Class type) {
		return container.supports(type);
	}

}
