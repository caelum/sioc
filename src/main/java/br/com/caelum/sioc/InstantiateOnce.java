package br.com.caelum.sioc;

public class InstantiateOnce implements Provider {

	private final Class something;
	private Object instance;

	public InstantiateOnce(Class something) {
		this.something = something;
	}

	@Override
	public Object provide(Class type) {
		if (this.instance == null) {
			try {
				instance = something.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return instance;
	}

	@Override
	public boolean supports(Class type) {
		return type.isAssignableFrom(something);
	}

}
