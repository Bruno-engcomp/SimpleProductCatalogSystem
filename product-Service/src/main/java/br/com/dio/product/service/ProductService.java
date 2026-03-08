package br.com.dio.product.service;

import br.com.dio.product.dto.ProductRequest;
import br.com.dio.product.dto.ProductResponse;
import br.com.dio.product.dto.ProductUpdate;
import br.com.dio.product.model.Product;
import br.com.dio.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse productCreate(ProductRequest productRequest)
    {
        Product product = new Product(  // Criacao de um novo produto com os dados recebidos pelo dtoRequest
                productRequest.name(),
                productRequest.description(),
                productRequest.price()
        );

        Product savedProduct = productRepository.save(product); // Salvando o produto criado no banco de dados jpa

        return new ProductResponse( // transformando a entidade produto em dto para poder retornar o produto criado como resposta
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice()
        );
    }

    public List<ProductResponse> getAllproducts()
    {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        )).toList();
    }

    public ProductResponse getProductById(Long id)
    {
        Product product = productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id)); // Caso nao encontre o produto com esse id lanca a excecao
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public ProductResponse productUpdate(Long id,ProductUpdate productUpdate)
    {

        Product product = productRepository.findById(id). // Busca o produto que eu quero atualizar e se nao achar, lanca a excecao
                orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        // altera os dados do produto
        product.setName(productUpdate.name());
        product.setDescription(productUpdate.description());
        product.setPrice(productUpdate.price());

        // Retorna a resposta
        Product updatedProduct = productRepository.save(product);
        return new ProductResponse( // transformando a entidade produto em dto para poder retornar o produto criado como resposta
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice()
        );
    }

    public void productDelete(long id)
    {
        Product product = productRepository.findById(id). // Busca o produto que eu quero atualizar e se nao achar, lanca a excecao
                orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));


        productRepository.delete(product); // Deleta do repositorio
    }
}
