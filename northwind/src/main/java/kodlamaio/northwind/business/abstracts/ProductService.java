package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.core.utilities.result.DataResult;
import kodlamaio.northwind.core.utilities.result.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductService {
    //List<Product> getAll(); -->List<Product> yerine DataResult döndürüyoruz
    DataResult<List<Product>> getAll();
    Result add(Product product);

    // Data dönecekleri için DataResult yapıyoruz
    DataResult<Product> getByProductName(String productName);
    DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId);
    DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId);
    DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories);
    DataResult<List<Product>> getByProductNameContains(String productName);
    DataResult<List<Product>> getByProductNameStartsWith(String productName);
    DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId);

    // Sayfalama
    DataResult<List<Product>> getAll(int pageNumber, int pageSize); // sayfano, bi sayfada kaç data var

    // Sıralama
    DataResult<List<Product>> getAllSorted();

    DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails();
}
