import java.sql.Connection;

import com.ecomProject.EcommerceJdbc.dao.CustomerDAO;
import com.ecomProject.EcommerceJdbc.exception.CustomerNotFoundException;
import com.ecomProject.EcommerceJdbc.menu.MainMenu;
import com.ecomProject.EcommerceJdbc.models.Customer;
import com.ecomProject.EcommerceJdbc.services.CustomerService;
import com.ecomProject.EcommerceJdbc.utils.DBConnection;

public class Driver {

	public static void main(String[] args) throws CustomerNotFoundException {

		try {
			Connection conn = DBConnection.getConnection();

			if (conn != null) {
				System.out.println("Database Connected Successfully!");
			} else {
				System.out.println("Connection Failed.");
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// CustomerDAO dao = new CustomerDAO();

		// need to update on each test to avoid duplicate
//		Customer customer = new Customer("Test8861", "User", "testuser92685@gmail.com", "9876543299", "test123", "Pune",
//				"USER");
//
//		boolean result = dao.addCustomer(customer);
//
//		System.out.println(result);
//
//		for (Customer customerNmae : dao.getAllCustomers()) {
//			System.out.println(customerNmae);
//		}

		// CustomerService service = new CustomerService();

		// need to update on each test to avoid duplicate
//		Customer customer1 = new Customer("Rahulll", "Patil", "rahullpatilgggg@gmail.com", "9876543218", "rahul123",
//				"Pune", "USER");

		// System.out.println(service.registerCustomer(customer1));

		MainMenu menu = new MainMenu();
		menu.showMainMenu();

	}
}
