package bg.softuni;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.*;

import java.time.LocalDate;
import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercises");

        EntityManager em = emf.createEntityManager();

        // 2. Sales Database

        /*
        em.getTransaction().begin();

        // Create and persist a StoreLocation
        StoreLocation location = new StoreLocation();
        location.setName("Downtown Branch");
        em.persist(location);

        // Create and persist a Product
        Product product = new Product();
        product.setName("Coffee Beans");
        product.setQuantity(50.0);
        product.setPrice(12.99);
        em.persist(product);

        // Create and persist a Customer
        Customer customer = new Customer();
        customer.setName("Alice Smith");
        customer.setEmail("alice@example.com");
        customer.setCreditCardNumber("1234-5678-9012-3456");
        em.persist(customer);

        // Create and persist a Sale
        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setProduct(product);
        sale.setStoreLocation(location);
        sale.setDate(LocalDate.now());
        em.persist(sale);

        em.getTransaction().commit();
        */

        // 3. University System

        /*
        em.getTransaction().begin();

        Teacher teacher = new Teacher();
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setEmail("john.doe@example.com");
        teacher.setSalaryPerHour(45.0);
        em.persist(teacher);

        Course course = new Course();
        course.setName("Java Basics");
        course.setDescription("Introductory Java programming course");
        course.setStartDate(LocalDate.of(2025, 9, 1));
        course.setEndDate(LocalDate.of(2025, 12, 15));
        course.setCredits(6);
        course.setTeacher(teacher);
        em.persist(course);

        Student student = new Student();
        student.setFirstName("Alice");
        student.setLastName("Smith");
        student.setAverageGrade(4.5);
        student.setAttendance(95);
        student.setCourses(Set.of(course));
        em.persist(student);

        course.setStudents(Set.of(student));

        em.getTransaction().commit();
        */

        // 4. Hospital Database

        /*
        em.getTransaction().begin();

        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setAddress("123 Main St");
        patient.setEmail("john.doe@example.com");
        patient.setDateOfBirth(LocalDate.of(1990, 5, 20));
        patient.setHasMedicalInsurance(true);
        patient.setPicture("https://example.com/john_doe.jpg");

        Visitation visitation = new Visitation();
        visitation.setDate(LocalDate.now());
        visitation.setComments("Routine check-up");
        visitation.setPatient(patient);

        patient.getVisitations().add(visitation);

        Diagnose diagnose = new Diagnose();
        diagnose.setName("Common Cold");
        diagnose.setComments("Minor symptoms");

        diagnose.getPatients().add(patient);
        patient.getDiagnoses().add(diagnose);

        Medicament medicament = new Medicament();
        medicament.setName("Paracetamol");

        medicament.getPatients().add(patient);
        patient.getMedicaments().add(medicament);

        em.persist(patient);
        em.persist(visitation);
        em.persist(diagnose);
        em.persist(medicament);

        em.getTransaction().commit();
        */

        // 5. Bills Payment System

        /*
        em.getTransaction().begin();

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("securePassword123");

        em.persist(user);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankName("Big Bank");
        bankAccount.setSwiftCode("BIGBB123");
        bankAccount.setNumber("123456789");
        bankAccount.setOwner(user);

        em.persist(bankAccount);

        CreditCard creditCard = new CreditCard();
        creditCard.setNumber("4111111111111111");
        creditCard.setExpirationMonth(12);
        creditCard.setExpirationYear(2027);
        creditCard.setType(CreditCardType.Gold);
        creditCard.setOwner(user);

        em.persist(creditCard);

        em.getTransaction().commit();
        */
    }
}