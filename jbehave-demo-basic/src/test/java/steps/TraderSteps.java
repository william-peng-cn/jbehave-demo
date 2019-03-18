package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;


public class TraderSteps {


	private double threshold;
	private double price;

	@Given("a stock of symbol $symbol and a threshold of $threshold")
	public void aStock(String symbol, double threshold) {
		this.threshold = threshold;
	}

	@When("the stock is traded at $price")
	public void theStockIsTradedAt(double price) {
		this.price = price;
	}

	@Then("the alert status should be $status")
	public void theAlertStatusShouldBe(String status) {
		Assert.assertTrue((price > threshold) ? "ON".equals(status) : "OFF".equals(status));
	}

}
