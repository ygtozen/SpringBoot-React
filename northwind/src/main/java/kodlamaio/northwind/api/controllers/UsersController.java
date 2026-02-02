package kodlamaio.northwind.api.controllers;

import jakarta.validation.Valid;
import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.result.ErrorDataResult;
import kodlamaio.northwind.core.utilities.result.Result;
import kodlamaio.northwind.core.utilities.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user) {
        return ResponseEntity.ok(this.userService.add(user));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError filedError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(filedError.getField(), filedError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama Hataları");
        return errors;
    }

}

/*
* 200 - Get işlemleri için 200 kullanılır add işlemleri için 201 kullanılır genelede
* 300 -
* 400 -
* 500 -
*
* ResponseEntity<?> --> ne döneceği belli değil işleme sonucuna göre sen onu ekle diyoruz
*
* Bu işlem sonucunda hatada dönebilir o yüzden burada bir hata ayıklama işlemi gerçekleştiricez - AOP
* AOP -> bütün metodlarımız geçeceği önüne bir global exception handle yazarız, bütün operasyonlarımıza try-catch yazmak yerine bir
* tane try-catch yazarız ve buütün operasyonları oraya yollarız, spring ile bunu yapabiliyoruz
*
* public ErrorDataResult<Object> ne dönceği belli değil o yüzden obejcy döndürüyoruz
* @ExceptionHandler(MethodArgumentNotValidException) --> Bütün validasyon hatalarında buraya girer
* Map<String, String> --> hangi alan, hata
*
*  @ResponseStatus(HttpStatus.BAD_REQUEST) --> otomatik olarak BAD_REQUEST dönsün 400
*    @Valid fonskiyonu Validation işleminden geçir demek
*
*   AOP - Bütün metodlarınızı "global exception handler" ile yönlenir.
* Bu handlerdan sonra metodlar çağrılır. TÜm metodlara try-catch yazmaktan kurtuluruz.
*     Bu sistemde şu tipte bir hata olursa bu metodu çağır!
* Jpa repository code-first çalışır
* */
