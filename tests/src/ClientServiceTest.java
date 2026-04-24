import service.ClientService;
import model.Client;

public class ClientServiceTest {
    private static final String TEST_USER = "testuser_" + System.currentTimeMillis();
    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_PWD = "test123";

    public void testRegisterAndAuthenticate() {
        ClientService service = new ClientService();
        // Register
        boolean reg = service.register(TEST_USER, TEST_EMAIL, TEST_PWD);
        assert reg : "Inscription échouée";
        // Authenticate
        Client client = service.authenticate(TEST_USER, TEST_PWD);
        assert client != null : "Authentification échouée après inscription";
        assert !client.isBlocked() : "Client devrait être actif";
        service.shutdown();
    }

    public void testChangePassword() {
        ClientService service = new ClientService();
        service.register("pwduser", "pwd@test.com", "oldpass");
        Client c = service.authenticate("pwduser", "oldpass");
        assert c != null;
        boolean changed = service.changePassword(c.getId(), "pwduser", "oldpass", "newpass", "newpass");
        assert changed : "Changement de mot de passe échoué";
        Client c2 = service.authenticate("pwduser", "newpass");
        assert c2 != null : "Nouveau mot de passe non accepté";
        service.shutdown();
    }
}