package bg.softuni.jackson;

public class PersonDTO
{
    private String firstName;
    private String lastName;
    private int age;
    private boolean isMarried;

    public PersonDTO() {} // Jackson library needs a default constructor ot convert JSON to object

    public PersonDTO(String firstName, String lastName, int age, boolean isMarried)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isMarried = isMarried;
    }

    // Jackson library needs getters and setters to convert object to JSON
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public boolean isMarried()
    {
        return isMarried;
    }

    public void setMarried(boolean married)
    {
        isMarried = married;
    }

    @Override
    public String toString()
    {
        return "PersonDTO {" +
                "firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", age = " + age +
                ", isMarried = " + isMarried +
                '}';
    }
}
