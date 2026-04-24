public class ConnectionTest {
    public void testDatabaseConnection() {
        ClientService service = new ClientService();
        assert service.testConnection() : "Échec de connexion à MySQL";
        service.shutdown();
    }
}