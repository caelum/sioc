package br.com.caelum.sioc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CuteContainer implements Container {
	
	private final List<Provider> providers = new ArrayList<Provider>();

	public CuteContainer(Container container) {
		providers.add(new ParentProvider(container));
	}
	
	public CuteContainer() {
	}
	
	@Override
	public  void register(Object something) {
		providers.add(new FixedProvider(something));
	}

	@Override
	public <T> T provide(Class<T> type) {
		return (T) providerFor(type).provide(type);
	}
	
	private <T> Provider<T> providerFor(Class<T> type) {
		for(Provider provider : providers) {
			if(provider.supports(type)) {
				return provider;
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class type) {
		for(Provider provider : providers) {
			if(provider.supports(type)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void useFactory(Object factory) {
		register(factory);
		registerFactoryMethodsFor(factory.getClass());
	}

	@Override
	public void useFactory(Class factory) {
		register(factory);
		registerFactoryMethodsFor(factory);
	}

	private void registerFactoryMethodsFor(Class factory) {
		for(Method m : factory.getDeclaredMethods()) {
			if(!m.getReturnType().equals(Void.class)) {
				providers.add(new FactoryProvider(this, factory, m));
			}
		}
	}
	@Override
	public void register(Class something) {
		providers.add(new InstantiateOnce(something));
	}


}
