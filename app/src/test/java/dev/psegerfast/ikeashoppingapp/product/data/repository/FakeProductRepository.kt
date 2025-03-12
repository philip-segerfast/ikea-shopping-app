package dev.psegerfast.ikeashoppingapp.product.data.repository

import dev.psegerfast.ikeashoppingapp.core.domain.DataError
import dev.psegerfast.ikeashoppingapp.core.domain.Money
import dev.psegerfast.ikeashoppingapp.core.domain.Result
import dev.psegerfast.ikeashoppingapp.product.domain.Product
import dev.psegerfast.ikeashoppingapp.product.domain.ProductRepository

class FakeProductRepository : ProductRepository {

    override suspend fun fetchProducts(): Result<List<Product>, DataError.IO> {
        return Result.Success(products)
    }

}

private val products = listOf(
    Product.Chair(
        id = 1,
        name = "Henriksdal",
        price = Money(499.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0462849_PE608354_S4.JPG",
        color = "white",
        material = "wood with cover"
    ),
    Product.Couch(
        id = 2,
        name = "Lidhult",
        price = Money(1035.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0667779_PE714073_S4.JPG",
        color = "beige",
        seats = 4
    ),
    Product.Couch(
        id = 3,
        name = "Nyhamn",
        price = Money(3395.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0556706_PE660542_S4.JPG",
        color = "gray",
        seats = 3
    ),
    Product.Couch(
        id = 4,
        name = "Landskrona",
        price = Money(7495.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0320948_PE514802_S4.JPG",
        color = "black",
        seats = 2
    ),
    Product.Chair(
        id = 5,
        name = "Odger",
        price = Money(695.20, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0727322_PE735594_S4.JPG",
        color = "dark blue",
        material = "plastic"
    ),
    Product.Couch(
        id = 6,
        name = "Landskrona",
        price = Money(14396.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0630092_PE694645_S4.JPG",
        color = "black",
        seats = 5
    ),
    Product.Couch(
        id = 7,
        name = "Klippan",
        price = Money(1995.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0655887_PE709150_S4.JPG",
        color = "multi",
        seats = 2
    ),
    Product.Chair(
        id = 8,
        name = "Kullaberg",
        price = Money(499.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0724693_PE734575_S4.JPG",
        color = "black",
        material = "wood and metal"
    ),
    Product.Couch(
        id = 9,
        name = "Stocksund",
        price = Money(4995.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0617825_PE688232_S4.JPG",
        color = "flanell",
        seats = 3
    ),
    Product.Chair(
        id = 10,
        name = "IKEA PS 2012",
        price = Money(495.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0154688_PE312833_S4.JPG",
        color = "black",
        material = "wood"
    ),
    Product.Chair(
        id = 11,
        name = "Henriksdal",
        price = Money(1195.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0728320_PE736188_S4.JPG",
        color = "black",
        material = "wood with cover"
    ),
    Product.Chair(
        id = 12,
        name = "Industriell",
        price = Money(499.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0579780_PE669907_S4.JPG",
        color = "wood",
        material = "wood"
    ),
    Product.Chair(
        id = 13,
        name = "Janinge",
        price = Money(395.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0728156_PE736116_S4.JPG",
        color = "white",
        material = "plastic"
    ),
    Product.Couch(
        id = 14,
        name = "Ektorp",
        price = Money(4495.0, "kr"),
        imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0619738_PE689282_S4.JPG",
        color = "multi",
        seats = 3
    )
)
