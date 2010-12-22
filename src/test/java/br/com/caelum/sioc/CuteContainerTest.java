package br.com.caelum.sioc;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class CuteContainerTest {
	
	@Test
	public void shouldReturnObjectThatWasRegistered() {
		Container container = new CuteContainer();
		container.register(new DefaultEmailSender());
		DefaultEmailSender provided = container.provide(DefaultEmailSender.class);
		assertEquals(DefaultEmailSender.class, provided.getClass());
	}
	
	@Test
	public void shouldReturnInstanceFromTypeIfRegisteringType() {
		Container container = new CuteContainer();
		container.register(DefaultEmailSender.class);
		DefaultEmailSender provided = container.provide(DefaultEmailSender.class);
		assertEquals(DefaultEmailSender.class, provided.getClass());
	}
	
	@Test
	public void shouldReturnAlwaysTheSameIfTheTypeIsRegistered() {
		Container container = new CuteContainer();
		container.register(DefaultEmailSender.class);
		assertEquals(container.provide(DefaultEmailSender.class),container.provide(DefaultEmailSender.class));
	}
	
	@Test
	public void shouldAllowContainerReplication() {
		Container container = new CuteContainer();
		container.register(new DefaultEmailSender());
		CuteContainer child = new CuteContainer(container);
		DefaultEmailSender provided = child.provide(DefaultEmailSender.class);
		assertEquals(DefaultEmailSender.class, provided.getClass());
	}
	
	static class EmailSenderFactory {
		public DefaultEmailSender sender() {
			return new DefaultEmailSender(); 
		}
	}

	@Test
	public void shouldAllowRegisteringFactoryInstance() {
		Container container = new CuteContainer();
		container.useFactory(new EmailSenderFactory());
		DefaultEmailSender provided = container.provide(DefaultEmailSender.class);
		assertEquals(DefaultEmailSender.class, provided.getClass());
	}

	@Test
	public void shouldAllowRegisteringFactoryType() {
		Container container = new CuteContainer();
		container.useFactory(EmailSenderFactory.class);
		DefaultEmailSender provided = container.provide(DefaultEmailSender.class);
		assertEquals(DefaultEmailSender.class, provided.getClass());
	}
	
	static class AppConfig {
		Integer base() {
			return 1;
		}
	}
	
	static class RequestConfig {
		private final Integer base;
		public RequestConfig(Integer base) {
			this.base = base;
		}
		
		public Double anExtraHalf() {
			return this.base+0.5;
		}
	}

	@Test
	public void shouldAcceptInjection() {
		Container container = new CuteContainer();
		container.useFactory(EmailSenderFactory.class);
		DefaultEmailSender provided = container.provide(DefaultEmailSender.class);
		assertEquals(DefaultEmailSender.class, provided.getClass());
	}

}
