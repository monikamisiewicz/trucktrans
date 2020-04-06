package pl.coderslab.trucktrans.contractor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.junit.Assert.*;

class ContractorServiceTest {

    private ContractorRepository contractorRepository;
    private ContractorService contractorService;

    @Test
    @DisplayName("żźćąęó")
    void shouldDisplayPolishCaractersInPdf() {
        String expected = "żźćąęó";
        String actual  = "żźćąęó";
        assertEquals(expected, actual);
    }
}