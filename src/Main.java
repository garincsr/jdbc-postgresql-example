import com.enigmacamp.console.CustomerConsole;
import com.enigmacamp.console.MainConsole;
import com.enigmacamp.console.ProductConsole;
import com.enigmacamp.console.TransactionConsole;
import com.enigmacamp.service.CustomerService;
import com.enigmacamp.service.ProductService;
import com.enigmacamp.service.TransactionService;
import com.enigmacamp.service.impl.CustomerServiceImpl;
import com.enigmacamp.service.impl.ProductServiceImpl;
import com.enigmacamp.service.impl.TransactionServiceImpl;
import com.enigmacamp.utils.InputHandler;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();

        CustomerService customerService = new CustomerServiceImpl();
        ProductService productService = new ProductServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl(productService);

        CustomerConsole customerConsole = new CustomerConsole(customerService, inputHandler);
        ProductConsole productConsole = new ProductConsole(productService, inputHandler);
        TransactionConsole transactionConsole = new TransactionConsole(
                customerConsole,
                productService,
                transactionService,
                inputHandler
        );

        MainConsole mainConsole = new MainConsole(
                inputHandler,
                productConsole,
                customerConsole,
                transactionConsole
        );

        mainConsole.run();
    }
}
