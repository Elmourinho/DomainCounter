package counter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DomainCollectorTest {

	private final DomainCollector domainCollector = new DomainCollector();

	@Test
	public void testHandleDomainCounts() {

		List<String> emails = Arrays.asList(
				"user1@example.com",
				"user2@example.com",
				"user1@example.net"
		);

		Map<String, Integer> domainCounts = domainCollector.handleDomainCounts(emails, 10);

		assertEquals(2, domainCounts.size());
		assertEquals(2, domainCounts.get("example.com").intValue());
		assertEquals(1, domainCounts.get("example.net").intValue());
	}

	@Test
	public void testHandleDomainCountsWithCaseSensitiveEmails() {

		List<String> emails = Arrays.asList(
				"User1@example.com",
				"user1@example.com",
				"user1@example.net",
				"USER1@example.net",
				"USER1@EXAMPLE.NET"
		);

		Map<String, Integer> domainCounts = domainCollector.handleDomainCounts(emails, 10);

		assertEquals(2, domainCounts.size());
		assertEquals(1, domainCounts.get("example.com").intValue());
		assertEquals(1, domainCounts.get("example.net").intValue());
	}

	@Test
	public void testHandleDomainCountsWithUniqueEmails() {

		List<String> emails = Arrays.asList(
				"user1@example.com",
				"user1@example.com",
				"user1@example.net",
				"user1@example.net",
				"user1@example.net",
				"user1@example.net"
		);

		Map<String, Integer> domainCounts = domainCollector.handleDomainCounts(emails, 10);

		assertEquals(2, domainCounts.size());
		assertEquals(1, domainCounts.get("example.com").intValue());
		assertEquals(1, domainCounts.get("example.net").intValue());
	}

	@Test
	public void testHandleDomainCountsWithInvalidEmails() {

		List<String> emails = Arrays.asList(
				"user1@example.com",
				"user2@example.com",
				"invalid-email",
				"user3@example.net",
				"user4@example.net",
				"user5@example.net",
				"user6@example.net",
				"user1@mail.com",
				"user2@mail.com",
				"user3@mail.com",
				"invalid-email2"

		);

		Map<String, Integer> domainCounts = domainCollector.handleDomainCounts(emails, 10);

		assertEquals(3, domainCounts.size());
		assertEquals(2, domainCounts.get("example.com").intValue());
		assertEquals(4, domainCounts.get("example.net").intValue());
		assertEquals(3, domainCounts.get("mail.com").intValue());
		assertNull(domainCounts.get("invalid-email"));
		assertNull(domainCounts.get("invalid-email2"));
	}

	@Test
	public void testHandleDomainCountsEmptyList() {

		List<String> emails = Collections.emptyList();
		Map<String, Integer> domainCounts = domainCollector.handleDomainCounts(emails, 10);

		assertEquals(0, domainCounts.size());
	}

	@Test
	public void testHandleDomainCountsNullList() {

		Map<String, Integer> domainCounts = domainCollector.handleDomainCounts(null, 10);

		assertEquals(0, domainCounts.size());
	}


}