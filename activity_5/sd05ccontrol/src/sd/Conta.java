package sd.cc;

public class Conta {

	private double saldo;
	// construtores
	public Conta() {
		saldo = 0;
	}

	public Conta(double saldoInicial) {
		saldo = saldoInicial;
	}

	public double getSaldo() {
		return saldo;
	}

	public void credito(double valor) {
		synchronized (this) {
			System.out.println(" ++crédito pedido " + Thread.currentThread().getName() + ", saldo incial=" + saldo);

			double saldoTmp = valor + saldo;
			// simular uma demora
			// try { Thread.sleep(1000); } catch (Exception e){}

			saldo = saldoTmp;

			System.out.println("\t ++crédito realizado " + Thread.currentThread().getName() + " saldo= " + saldo);
			notifyAll();

		}

	}

	public void debito(double valor) throws Exception {
		synchronized (this) {
			System.out.println(" ++ débito pedido " + Thread.currentThread().getName() + ", saldo incial=" + saldo);
			while(this.saldo < valor) {
				wait();
			}
			double saldoTmp = saldo - valor;
			// simular uma demora
			// try { Thread.sleep(1000); } catch (Exception e){}

			saldo = saldoTmp;

			System.out.println("\t ++ débito realizado " + Thread.currentThread().getName() + " saldo= " + saldo);
		}

	}

}
