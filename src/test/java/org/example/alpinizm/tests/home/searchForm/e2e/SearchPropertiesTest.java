package org.example.alpinizm.tests.home.searchForm.e2e;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.alpinizm.BaseTest;
import org.example.alpinizm.pages.home.HomePage;
import org.example.alpinizm.pages.search.SearchResultsPage;
import org.example.alpinizm.selenium.WebDriverManager;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SearchPropertiesFormDataProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("Barcelona", LocalDate.of(2023, 11, 28), LocalDate.of(2023, 11, 30), 2, false, Arrays.asList("Air conditioning"), 2)
        );
    }
}

public class SearchPropertiesTest extends BaseTest {

    private static final HomePage homePage = new HomePage();

    @ParameterizedTest
    @Epic("E2E")
    @Feature("Search using main form")
    @ArgumentsSource(SearchPropertiesFormDataProvider.class)
    public void searchProperties(String location, LocalDate checkInDate, LocalDate checkOutDate,
                                 int guestsNumber, boolean pets, List<String> amenities, int foundItemsNumber) {
        SearchResultsPage searchResultsPage = homePage.searchPropertiesForm()
                .selectLocationByText(location)
//                .selectCheckInDate(checkInDate)
//                .selectCheckOutDate(checkOutDate)
                .setGuestsNumber(guestsNumber)
                .setPetsAllowed(pets)
                .selectAmenitiesByTexts(amenities)
                .clickSearchButton();

        assertThat(searchResultsPage.getFoundPropertiesNumber()).isEqualTo(foundItemsNumber);
    }
}
