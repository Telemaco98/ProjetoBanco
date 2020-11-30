package br.ic.ufal.testingtools.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ContaTeste {
	public Conta conta;
	
	public static String nome = "Teste Conta";
	public static int saldo = 2200;
	public static int credito = 200;
	
	@Before
	public void iniciar () {
		conta = new Conta(nome, saldo, credito);
	}
		
	@Test
	public void testeSaque() {
		conta.saque(200);
		assertEquals(2000, conta.saldo());
	}
	
	@Test
	public void testeSaqueBoundary() {
		conta.saque(2200);
		assertEquals(0, conta.saldo());
	}
	
	@Test
	public void testeSaqueException() {
		try {
			conta.saque(2201);
			fail("Teste Saque exceção falhou");
		} catch (SaldoNaoSuficienteException e) {
			assertEquals("Você não possui saldo suficiente", e.getMessage());
		}
	}
	
	@Test
	public void testeDeposito() {
		conta.deposito(99);
		assertEquals(2299, conta.saldo());
	}
	
	@Test
	public void testeTransferencia() {
		conta.transferenciaDoc(500);
		assertEquals(1700, conta.saldo());
	}
	
	@Test
	public void testePagarConta() {
		conta.pagarConta(59);
		assertEquals(2141, conta.saldo());
		assertEquals(200, conta.credito());
	}
	
	@Test
	public void testePagarConta1() {
		conta.pagarConta(0);
		assertEquals(2200, conta.saldo());
		assertEquals(200, conta.credito());
	}

	@Test
	public void testePagarContaComCredito() {
		conta.pagarConta(2300);
		assertEquals(0, conta.saldo());
		assertEquals(100, conta.credito());
	}

	@Test
	public void testePagarContaCreditoExcept() {
		try {
			conta.pagarConta(5999);
			fail("Teste PagarConta falhou");
		} catch (SaldoNaoSuficienteException e) {
			assertEquals("Você não possui saldo nem crédito suficientes", e.getMessage());
		}
	}
	
	@Test
	public void testePagarContaBoundary() { // com saldo
		conta.pagarConta(2199);
		assertEquals(1, conta.saldo());
		assertEquals(200, conta.credito());
	}
	
	@Test
	public void testePagarContaBoundaryEquals() {
		conta.pagarConta(2200);
		assertEquals(0, conta.saldo());
		assertEquals(200, conta.credito());
	}
	
	@Test
	public void testePagarContaUpBoundary() { // com credito
		conta.pagarConta(2201);
		assertEquals(0, conta.saldo());
		assertEquals(199, conta.credito());
	}
	
	@Test
	public void testePagarContaCreditoUpBoundary() { // com exceção
		try {
			conta.pagarConta(2401);
			fail("teste falhou");
		} catch (SaldoNaoSuficienteException e) {
			assertEquals(2200, conta.saldo());
			assertEquals(200, conta.credito());
		}
	}
	
	@Test
	public void testePagarContaComCreditoBoundary() {
		conta.pagarConta(2400);
		assertEquals(0, conta.saldo());
		assertEquals(0, conta.credito());
	}
	
	@Test
	public void testeSaldo() {
		assertEquals(2200, conta.saldo());
	}
	
	@Test
	public void testeCredito() {
		assertEquals(200, conta.credito());
	}
	
	@Test
	public void testeTransferenciaException () {
		try {
			conta.transferenciaDoc(501);
			fail("Teste de Transferencia Exception falhou");
		} catch (TransferenciaException e) {
			assertEquals("Valor máximo para transferência DOC: R$ 5.000,00", e.getMessage());
		}
	}
	
	@Ignore
	public void testeSaqueNumerosNeg() {
		conta.saque(-1000);
		assertEquals(2200, conta.saldo()); // Saque não deveria aceitar aceitar numeros negativos
	}
	
	@Ignore
	public void testeDepositoNumerosNeg() {
		conta.deposito(-100);
		assertEquals(2200, conta.saldo()); // Deposito não deveria aceitar aceitar numeros negativos
	}	

	@Ignore
	public void testeTransferenciaNumerosNeg() {
		conta.transferenciaDoc(-10);
		assertEquals(2200, conta.saldo()); // Transferencia não deveria aceitar aceitar numeros negativos
	}

	@Ignore
	public void testePagarContaNumerosNeg() {
		conta.pagarConta(-1256);
		assertEquals(2200, conta.saldo()); // Pagar Conta não deveria aceitar aceitar numeros negativo
	}

	@Test
	public void testeImprimeDados() {
		conta.imprimeDados();
		assertTrue(true);
	}
}