package counter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static final int TOP_COUNT = 10;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter email addresses (separated by spaces):");
		String input = scanner.nextLine();

		List<String> emails = Arrays.asList(input.split("\\s+"));

		DomainCollector domainCollector = new DomainCollector();
		Map<String, Integer> sortedDomainFrequency = domainCollector.handleDomainCounts(emails, TOP_COUNT);

		System.out.println("Domain frequencies:");
		sortedDomainFrequency.forEach((domain, frequency) -> System.out.println(domain + ": " + frequency));

		scanner.close();
	}

}
