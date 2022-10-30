import com.shibartender.dto.CocktailDto
import com.shibartender.model.Cocktail

fun Cocktail.toDto(): CocktailDto =
    CocktailDto(
        id = this.id.toString(),
        name = this.name,
        // ingredients = this.ingredients,
        method = this.method,
        garnish = this.garnish
    )

fun CocktailDto.toCocktail(): Cocktail =
    Cocktail(
        name = this.name,
        // ingredients = this.ingredients,
        method = this.method,
        garnish = this.garnish
    )