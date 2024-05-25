package com.njhyuk.codi.core.product.command

import com.njhyuk.codi.core.price.exception.NotExistsProductException
import com.njhyuk.codi.core.product.domian.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductUpdater(
    private val productRepository: ProductRepository
) {
    @Transactional
    fun update(command: UpdateProductCommand): Long {
        val product = productRepository.findByIdOrNull(command.productNo)
            ?: throw NotExistsProductException()

        product.apply {
            price = command.price
            category = command.category
            brand = command.brand
        }

        return product.id
    }
}
