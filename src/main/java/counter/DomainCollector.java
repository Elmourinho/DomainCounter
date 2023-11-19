package counter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DomainCollector {

	public Map<String, Integer> handleDomainCounts(List<String> emails, int limit) {

		if (emails == null || emails.isEmpty()) {
			return Map.of();
		}

		Map<String, Integer> domainCounts = emails.stream()
				.map(String::toLowerCase)
				.distinct()
				.filter(EmailUtil::isValidEmail)
				.collect(Collectors.toMap(
						email -> email.substring(email.lastIndexOf('@') + 1),
						domain -> 1,
						Integer::sum
				));

		return domainCounts.entrySet().stream()
				.sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
				.limit(limit)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
