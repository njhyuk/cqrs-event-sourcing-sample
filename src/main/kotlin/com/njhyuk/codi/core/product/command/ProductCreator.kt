package com.njhyuk.codi.core.product.command

import com.njhyuk.codi.core.product.domian.Product
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.core.product.event.ProductCreatedEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCreator(
    private val productRepository: ProductRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    @Transactional
    fun create(command: CreateProductCommand): Long {
        val product = productRepository.save(
            Product(
                price = command.price,
                category = command.category,
                brand = command.brand
            )
        )

        applicationEventPublisher.publishEvent(
            ProductCreatedEvent(
                productNo = product.id,
                category = product.category,
                brand = product.brand,
            )
        )

        return product.id
    }
}
