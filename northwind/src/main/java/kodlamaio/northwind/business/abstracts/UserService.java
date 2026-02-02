package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.result.DataResult;
import kodlamaio.northwind.core.utilities.result.Result;

import java.util.List;

public interface UserService {

    Result add(User user);
    DataResult<User> findByEmail(String email);

}
