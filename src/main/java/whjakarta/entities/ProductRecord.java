package whjakarta.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductRecord(
        @NotNull(message = "Need to enter a product name")
        String name,
        //Using String instead of Category for better UX
        @NotNull(message = "Need to enter a category")
        String category,
        @Min(1) @Max(10)
        int rating) {
}
