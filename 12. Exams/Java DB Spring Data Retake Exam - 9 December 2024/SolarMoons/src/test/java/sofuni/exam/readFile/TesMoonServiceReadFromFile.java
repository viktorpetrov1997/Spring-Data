package sofuni.exam.readFile;
//TestMoonServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import sofuni.exam.service.Impl.MoonServiceImpl;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TesMoonServiceReadFromFile {

    @Autowired
    private MoonServiceImpl moonService;

    @Test
    void readMoonsFromFile() throws IOException {

        String expected = """
                <?xml version='1.0' encoding='UTF-8'?>
                <moons>
                    <moon>
                        <name>Moon</name>
                        <discovered>1609-11-30</discovered>
                        <distance_from_planet>384400</distance_from_planet>
                        <radius>1737.4</radius>
                        <discoverer_id>1</discoverer_id>
                        <planet_id>3</planet_id>
                    </moon>
                    <moon>
                        <name>Moon</name>
                        <discovered>1609-11-30</discovered>
                        <distance_from_planet>384400</distance_from_planet>
                        <radius>1737.4</radius>
                        <discoverer_id>1</discoverer_id>
                        <planet_id>3</planet_id>
                    </moon>
                    <moon>
                        <name>Phobos</name>
                        <discovered>1877-08-18</discovered>
                        <distance_from_planet>9377</distance_from_planet>
                        <radius>11.27</radius>
                        <discoverer_id>6</discoverer_id>
                        <planet_id>4</planet_id>
                    </moon>
                    <moon>
                        <name>P</name>
                        <discovered>1877-08-18</discovered>
                        <distance_from_planet>9377</distance_from_planet>
                        <radius>11.27</radius>
                        <discoverer_id>6</discoverer_id>
                        <planet_id>4</planet_id>
                    </moon>
                    <moon>
                        <name>Deimos</name>
                        <discovered>1877-08-12</discovered>
                        <distance_from_planet>23460</distance_from_planet>
                        <radius>6.2</radius>
                        <discoverer_id>6</discoverer_id>
                        <planet_id>4</planet_id>
                    </moon>
                    <moon>
                        <name>Io</name>
                        <discovered>1610-01-07</discovered>
                        <distance_from_planet>421800</distance_from_planet>
                        <radius>1821.6</radius>
                        <discoverer_id>1</discoverer_id>
                        <planet_id>5</planet_id>
                    </moon>
                    <moon>
                        <name>Europa</name>
                        <discovered>1610-01-07</discovered>
                        <distance_from_planet>670900</distance_from_planet>
                        <radius>1560.8</radius>
                        <discoverer_id>1</discoverer_id>
                        <planet_id>5</planet_id>
                    </moon>
                    <moon>
                        <name>Ganymede</name>
                        <discovered>1610-01-07</discovered>
                        <distance_from_planet>1070400</distance_from_planet>
                        <radius>2634.1</radius>
                        <discoverer_id>1</discoverer_id>
                        <planet_id>5</planet_id>
                    </moon>
                    <moon>
                        <name>Callisto</name>
                        <discovered>1610-01-07</discovered>
                        <distance_from_planet>1882700</distance_from_planet>
                        <radius>2410.3</radius>
                        <discoverer_id>1</discoverer_id>
                        <planet_id>5</planet_id>
                    </moon>
                    <moon>
                        <name>Titan</name>
                        <discovered>1655-03-25</discovered>
                        <distance_from_planet>1221830</distance_from_planet>
                        <radius>2574.73</radius>
                        <discoverer_id>2</discoverer_id>
                        <planet_id>6</planet_id>
                    </moon>
                    <moon>
                        <name>Rhea</name>
                        <discovered>1672-12-23</discovered>
                        <distance_from_planet>527040</distance_from_planet>
                        <radius>763.8</radius>
                        <discoverer_id>3</discoverer_id>
                        <planet_id>6</planet_id>
                    </moon>
                    <moon>
                        <name>Iapetus</name>
                        <discovered>1671-10-25</discovered>
                        <distance_from_planet>3560820</distance_from_planet>
                        <radius>734.5</radius>
                        <discoverer_id>3</discoverer_id>
                        <planet_id>6</planet_id>
                    </moon>
                    <moon>
                        <name>Tethys</name>
                        <discovered>1684-03-21</discovered>
                        <distance_from_planet>294619</distance_from_planet>
                        <radius>531.1</radius>
                        <discoverer_id>3</discoverer_id>
                        <planet_id>6</planet_id>
                    </moon>
                    <moon>
                        <name>Dione</name>
                        <discovered>1684-03-21</discovered>
                        <distance_from_planet>377396</distance_from_planet>
                        <radius>561.4</radius>
                        <discoverer_id>3</discoverer_id>
                        <planet_id>6</planet_id>
                    </moon>
                    <moon>
                        <name>Titania</name>
                        <discovered>1787-01-11</discovered>
                        <distance_from_planet>435910</distance_from_planet>
                        <radius>788.4</radius>
                        <discoverer_id>4</discoverer_id>
                        <planet_id>7</planet_id>
                    </moon>
                    <moon>
                        <name>Oberon</name>
                        <discovered>1787-01-11</discovered>
                        <distance_from_planet>583520</distance_from_planet>
                        <radius>761.4</radius>
                        <discoverer_id>4</discoverer_id>
                        <planet_id>7</planet_id>
                    </moon>
                    <moon>
                        <name>Ariel</name>
                        <discovered>1851-10-24</discovered>
                        <distance_from_planet>191020</distance_from_planet>
                        <radius>578.9</radius>
                        <discoverer_id>7</discoverer_id>
                        <planet_id>7</planet_id>
                    </moon>
                    <moon>
                        <name>Umbriel</name>
                        <discovered>1851-10-24</discovered>
                        <distance_from_planet>266000</distance_from_planet>
                        <radius>584.7</radius>
                        <discoverer_id>7</discoverer_id>
                        <planet_id>7</planet_id>
                    </moon>
                    <moon>
                        <name>Miranda</name>
                        <discovered>1948-02-16</discovered>
                        <distance_from_planet>129390</distance_from_planet>
                        <radius>235.8</radius>
                        <discoverer_id>5</discoverer_id>
                        <planet_id>7</planet_id>
                    </moon>
                    <moon>
                        <name>Triton</name>
                        <discovered>1846-10-10</discovered>
                        <distance_from_planet>354800</distance_from_planet>
                        <radius>1353.4</radius>
                        <discoverer_id>7</discoverer_id>
                        <planet_id>8</planet_id>
                    </moon>
                    <moon>
                        <name>Nereid</name>
                        <discovered>1949-05-01</discovered>
                        <distance_from_planet>5513800</distance_from_planet>
                        <radius>170</radius>
                        <discoverer_id>5</discoverer_id>
                        <planet_id>8</planet_id>
                    </moon>
                    <moon>
                        <name>Galatea</name>
                        <discovered>1989-07-30</discovered>
                        <distance_from_planet>37200</distance_from_planet>
                        <radius>87.4</radius>
                        <discoverer_id>9</discoverer_id>
                        <planet_id>8</planet_id>
                    </moon>
                    <moon>
                        <name>Charon</name>
                        <discovered>1978-06-22</discovered>
                        <distance_from_planet>17536</distance_from_planet>
                        <radius>606</radius>
                        <discoverer_id>8</discoverer_id>
                        <planet_id>9</planet_id>
                    </moon>
                </moons>""";

        String actual = moonService.readMoonsFileContent()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();
        Assertions.assertEquals(expected, actual);
    }
}