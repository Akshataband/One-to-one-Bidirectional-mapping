package onetoonebidirectional;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CarEngineDriver {
	public static void main(String[] args) {
//		addData();
//		addEngine();
//		accessCarByEid(2);
//		accessEngineByCid(106);
//		deAllocateEngine(105) ;
//		allocateEngine(105,3);
//		findAll(105);
//		updateCar(108);
//		updateEngine(5);
//		deleteCarById(105);
//		deleteEngineById(5);
//		updateCarModel(107, "Thar");
//		updateCar(106);
//		updateEngine(4);
//		updateEngineCC(3,2400.0);
	}

	public static void addData() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Car c = new Car();
		Engine e = new Engine();
		e.setCc(4500);
		e.setType("petrol");

		c.setBrand("volkswagan");
		c.setModel("polo");
		c.setRegisterDate(LocalDate.now());
		c.setE(e);

		et.begin();
		em.persist(e);
		em.persist(c);
		et.commit();
	}
	
	public static void addEngine() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    Engine e = new Engine();
	    e.setCc(1800);
	    e.setType("Diesel");

	    et.begin();
	    em.persist(e);
	    et.commit();
	}


	public static void accessCarByEid(int eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Engine engine = em.find(Engine.class, eid);
		Car car = engine.getC();
		System.out.println("Car : " + engine.getC());
	}

	public static void accessEngineByCid(int cid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Car c = em.find(Car.class, cid);
		Engine e = c.getE();
		System.out.println("Engine : " + c.getE());

	}

	public static void allocateEngine(int cid, int eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Car c = em.find(Car.class, cid);
		Engine e = em.find(Engine.class, eid);
		c.setE(e);
		em.merge(c);
		et.commit();
	}

	public static void deAllocateEngine(int cid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Car c = em.find(Car.class, cid);
//		Engine e = em.find(Engine.class, eid);
		c.setE(null);
		em.merge(c);
		et.commit();
	}

	public static void findAll(int cid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query q = em.createQuery("SELECT c FROM Car c");
		List<Car> list = q.getResultList();
		et.commit();

		list.forEach(car -> System.out.println(
				car.getId() + " | " + car.getBrand() + " | " + car.getModel() + " | " + car.getRegisterDate() ));
	}

	public static void updateCar(int cid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Car car = em.find(Car.class, cid);
		car.setBrand("Maruti");
		car.setModel("XUV");
		em.merge(car);
		et.commit();
	}

	public static void updateEngine(int eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Engine engine = em.find(Engine.class, eid);
		engine.setType("Diesel");
		engine.setCc(1500);
		em.merge(engine);
		et.commit();
	}

	public static void deleteEngineById(int eid) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    et.begin();
	    Engine engine = em.find(Engine.class, eid);
	    em.remove(engine);
	    et.commit();
	}


	public static void deleteCarById(int cid) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    et.begin();
	    Car car = em.find(Car.class, cid);
	    em.remove(car);
	    et.commit();
	}

	
	public static void updateCarModel(int cid, String newModel) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    et.begin();
	    Car car = em.find(Car.class, cid);
	    car.setModel(newModel);
	    em.merge(car);
	    et.commit();
	}
	public static void updateEngineCC(int eid, double newCC) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    et.begin();
	    Engine engine = em.find(Engine.class, eid);
	    engine.setCc(newCC);
	    em.merge(engine);
	    et.commit();
	}


}
